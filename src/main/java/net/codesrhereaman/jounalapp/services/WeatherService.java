package net.codesrhereaman.jounalapp.services;

import lombok.RequiredArgsConstructor;
import net.codesrhereaman.jounalapp.cache.AppCache;
import net.codesrhereaman.jounalapp.enums.Weather;
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

    private final RedisService redisService;


    public  WeatherResponse weatherResponse(String city){
        WeatherResponse weatherResponse = redisService.get(Weather.weather_of + city, WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }else{
            String finalApi = cache.appCache.get(AppCache.keys.WEATHER_API.name()).replace(Placeholders.API_KEY,apiKey).replace(Placeholders.CITY,city);
            ResponseEntity<WeatherResponse> weather = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = weather.getBody();
            if(body != null){
                redisService.set(Weather.weather_of + city,body,300);
            }
            return body;
        }
    }




}
