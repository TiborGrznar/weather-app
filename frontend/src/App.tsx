import { useState } from "react";
import type { WeatherResponse } from "./types/weather";
import WeatherResult from "./components/WeatherResult";


function App() {
  const [city, setCity] = useState("");
  const [weather, setWeather] = useState<WeatherResponse | null>(null);

  const handleSearch = async () => {
    const response = await fetch(`http://localhost:8080/api/weather?city=${city}`);
    const data = await response.json();
    setWeather(data);
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
    </div>
  );
}

export default App;