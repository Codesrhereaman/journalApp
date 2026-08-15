package net.codesrhereaman.jounalApp.services;

import lombok.RequiredArgsConstructor;
import net.codesrhereaman.jounalApp.JournalEntry.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherService {


    private final RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private  String apiKey ;

    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=City";

    public  WeatherResponse weatherResponse(String city){
        String finalApi = API.replace("API_KEY",apiKey).replace("City",city);
        ResponseEntity<WeatherResponse> weather = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = weather.getBody();
        return body;
    }

}
