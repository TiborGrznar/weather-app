import { useState } from "react";
import type { WeatherResponse } from "./types/weather";
import WeatherResult from "./components/WeatherResult";
import ErrorMessage from "./components/ErrorMessage";


function App() {
  const [city, setCity] = useState("");
  const [weather, setWeather] = useState<WeatherResponse | null>(null);
  const [error, setError] = useState<string | null>(null);
 
  const handleSearch = async () => {
    setError(null);
    setWeather(null);
  

  try {
      const response = await fetch(`http://localhost:8080/api/weather?city=${city}`);

      if (!response.ok) {
        const errorData = await response.json();
        setError(errorData.message);
        return;
      }

      const data = await response.json();
      setWeather(data);
    } catch {
      setError("Could not reach the server. Please try again later.");
    }
  };

  return (
    <div>
      <h1>Weather App</h1>
      <input
        type="text"
        value={city}
        onChange={(event) => setCity(event.target.value)}
        placeholder="Enter a city"
      />
      <button onClick={handleSearch}>Search</button>

      {weather && <WeatherResult weather={weather} />}
      {error && <ErrorMessage message={error} />}
    </div>
  );
}

export default App;