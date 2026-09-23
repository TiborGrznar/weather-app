package com.tgrznar.weather_app.dto;

public record WeatherResponse(
        String city,
        String country,
        double temperature,
        String description,
        String condition,
        double feelsLike,
        int humidity,
        double windSpeed,
        int pressure,
        double minTemperature,
        double maxTemperature
) {}
