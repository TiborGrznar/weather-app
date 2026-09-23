import type { WeatherResponse } from "../types/weather";
import WeatherIcon from "./WeatherIcon";

function WeatherResult({ weather }: { weather: WeatherResponse }) {
  return (
    <div className="text-center pt-6 mt-4 border-t border-gray-200 dark:border-gray-700">
      <p className="text-base font-medium text-gray-600 dark:text-gray-300">
        {weather.city}, {weather.country}
      </p>

      <div className="flex items-center justify-center gap-3 my-1 text-gray-700 dark:text-white">
        <WeatherIcon condition={weather.condition} />
        <p className="text-4xl font-semibold">
          {Math.round(weather.temperature)}°C
        </p>
      </div>

      <p className="text-sm text-gray-500 dark:text-gray-400">{weather.description}</p>
      <p className="text-xs text-gray-400 dark:text-gray-500 mt-1">
        H: {Math.round(weather.maxTemperature)}°C&nbsp;&nbsp;L: {Math.round(weather.minTemperature)}°C
      </p>

      <div className="grid grid-cols-2 gap-2 mt-4 text-left">
        <div className="bg-gray-100 dark:bg-gray-700 rounded-lg px-3 py-2">
          <p className="text-xs text-gray-400 dark:text-gray-400">Feels like</p>
          <p className="text-sm font-medium text-gray-600 dark:text-white">
            {Math.round(weather.feelsLike)}°C
          </p>
        </div>
        <div className="bg-gray-100 dark:bg-gray-700 rounded-lg px-3 py-2">
          <p className="text-xs text-gray-400 dark:text-gray-400">Humidity</p>
          <p className="text-sm font-medium text-gray-700 dark:text-white">{weather.humidity}%</p>
        </div>
        <div className="bg-gray-100 dark:bg-gray-700 rounded-lg px-3 py-2">
          <p className="text-xs text-gray-400 dark:text-gray-400">Wind</p>
          <p className="text-sm font-medium text-gray-700 dark:text-white">
            {weather.windSpeed.toFixed(1)} km/h
          </p>
        </div>
        <div className="bg-gray-100 dark:bg-gray-700 rounded-lg px-3 py-2">
          <p className="text-xs text-gray-400 dark:text-gray-400">Pressure</p>
          <p className="text-sm font-medium text-gray-700 dark:text-white">{weather.pressure} hPa</p>
        </div>
      </div>
    </div>
  );
}

export default WeatherResult;