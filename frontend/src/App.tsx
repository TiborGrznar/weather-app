import { useState } from "react";
import type { WeatherResponse } from "./types/weather";
import WeatherResult from "./components/WeatherResult";
import ErrorMessage from "./components/ErrorMessage";


function App() {
  const [city, setCity] = useState("");
  const [weather, setWeather] = useState<WeatherResponse | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState(false);
 
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
  <div className="min-h-screen bg-gray-50 flex items-center justify-center">
    <div className="w-full max-w-sm bg-white rounded-2xl border border-gray-200 p-6">
      <h1 className="text-2xl font-semibold text-center mb-5">Weather App</h1>

      <div className="flex gap-2 mb-2">
        <input
          type="text"
          value={city}
          onChange={(event) => setCity(event.target.value)}
          placeholder="Enter a city"
          className="flex-1 border border-gray-300 rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-400"
        />
        <button
          onClick={handleSearch}
          disabled={loading}
          className="px-4 py-2 bg-blue-500 text-white text-sm font-medium rounded-lg disabled:opacity-50"
        >
          Search
        </button>
      </div>

      {loading && <p className="text-sm text-gray-500 text-center mt-2">Loading...</p>}

      {weather && <WeatherResult weather={weather} />}
      {error && <ErrorMessage message={error} />}
    </div>
  </div>
);
}

export default App;