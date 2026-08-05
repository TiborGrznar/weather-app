package com.tgrznar.weather_app.dto;

public record WeatherResponse(String city, double temperature, String description) {
    }
