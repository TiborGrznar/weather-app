package com.tgrznar.weather_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Restrict cross-origin access to our own frontend only, so other websites
    // can't call our backend (and indirectly consume our OpenWeatherMap quota)
    // from their visitors' browsers.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173");
    }
}