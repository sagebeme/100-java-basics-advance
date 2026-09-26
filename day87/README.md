# Day 87 - Portfolio Project: Cafe Finder Website

## 📚 Learning Objectives
- Build location-based app
- Integrate maps API
- Search functionality
- Display locations
- Create useful application

## 🎯 Project Requirements
Create Cafe Finder:
1. Search cafes by location
2. Display on map
3. Show cafe details
4. Filter options
5. Responsive design

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE; Maps API (Day 33); see Day 54–59.

## ✅ Checklist
- [ ] Integrated maps API
- [ ] Added search
- [ ] Created UI
- [ ] Added filters
- [ ] Completed website
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `config/DataSeeder.java` | Adds sample cafes when the app starts |
| `controller/CafeController.java` | `GET /` searches cafes with filters |
| `model/Cafe.java` | A JPA entity |
| `model/PriceLevel.java` | Price bands, shown as $ to $$$ |
| `repository/CafeRepository.java` | Spring Data repository |
| `service/CafeService.java` | Searches by name or address, with minimum rating, Wi-Fi and maximum price filters |

Also in `src/main/resources/`: `templates/index.html`.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day87
mvn spring-boot:run
```

Then open http://localhost:8080. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day87
mvn test
```

Runs `CafeControllerTest`, `CafeRepositoryTest` and `CafeServiceTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 88?** You'll build a Todo List Application!
