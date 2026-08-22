package com.tgrznar.weather_app.service;

import com.tgrznar.weather_app.dto.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;

@RestClientTest(WeatherService.class)
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    void getWeather_mapsOpenWeatherResponseToWeatherResponse() {

        String fakeJson = """
                {
                  "name": "Kosice",
                  "main": { "temp": 21.5 },
                  "weather": [ { "description": "clear sky" } ]
                }
                """;

        server.expect(requestTo(org.hamcrest.Matchers.startsWith(
                        "https://api.openweathermap.org/data/2.5/weather")))
                .andExpect(queryParam("q", "Kosice"))
                .andExpect(queryParam("units", "metric"))
                .andRespond(withSuccess(fakeJson, MediaType.APPLICATION_JSON));

        WeatherResponse result = weatherService.getWeather("Kosice");

        assertThat(result.city()).isEqualTo("Kosice");
        assertThat(result.temperature()).isEqualTo(21.5);
        assertThat(result.description()).isEqualTo("clear sky");
    }
}