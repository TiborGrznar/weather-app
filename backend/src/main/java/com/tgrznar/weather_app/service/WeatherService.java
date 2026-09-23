package com.tgrznar.weather_app.service;

import com.tgrznar.weather_app.client.OpenWeatherMapResponse;
import com.tgrznar.weather_app.dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherService {

    // Injected from application.properties, which resolves it from OPENWEATHER_API_KEY env variable (.env in dev)
    @Value("${openweather.api.key}")
    private String apiKey;

    // Built from the injected Builder rather than via Lombok/DI directly — required so that
    // @RestClientTest can supply a Builder wired to MockRestServiceServer in tests.
    private final RestClient restClient;

    public WeatherService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    public WeatherResponse getWeather(String city) {

        OpenWeatherMapResponse response = restClient.get()
                .uri("https://api.openweathermap.org/data/2.5/weather?q={city}&appid={key}&units=metric",
                        city, apiKey)
                .retrieve()
                .body(OpenWeatherMapResponse.class);

        String description = response.weather().get(0).description();
        int conditionId = response.weather().get(0).id();
        String condition = WeatherConditionMapper.fromOpenWeatherMapId(conditionId).name();

        double windSpeedKmh = response.wind().speed() * 3.6;

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