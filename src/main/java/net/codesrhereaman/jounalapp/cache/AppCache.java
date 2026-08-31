package net.codesrhereaman.jounalapp.cache;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.codesrhereaman.jounalapp.journalentry.JournalAppConfigEntity;
import net.codesrhereaman.jounalapp.repository.JournalAppConfigRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    private final JournalAppConfigRepository journalAppConfigRepository;

    public Map<String,String> appCache ;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<JournalAppConfigEntity> APP_CACHE = journalAppConfigRepository.findAll();
        for(JournalAppConfigEntity journalAppConfigEntity : APP_CACHE  ){
            appCache.put(journalAppConfigEntity.getKey(),journalAppConfigEntity.getValue());
        };
    }

}
