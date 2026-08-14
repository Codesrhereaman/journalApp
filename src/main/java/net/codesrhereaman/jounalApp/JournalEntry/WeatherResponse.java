package net.codesrhereaman.jounalApp.JournalEntry;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    public static class Current {

        private Astro astro;

        @JsonProperty("air_quality")
        private AirQuality airQuality;

        @JsonProperty("wind_speed")
        private int windSpeed;

        private int humidity;

        @JsonProperty("feelslike")
        private int feelsLike;
    }

    @Getter
    @Setter
    public static class AirQuality {
        private String co;
        private String o3;
    }

    @Getter
    @Setter
    public static class Astro {
        private String sunrise;
        private String sunset;
        private String moonrise;
        private String moonset;
    }
}