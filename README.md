# Weather App

Full-stack weather application built as a learning project to practice a realistic production-style architecture: a React + TypeScript frontend paired with a Spring Boot backend that securely proxies the OpenWeatherMap API.

## Why a backend proxy?

The frontend never talks to OpenWeatherMap directly. Any API key embedded in frontend code is publicly visible in the browser (view-source, DevTools network tab), so the Spring Boot backend holds the OpenWeatherMap API key server-side and exposes its own minimal REST endpoint for the frontend to call instead.

```
Browser (React) → Spring Boot backend → OpenWeatherMap API
```

## Tech stack

- **Backend**: Java 21, Spring Boot 4 (Spring MVC, RestClient)
- **Frontend**: React + TypeScript (Vite) — in progress
- **Testing**: JUnit 5, Mockito, MockRestServiceServer, MockMvc
- **CI/CD**: GitHub Actions (build + test on every push/PR to `main`)

## Project structure

```
weather-app/
├── backend/                    Spring Boot API
├── frontend/                   React + TypeScript client (WIP)
└── .github/workflows/          CI pipeline
```

## Backend setup

1. Get a free API key at [openweathermap.org/api](https://openweathermap.org/api)
2. Copy `backend/.env.example` to `backend/.env` and fill in your key:
   ```
   OPENWEATHER_API_KEY=your_key_here
   ```
3. Run the app (IntelliJ Run configuration, or `./mvnw spring-boot:run` from `backend/`)
4. Test it: `http://localhost:8080/api/weather?city=Kosice`

## API

`GET /api/weather?city={city}`

```json
{
  "city": "Kosice",
  "temperature": 21.5,
  "description": "clear sky"
}
```

## Testing

```
cd backend
./mvnw test
```

`WeatherServiceTest` mocks the OpenWeatherMap call via `MockRestServiceServer`; `WeatherControllerTest` mocks `WeatherService` via `@MockitoBean` and drives requests through `MockMvc`. Neither test hits the real OpenWeatherMap API.

## CI/CD

Every push/PR to `main` builds and tests the backend via GitHub Actions (`.github/workflows/backend-ci.yml`). The OpenWeatherMap API key is injected as a GitHub repository secret — never committed to source control.

## Status

- [x] Backend: `WeatherService` + `WeatherController`
- [x] Unit and integration tests
- [x] CI/CD pipeline
- [ ] CORS configuration
- [ ] Global error handling
- [ ] Frontend (React + TypeScript)
