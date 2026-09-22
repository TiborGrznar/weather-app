# Weather App

Full-stack weather application built as a learning project to practice a realistic production-style architecture: a React + TypeScript frontend paired with a Spring Boot backend that securely proxies the OpenWeatherMap API.

## Why a backend proxy?

The frontend never talks to OpenWeatherMap directly. Any API key embedded in frontend code is publicly visible in the browser (view-source, DevTools network tab), so the Spring Boot backend holds the OpenWeatherMap API key server-side and exposes its own minimal REST endpoint for the frontend to call instead.

```
Browser (React) → Spring Boot backend → OpenWeatherMap API
```

## Tech stack

- **Backend**: Java 21, Spring Boot 4 (Spring MVC, RestClient)
- **Frontend**: React + TypeScript (Vite)
- **Testing**: JUnit 5, Mockito, MockRestServiceServer, MockMvc
- **CI/CD**: GitHub Actions (separate backend and frontend pipelines, build + test on every push/PR to `main`)

## Project structure

```
weather-app/
├── backend/                    Spring Boot API
├── frontend/                   React + TypeScript client
└── .github/workflows/          CI pipelines (backend-ci.yml, frontend-ci.yml)
```

## Backend setup

1. Get a free API key at [openweathermap.org/api](https://openweathermap.org/api)
2. Copy `backend/.env.example` to `backend/.env` and fill in your key:
   ```
   OPENWEATHER_API_KEY=your_key_here
   ```
3. Run the app (IntelliJ Run configuration, or `./mvnw spring-boot:run` from `backend/`)
4. Test it: `http://localhost:8080/api/weather?city=Kosice`

## Frontend setup

1. Copy `frontend/.env.example` to `frontend/.env` (defaults to `http://localhost:8080`, adjust if your backend runs elsewhere):
   ```
   VITE_API_URL=http://localhost:8080
   ```
2. Install dependencies: `npm install` (from `frontend/`)
3. Run the dev server: `npm run dev`
4. Open `http://localhost:5173`

The backend must be running (and CORS-configured for `http://localhost:5173`) for the frontend to fetch weather data.

## API

`GET /api/weather?city={city}`

```json
{
  "city": "Kosice",
  "temperature": 21.5,
  "description": "clear sky"
}
```

If the city isn't found, the API responds with `404` and:
```json
{
  "message": "City not found"
}
```

## Testing

### Backend
```
cd backend
./mvnw test
```
`WeatherServiceTest` mocks the OpenWeatherMap call via `MockRestServiceServer`; `WeatherControllerTest` mocks `WeatherService` via `@MockitoBean` and drives requests through `MockMvc`. Neither test hits the real OpenWeatherMap API.

### Frontend
Not yet implemented — planned (Vitest + React Testing Library).

## CI/CD

Every push/PR to `main` runs two independent GitHub Actions pipelines, each scoped to its own directory so unrelated changes don't trigger unnecessary runs:
- `backend-ci.yml` — builds and tests the backend (triggers on `backend/**` changes)
- `frontend-ci.yml` — lints and builds the frontend (triggers on `frontend/**` changes)

The OpenWeatherMap API key is injected into backend tests as a GitHub repository secret — never committed to source control.

## Status

- [x] Backend: `WeatherService` + `WeatherController`
- [x] Backend unit and integration tests
- [x] CI/CD pipeline (backend + frontend)
- [x] CORS configuration
- [x] Backend global error handling
- [x] Frontend: fetch weather data and display results
- [x] Frontend: error handling and loading state
- [ ] Frontend tests
- [ ] Styling / polish
