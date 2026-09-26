# Day 72 - Data Visualization

## 📚 Learning Objectives
- Create data visualizations
- Use charting libraries
- Generate graphs
- Display data visually
- Build dashboards

## 🎯 Topics Covered
- Chart libraries (JFreeChart)
- Graph generation
- Data visualization
- Dashboard creation
- Interactive charts

## 📝 Step-by-Step Instructions

### Step 1: Create Charts
Generate charts:

```java
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;

public class ChartGenerator {
    public JFreeChart createBarChart(String title, Dataset dataset) {
        return ChartFactory.createBarChart(
            title, "Category", "Value", dataset
        );
    }
}
```

## 🎮 Project: Data Visualization Dashboard

### Requirements
Create dashboard with:
1. Multiple chart types
2. Interactive visualizations
3. Data filtering
4. Export capabilities

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** Run from IDE or `mvn exec:java`.

## ✅ Checklist
- [ ] Can create charts
- [ ] Can visualize data
- [ ] Can build dashboards
- [ ] Completed visualization project
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `ChartGenerator.java` | Bar, line and pie charts with JFreeChart, a filter for values above a threshold, and PNG export |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day72
mvn compile exec:java
```

Or run the main class from your IDE.

## 🧪 How to Test

```bash
cd day72
mvn test
```

Runs `ChartGeneratorTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 73?** You'll learn aggregate data operations!
