import type { WeatherResponse } from "../types/weather";

function WeatherResult({ weather }: { weather: WeatherResponse }) {
  return (
    <div>
      <p>{weather.city}</p>
      <p>{weather.temperature}°C</p>
      <p>{weather.description}</p>
    </div>
  );
}

export default WeatherResult;