package com.tgrznar.weather_app.controller;

import com.tgrznar.weather_app.dto.WeatherResponse;
import com.tgrznar.weather_app.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherService weatherService;

    @Test
    void getWeather_returnsJsonFromService() throws Exception {
        when(weatherService.getWeather("Kosice"))
                .thenReturn(new WeatherResponse(
                        "Kosice",
                        "Slovakia",
                        21.5,
                        "clear sky",
                        "CLEAR",
                        20.8,
                        55,
                        12.5,
                        1015,
                        18.0,
                        24.0
                ));

        mockMvc.perform(get("/api/weather").param("city", "Kosice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Kosice"))
                .andExpect(jsonPath("$.country").value("Slovakia"))
                .andExpect(jsonPath("$.temperature").value(21.5))
                .andExpect(jsonPath("$.description").value("clear sky"))
                .andExpect(jsonPath("$.condition").value("CLEAR"))
                .andExpect(jsonPath("$.feelsLike").value(20.8))
                .andExpect(jsonPath("$.humidity").value(55))
                .andExpect(jsonPath("$.windSpeed").value(12.5))
                .andExpect(jsonPath("$.pressure").value(1015))
                .andExpect(jsonPath("$.minTemperature").value(18.0))
                .andExpect(jsonPath("$.maxTemperature").value(24.0));
    }
}