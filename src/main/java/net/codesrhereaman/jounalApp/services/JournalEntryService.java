package net.codesrhereaman.jounalApp.services;

import lombok.extern.slf4j.Slf4j;
import net.codesrhereaman.jounalApp.JournalEntry.JournalEntry;
import net.codesrhereaman.jounalApp.JournalEntry.User;
import net.codesrhereaman.jounalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    //dependency injection
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;


    //adding logger to see what is causing error and how in console or other file
   //USING @slf4j

    //as transactional call are only used when mongo db can have replicas ,hence connection to a mongodb atlas cluster
    @Transactional
    public void saveEntry(JournalEntry journalEntry,String userName){
        try{
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry save = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(save);
            userService.saveUser(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An Error occured while saving the entry ! ");
        }

    }

    public Optional<JournalEntry> getEntryById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public boolean deleteEntryById(String userName, ObjectId id){
        try {
            User user = userService.findByUserName(userName);
            user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            journalEntryRepository.deleteById(id);
            userService.saveUser(user);
            return true;
        }catch (Exception e){
            log.trace("error occured in :{}",id,e);
            log.info("error occured in :{}",id,e);
            log.warn("error occured in :{}",id);
            log.debug("error occured in :{}",id);
            log.error("error occured in :{}",id);
            return false;
        }
    }

//    public JournalEntry modifyEntryById(JournalEntry newEntry){
//
//    }

}
