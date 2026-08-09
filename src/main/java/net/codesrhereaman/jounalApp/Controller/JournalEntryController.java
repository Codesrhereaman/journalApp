//package net.coderrhereaman.jounalApp.Controller;
//
//import net.coderrhereaman.jounalApp.JournalEntry.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
////this is POJO(plain old java object)
//@RestController
//@RequestMapping("/_journal")   //gives a path to a class
//public class JournalEntryController {
//    //all methods inside post mapping are public
//    public Map<Long, JournalEntry> journalEntry = new HashMap<>();
//
//    //if without mapping path journal url is called then it check it get then go to getMapping else go to post...
//    //no same mapping without specific path can not exist in a same api
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntry.values());
//    }
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry entry){
//        journalEntry.put(entry.getId(),entry);
//        return true;
//    }
//
//    @GetMapping("id/{myid}")  //myid is a pth variable
//    public JournalEntry getJournalById(@PathVariable long myid){
//        return journalEntry.get(myid);
//    }
//
//    @DeleteMapping("id/{myid}")  //myid is a pth variable
//    public JournalEntry deleteJournalById(@PathVariable long myid){
//        return journalEntry.remove(myid);
//    }
//    @PutMapping("id/{id}")  //myid is a pth variable
//    public JournalEntry modifyJournalById(@PathVariable long id,@RequestBody JournalEntry entry){
//        return journalEntry.put(id,entry);  //will return the old entry
//    }
//}
