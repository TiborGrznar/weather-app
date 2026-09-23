export type WeatherCondition =
    | "CLEAR"
    | "PARTLY_CLOUDY"
    | "CLOUDY"
    | "DRIZZLE"
    | "RAIN"
    | "THUNDERSTORM"
    | "SNOW"
    | "FOG"
    | "UNKNOWN";

export interface WeatherResponse {
    city: string;
    country: string;
    temperature: number;
    description: string;
    condition: WeatherCondition;
    feelsLike: number;
    humidity: number;
    windSpeed: number;
    pressure: number;
    minTemperature: number;
    maxTemperature: number;
}