package net.codesrhereaman.jounalApp.services;

import net.codesrhereaman.jounalApp.JournalEntry.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {



    @Autowired
    private RestTemplate restTemplate;

    private final String apiKey = "4bffe0f2ce050d167aa6eb474a1e2065";

    private String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=City";

    public  WeatherResponse weatherResponse(String city){
        String finalApi = API.replace("API_KEY",apiKey).replace("City",city);
        ResponseEntity<WeatherResponse> weather = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = weather.getBody();
        return body;
    }

}
