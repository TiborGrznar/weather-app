import { useEffect, useState } from "react";
import type { WeatherResponse } from "./types/weather";
import WeatherResult from "./components/WeatherResult";
import ErrorMessage from "./components/ErrorMessage";

function App() {
  const [city, setCity] = useState("");
  const [weather, setWeather] = useState<WeatherResponse | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);
  const [darkMode, setDarkMode] = useState(false);

  useEffect(() => {
    document.documentElement.classList.toggle("dark", darkMode);
  }, [darkMode]);

  const handleSearch = async () => {
    setError(null);
    setWeather(null);
    setLoading(true);

    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/api/weather?city=${city}`);

      if (!response.ok) {
        const errorData = await response.json();
        setError(errorData.message);
        return;
      }

      const data = await response.json();
      setWeather(data);
    } catch {
      setError("Could not reach the server. Please try again later.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 dark:bg-gray-900 flex items-center justify-center">
      <div className="relative w-full max-w-sm bg-gray-50 dark:bg-gray-800 rounded-2xl border border-gray-200 dark:border-gray-700 p-6">
        <button
          onClick={() => setDarkMode(!darkMode)}
          className="absolute top-4 right-4 text-xs text-gray-500 dark:text-gray-400 border border-gray-300 dark:border-gray-600 rounded-lg px-2 py-1"
        >
          {darkMode ? "Light mode" : "Dark mode"}
        </button>

        <h1 className="text-2xl font-semibold text-center mb-5 text-gray-700 dark:text-white">
          Weather App
        </h1>

        <div className="flex gap-2 mb-2">
          <input
            type="text"
            value={city}
            onChange={(event) => setCity(event.target.value)}
            placeholder="Enter a city"
            className="flex-1 border border-gray-300 dark:border-gray-600 dark:bg-gray-700 dark:text-white rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-400 dark:focus:ring-blue-900"
          />
          <button
            onClick={handleSearch}
            disabled={loading}
            className="px-4 py-2 bg-blue-500 text-white text-sm font-medium rounded-lg disabled:opacity-50 dark:bg-blue-900"
          >
            Search
          </button>
        </div>

        {loading && <p className="text-sm text-gray-500 dark:text-gray-400 text-center mt-2">Loading...</p>}

        {weather && <WeatherResult weather={weather} />}
        {error && <ErrorMessage message={error} />}
      </div>
    </div>
  );
}

export default App;