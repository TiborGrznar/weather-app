package com.tgrznar.weather_app.service;

import com.tgrznar.weather_app.dto.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.data.Offset.offset;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

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
                  "sys": { "country": "Slovakia" },
                  "main": {
                    "temp": 21.5,
                    "feels_like": 20.8,
                    "temp_min": 18.0,
                    "temp_max": 24.0,
                    "pressure": 1015,
                    "humidity": 55
                  },
                  "weather": [ { "id": 800, "description": "clear sky" } ],
                  "wind": { "speed": 3.4 }
                }
                """;

        server.expect(requestTo(org.hamcrest.Matchers.startsWith(
                        "https://api.openweathermap.org/data/2.5/weather")))
                .andExpect(queryParam("q", "Kosice"))
                .andExpect(queryParam("units", "metric"))
                .andRespond(withSuccess(fakeJson, MediaType.APPLICATION_JSON));

        WeatherResponse result = weatherService.getWeather("Kosice");

        assertThat(result.city()).isEqualTo("Kosice");
        assertThat(result.country()).isEqualTo("Slovakia");
        assertThat(result.temperature()).isEqualTo(21.5);
        assertThat(result.description()).isEqualTo("clear sky");
        assertThat(result.condition()).isEqualTo("CLEAR");
        assertThat(result.feelsLike()).isEqualTo(20.8);
        assertThat(result.humidity()).isEqualTo(55);
        assertThat(result.windSpeed()).isCloseTo(12.24, offset(0.01));
        assertThat(result.pressure()).isEqualTo(1015);
        assertThat(result.minTemperature()).isEqualTo(18.0);
        assertThat(result.maxTemperature()).isEqualTo(24.0);
    }

    @Test
    void getWeather_throwsNotFoundWhenCityDoesNotExist() {

        server.expect(requestTo(org.hamcrest.Matchers.startsWith(
                        "https://api.openweathermap.org/data/2.5/weather")))
                .andExpect(queryParam("q", "Nonexistentcity"))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        assertThatThrownBy(() -> weatherService.getWeather("Nonexistentcity"))
                .isInstanceOf(HttpClientErrorException.NotFound.class);
    }
}