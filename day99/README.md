# Day 99 - Portfolio Project: Advanced Analytics

## 📚 Learning Objectives
- Build advanced analytics system
- Implement complex calculations
- Create predictive models
- Generate insights
- Build analytics platform

## 🎯 Project Requirements
Create Advanced Analytics:
1. Advanced calculations
2. Predictive analytics
3. Data visualization
4. Report generation
5. Insights dashboard

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** Run from IDE or `mvn spring-boot:run` for web; see Days 71–75 for data/visualization.

## ✅ Checklist
- [ ] Implemented analytics
- [ ] Added predictions
- [ ] Created visualizations
- [ ] Generates reports
- [ ] Completed analytics
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/AnalyticsController.java` | `GET /` shows the analysis, `GET /report` downloads a PDF report |
| `model/SalesRecord.java` | One sale |
| `service/AdvancedStatistics.java` | Monthly totals, mean, standard deviation, month-on-month growth |
| `service/AnalyticsService.java` | Loads the data once |
| `service/InsightGenerator.java` | Plain-language insights, each backed by a real number |
| `service/ReportGenerator.java` | Builds the PDF with Apache PDFBox |
| `service/RevenueForecaster.java` | Fits a least-squares trend line and forecasts the next months |
| `service/SalesDataLoader.java` | Loads `sales-data.csv` |

Also in `src/main/resources/`: `sales-data.csv`, `templates/dashboard.html`.

The PDF report is made with Apache PDFBox.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day99
mvn spring-boot:run
```

Then open http://localhost:8080. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day99
mvn test
```

Runs `AdvancedStatisticsTest`, `AnalyticsControllerTest`, `InsightGeneratorTest`, `ReportGeneratorTest` and `RevenueForecasterTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 100?** Final project - Machine Learning Integration!

**Congratulations on reaching Day 99!** 🎉
