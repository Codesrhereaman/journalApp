package net.codesrhereaman.jounalApp.JournalEntry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class GreetingResponse {
    private WeatherResponse weatherResponse;
    private String  message;
}
