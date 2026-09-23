package com.tgrznar.weather_app.service;

import com.tgrznar.weather_app.client.OpenWeatherMapResponse;
import com.tgrznar.weather_app.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class WeatherService {

    // Injected from application.properties, which resolves it from OPENWEATHER_API_KEY env variable (.env in dev)
    @Value("${openweather.api.key}")
    private String apiKey;

    // Injected from WebConfig's weatherRestClient bean; RestClient instances are safe to share
    private final RestClient restClient;

    public WeatherResponse getWeather(String city) {

        // Call OpenWeatherMap's current weather endpoint for the given city
        // {city} and {key} placeholders are filled in order by city, apiKey
        OpenWeatherMapResponse response = restClient.get()
                .uri("https://api.openweathermap.org/data/2.5/weather?q={city}&appid={key}&units=metric",
                        city, apiKey)
                .retrieve()
                .body(OpenWeatherMapResponse.class);

        // OpenWeatherMap returns "weather" as a list; we only need the first entry
        String description = response.weather().get(0).description();
        int conditionId = response.weather().get(0).id();
        String condition = WeatherConditionMapper.fromOpenWeatherMapId(conditionId).name();

        // OpenWeatherMap returns wind speed in m/s; convert to km/h for our own API contract
        double windSpeedKmh = response.wind().speed() * 3.6;

        // Map the external API's raw shape onto our own public DTO
        return new WeatherResponse(
                response.name(),
                response.sys().country(),
                response.main().temp(),
                description,
                condition,
                response.main().feelsLike(),
                response.main().humidity(),
                windSpeedKmh,
                response.main().pressure(),
                response.main().tempMin(),
                response.main().tempMax()
        );
    }
}