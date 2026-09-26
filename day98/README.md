# Day 98 - Portfolio Project: Data Analysis Dashboard

## 📚 Learning Objectives
- Build analytics dashboard
- Visualize data
- Create interactive charts
- Process large datasets
- Build professional dashboard

## 🎯 Project Requirements
Create Data Dashboard:
1. Load data
2. Multiple chart types
3. Interactive filters
4. Real-time updates
5. Export functionality

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE; charts (Day 72–75); open http://localhost:8080.

## ✅ Checklist
- [ ] Created dashboard
- [ ] Added charts
- [ ] Added filters
- [ ] Can export
- [ ] Completed dashboard
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/DashboardController.java` | `GET /` shows the dashboard with filters, `GET /export` downloads CSV or JSON |
| `model/SalesRecord.java` | One sale |
| `service/DashboardExporter.java` | Writes the filtered data as CSV or JSON |
| `service/DashboardService.java` | Filters, and revenue by category, region and month |
| `service/SalesDataLoader.java` | Loads `sales-data.csv` |

Also in `src/main/resources/`: `sales-data.csv`, `templates/dashboard.html`.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day98
mvn spring-boot:run
```

Then open http://localhost:8080. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day98
mvn test
```

Runs `DashboardControllerTest`, `DashboardExporterTest`, `DashboardServiceTest` and `SalesDataLoaderTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 99?** You'll build Advanced Analytics!
