package com.tgrznar.weather_app.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OpenWeatherMapResponse(
        String name,
        Sys sys,
        List<Weather> weather,
        Main main,
        Wind wind
) {

    public record Sys(String country) {}

    public record Weather(int id, String description) {}

    public record Main(
            double temp,
            @JsonProperty("feels_like") double feelsLike,
            @JsonProperty("temp_min") double tempMin,
            @JsonProperty("temp_max") double tempMax,
            int pressure,
            int humidity
    ) {}

    public record Wind(double speed) {}
}