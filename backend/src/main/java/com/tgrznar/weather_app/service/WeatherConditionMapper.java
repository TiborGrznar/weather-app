package com.tgrznar.weather_app.service;

import com.tgrznar.weather_app.dto.WeatherCondition;

public final class WeatherConditionMapper {

    private WeatherConditionMapper() {
        // utility class — not meant to be instantiated
    }

    public static WeatherCondition fromOpenWeatherMapId(int conditionId) {
        return switch (conditionId) {
            case 200, 201, 202, 210, 211, 212, 221, 230, 231, 232 -> WeatherCondition.THUNDERSTORM;
            case 300, 301, 302, 310, 311, 312, 313, 314, 321 -> WeatherCondition.DRIZZLE;
            case 500, 501, 502, 503, 504, 511, 520, 521, 522, 531 -> WeatherCondition.RAIN;
            case 600, 601, 602, 611, 612, 613, 615, 616, 620, 621, 622 -> WeatherCondition.SNOW;
            case 701, 711, 721, 731, 741, 751, 761, 762, 771, 781 -> WeatherCondition.FOG;
            case 800 -> WeatherCondition.CLEAR;
            case 801, 802 -> WeatherCondition.PARTLY_CLOUDY;
            case 803, 804 -> WeatherCondition.CLOUDY;
            default -> WeatherCondition.UNKNOWN;
        };
    }
}