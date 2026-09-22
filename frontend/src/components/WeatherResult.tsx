import type { WeatherResponse } from "../types/weather";

function WeatherResult({ weather }: { weather: WeatherResponse }) {
  return (
    <div className="text-center pt-6 mt-4 border-t border-gray-200">
      <p className="text-base font-medium text-gray-600">{weather.city}</p>
      <p className="text-4xl font-semibold my-1">{weather.temperature}°C</p>
      <p className="text-sm text-gray-500">{weather.description}</p>
    </div>
  );
}

export default WeatherResult;