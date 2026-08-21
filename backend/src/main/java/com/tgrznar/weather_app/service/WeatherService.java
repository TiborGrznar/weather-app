package com.tgrznar.weather_app.service;

import com.tgrznar.weather_app.dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class WeatherService {

    // Injected from application.properties, which resolves it from OPENWEATHER_API_KEY env variablle (.env in dev)
    @Value("${openweather.api.key}")
    private String apiKey;

    // Reused across calls; RestClient instances are safe to share
    private final RestClient restClient;

    public WeatherService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }



    public WeatherResponse getWeather(String city) {

        // Call OpenWeatherMap's current weather endpoint for the given city
        // {city} and {key} placeholders are filled in order by city, apiKey
        OpenWeatherApiResponse response = restClient.get()
                .uri("https://api.openweathermap.org/data/2.5/weather?q={city}&appid={key}&units=metric",
                        city, apiKey)
                .retrieve()
                .body(OpenWeatherApiResponse.class);

        // OpenWeatherMap returns "weather" as a list; we only need the first entry's description
        String description = response.weather().get(0).description();

        // Map the external API's raw shape onto our own public DTO
        return new WeatherResponse(
                response.name(),
                response.main().temp(),
                description
        );
    }

    // Internal representation of OpenWeatherMap's JSON response.
    // Private: this is an implementation detail, not part of our API contract (see WeatherResponse).
    private record OpenWeatherApiResponse(String name, Main main, List<Weather> weather) {
        private record Main(double temp) {}
        private record Weather(String description) {}
    }
}
