# Day 74 - Time Series Analysis

## 📚 Learning Objectives
- Analyze time series data
- Work with temporal data
- Calculate time-based metrics
- Identify trends over time
- Process time series

## 🎯 Topics Covered
- Time series data
- Temporal analysis
- Trend analysis
- Date/time processing
- Time-based aggregations

## 📝 Step-by-Step Instructions

### Step 1: Process Time Series
Handle temporal data:

```java
public class TimeSeriesAnalyzer {
    public Map<LocalDate, Double> aggregateByDate(List<DataPoint> points) {
        return points.stream()
            .collect(Collectors.groupingBy(
                DataPoint::getDate,
                Collectors.averagingDouble(DataPoint::getValue)
            ));
    }
}
```

## 🎮 Project: Time Series Analyzer

### Requirements
Create time series analyzer:
1. Load time series data
2. Aggregate by time periods
3. Calculate trends
4. Visualize over time

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** Run from IDE or `mvn exec:java`.

## ✅ Checklist
- [ ] Can process time series
- [ ] Can aggregate by time
- [ ] Can calculate trends
- [ ] Completed time series analyzer
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `DataPoint.java` | A date and a value |
| `TimeSeriesAnalyzer.java` | Aggregates by day and month, moving averages, and a linear trend slope |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day74
mvn compile exec:java
```

Or run the main class from your IDE.

## 🧪 How to Test

```bash
cd day74
mvn test
```

Runs `TimeSeriesAnalyzerTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 75?** You'll create charts and data visualization!
