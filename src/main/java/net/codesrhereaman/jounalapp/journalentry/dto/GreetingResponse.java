package net.codesrhereaman.jounalapp.journalentry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.codesrhereaman.jounalapp.journalentry.WeatherResponse;

@Getter
@AllArgsConstructor
public class GreetingResponse {
    private WeatherResponse weatherResponse;
    private String  message;
}
