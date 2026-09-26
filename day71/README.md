# Day 71 - Data Analysis with Java

## 📚 Learning Objectives
- Perform data analysis with Java
- Work with data libraries
- Analyze datasets
- Calculate statistics
- Process large datasets

## 🎯 Topics Covered
- Data processing
- Statistical analysis
- Data libraries (Apache Commons Math)
- CSV/JSON processing
- Data aggregation
- Calculations

## 📝 Step-by-Step Instructions

### Step 1: Load Data
Load data for analysis:

```java
public class DataAnalyzer {
    public List<DataPoint> loadData(String filename) {
        // Load from CSV/JSON
        // Return data points
    }
}
```

### Step 2: Calculate Statistics
Perform calculations:

```java
public Statistics calculateStats(List<Double> values) {
    double mean = values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    double max = Collections.max(values);
    double min = Collections.min(values);
    // Calculate more statistics
    return new Statistics(mean, max, min);
}
```

## 🎮 Project: Sales Data Analysis

### Requirements
Analyze sales data:
1. Load sales data
2. Calculate statistics
3. Find trends
4. Generate reports

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** Run from IDE or `mvn exec:java` for Java data analysis.

## ✅ Checklist
- [ ] Can load data
- [ ] Can calculate statistics
- [ ] Can analyze trends
- [ ] Can generate reports
- [ ] Completed data analysis
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `DataAnalyzer.java` | Loads month,amount CSV data, calculates statistics, finds the trend and prints a report |
| `SaleRecord.java` | One month's sales |
| `Statistics.java` | Mean, median, min, max and standard deviation |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day71
mvn compile exec:java
```

Or run the main class from your IDE.

## 🧪 How to Test

```bash
cd day71
mvn test
```

Runs `DataAnalyzerTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 72?** You'll learn data visualization!
