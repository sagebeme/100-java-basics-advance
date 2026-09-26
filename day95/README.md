# Day 95 - Portfolio Project: API Integration Website

## 📚 Learning Objectives
- Integrate multiple APIs
- Build API-driven website
- Handle API responses
- Create dynamic content
- Build modern web app

## 🎯 Project Requirements
Create API Integration Website:
1. Integrate multiple APIs
2. Display API data
3. Handle errors
4. Beautiful UI
5. Real-time updates

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE; Day 33 for HTTP/APIs; open http://localhost:8080.

## ✅ Checklist
- [ ] Integrated APIs
- [ ] Displays data
- [ ] Handles errors
- [ ] Created UI
- [ ] Completed website
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/DashboardController.java` | `GET /` shows all three live panels |
| `dto/CryptoPrice.java` | One coin's price |
| `dto/IssLocation.java` | Latitude and longitude |
| `dto/WeatherInfo.java` | Weather data |
| `exception/ApiException.java` | Thrown when an API fails, so one broken panel doesn't break the page |
| `service/CryptoService.java` | Coin prices from CoinGecko |
| `service/IssService.java` | Where the International Space Station is right now |
| `service/WeatherService.java` | Nairobi's current weather from Open-Meteo |

Also in `src/main/resources/`: `templates/dashboard.html`.

Needs an internet connection: the panels call three free public APIs (no keys needed). If one is down, only its panel shows an error.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day95
mvn spring-boot:run
```

Then open http://localhost:8080. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day95
mvn test
```

Runs `CryptoServiceTest`, `DashboardControllerTest`, `IssServiceTest` and `WeatherServiceTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 96?** You'll build E-commerce with Payment!
