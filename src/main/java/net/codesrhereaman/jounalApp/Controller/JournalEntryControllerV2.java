package net.codesrhereaman.jounalApp.Controller;

import net.codesrhereaman.jounalApp.JournalEntry.JournalEntry;
import net.codesrhereaman.jounalApp.JournalEntry.User;
import net.codesrhereaman.jounalApp.services.JournalEntryService;
import net.codesrhereaman.jounalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal")   //gives a path to a class
public class JournalEntryControllerV2 {
    //all methods inside post mapping must be  public


    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName());
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            journalEntryService.saveEntry(entry, authentication.getName());
            return new ResponseEntity<>(entry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myid}")  //myid is a pth variable
    public ResponseEntity<?> getJournalById(@PathVariable ObjectId myid) { //using ? give you return any kind of returnType
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName());
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> entryById = journalEntryService.getEntryById(myid);
            if (entryById.isPresent()) {
                return new ResponseEntity<>(entryById.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myid}")  //myid is a pth variable
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName());
        boolean isRemoved = user.getJournalEntries().removeIf(x -> x.getId().equals(myid));
        try {
            if (isRemoved) {
                journalEntryService.deleteEntryById(user.getUserName(), myid);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")  //myid is a pth variable
    public ResponseEntity<?> modifyJournalById(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName());
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()) {
            JournalEntry old = journalEntryService.getEntryById(id).orElse(null);
            if (old != null) {
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old, authentication.getName());
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
