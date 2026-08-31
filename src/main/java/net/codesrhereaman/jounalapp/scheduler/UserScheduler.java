package net.codesrhereaman.jounalapp.scheduler;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.codesrhereaman.jounalapp.cache.AppCache;
import net.codesrhereaman.jounalapp.enums.Sentiments;
import net.codesrhereaman.jounalapp.journalentry.JournalEntry;
import net.codesrhereaman.jounalapp.journalentry.User;
import net.codesrhereaman.jounalapp.repository.UserRepositoryQueries;
import net.codesrhereaman.jounalapp.services.EmailSenderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Data
@Slf4j
public class UserScheduler {


    private final UserRepositoryQueries userRepositoryQueries;

    private final EmailSenderService emailSenderService;

    private final AppCache appCache;


    //cron is used to create scheduling in java with some format like this
    @Scheduled(cron = "0 * 0 * * SUN,Thu")
    public void fetchAndSendSAEmail(){
        List<User> users = userRepositoryQueries.getUsersWithSA();
        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiments> Sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiments,Integer> sentimentCount = new HashMap<>();
            for (Sentiments sentiment: Sentiments){
                if(sentiment!=null){
                    sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment,0)+1);
                }
            }
            int maxCount = 0;
            Sentiments mostFrequentSentiment = null;
            for(Map.Entry<Sentiments,Integer> count : sentimentCount.entrySet()){
                if(count.getValue() > maxCount){
                    mostFrequentSentiment = count.getKey();
                    maxCount = count.getValue();
                }
            }

            if(mostFrequentSentiment!=null){
                log.info(mostFrequentSentiment.toString());
                emailSenderService.sendEmail(user.getEmail(),"Sentiments for you upto last 7 days",mostFrequentSentiment.toString());
            }
        }
    }

    @Scheduled(cron = "* */5 * * * SUN")
    public void cacheRefresh(){
        log.info("Cache is refreshed");
        appCache.init();
    }
}
