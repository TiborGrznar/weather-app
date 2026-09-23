import type { ReactElement } from "react";
import type { WeatherCondition } from "../types/weather";

// Shared cloud outline reused by every icon that involves clouds
const CLOUD_PATH = "M6 17.5a4 4 0 0 1 .5-7.97A5.5 5.5 0 0 1 17 9.5a3.5 3.5 0 0 1-.5 8H6z";

const weatherIcons: Record<WeatherCondition, ReactElement> = {
  CLEAR: (
    <svg width="44" height="44" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round">
      <circle cx="12" cy="12" r="4.5" />
      <line x1="12" y1="2.5" x2="12" y2="4.5" />
      <line x1="12" y1="19.5" x2="12" y2="21.5" />
      <line x1="2.5" y1="12" x2="4.5" y2="12" />
      <line x1="19.5" y1="12" x2="21.5" y2="12" />
      <line x1="5.6" y1="5.6" x2="7" y2="7" />
      <line x1="17" y1="17" x2="18.4" y2="18.4" />
      <line x1="5.6" y1="18.4" x2="7" y2="17" />
      <line x1="17" y1="7" x2="18.4" y2="5.6" />
    </svg>
  ),

  PARTLY_CLOUDY: (
    <svg width="44" height="44" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round">
      <circle cx="8" cy="7" r="2.8" />
      <line x1="8" y1="1.8" x2="8" y2="2.8" />
      <line x1="2.8" y1="7" x2="3.8" y2="7" />
      <line x1="4.1" y1="3.1" x2="4.8" y2="3.8" />
      <path d={CLOUD_PATH} />
    </svg>
  ),

  CLOUDY: (
    <svg width="44" height="44" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round">
      <path d={CLOUD_PATH} />
    </svg>
  ),

  DRIZZLE: (
    <svg width="44" height="44" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round">
      <path d={CLOUD_PATH} />
      <line x1="9" y1="19" x2="9" y2="20.5" />
      <line x1="14" y1="19" x2="14" y2="20.5" />
    </svg>
  ),

  RAIN: (
    <svg width="44" height="44" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round">
      <path d={CLOUD_PATH} />
      <line x1="8" y1="19" x2="7" y2="21.5" />
      <line x1="12" y1="19" x2="11" y2="21.5" />
      <line x1="16" y1="19" x2="15" y2="21.5" />
    </svg>
  ),

  THUNDERSTORM: (
    <svg width="44" height="44" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round">
      <path d={CLOUD_PATH} />
      <polyline points="13,18 10.5,21.5 12.5,21.5 11,24" />
    </svg>
  ),

  SNOW: (
    <svg width="44" height="44" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.6" strokeLinecap="round" strokeLinejoin="round">
      <path d={CLOUD_PATH} />
      <line x1="7.5" y1="19" x2="7.5" y2="21.5" />
      <line x1="6.2" y1="19.7" x2="8.8" y2="20.8" />
      <line x1="12" y1="19" x2="12" y2="21.5" />
      <line x1="10.7" y1="19.7" x2="13.3" y2="20.8" />
      <line x1="16.5" y1="19" x2="16.5" y2="21.5" />
      <line x1="15.2" y1="19.7" x2="17.8" y2="20.8" />
    </svg>
  ),

  FOG: (
    <svg width="44" height="44" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round">
      <line x1="4" y1="8" x2="20" y2="8" />
      <line x1="4" y1="12.5" x2="16" y2="12.5" />
      <line x1="4" y1="17" x2="20" y2="17" />
    </svg>
  ),

  UNKNOWN: (
    <svg width="44" height="44" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round">
      <circle cx="12" cy="12" r="9" strokeDasharray="3 3" />
      <circle cx="12" cy="12" r="1.2" fill="currentColor" stroke="none" />
    </svg>
  ),
};

function WeatherIcon({ condition }: { condition: WeatherCondition }) {
  return weatherIcons[condition];
}

export default WeatherIcon;