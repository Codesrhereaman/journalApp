package net.codesrhereaman.jounalapp.services;

import lombok.RequiredArgsConstructor;
import net.codesrhereaman.jounalapp.cache.AppCache;
import net.codesrhereaman.jounalapp.journalentry.WeatherResponse;
import net.codesrhereaman.jounalapp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherService {



    private final AppCache cache;



    private final RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private  String apiKey ;


    public  WeatherResponse weatherResponse(String city){
        String finalApi = cache.appCache.get(AppCache.keys.WEATHER_API.name()).replace(Placeholders.API_KEY,apiKey).replace(Placeholders.CITY,city);
        ResponseEntity<WeatherResponse> weather = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = weather.getBody();
        return body;
    }

}
