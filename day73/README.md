# Day 73 - Aggregate Data Operations

## 📚 Learning Objectives
- Perform aggregate operations
- Group and summarize data
- Use Stream API aggregations
- Calculate aggregations
- Process grouped data

## 🎯 Topics Covered
- Grouping operations
- Aggregation functions
- Stream API
- Data summarization
- Statistical aggregations

## 📝 Step-by-Step Instructions

### Step 1: Group Data
Group data by category:

```java
Map<String, List<Sale>> grouped = sales.stream()
    .collect(Collectors.groupingBy(Sale::getCategory));
```

### Step 2: Aggregate Values
Calculate aggregations:

```java
Map<String, Double> totals = sales.stream()
    .collect(Collectors.groupingBy(
        Sale::getCategory,
        Collectors.summingDouble(Sale::getAmount)
    ));
```

## 🎮 Project: Sales Aggregation System

### Requirements
Create aggregation system:
1. Group data
2. Calculate totals
3. Find averages
4. Generate summaries

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** Run from IDE or `mvn exec:java`.

## ✅ Checklist
- [ ] Can group data
- [ ] Can aggregate values
- [ ] Can calculate statistics
- [ ] Completed aggregation system
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Sale.java` | One sale: category, region and amount |
| `SalesAggregator.java` | Groups, totals, averages and counts sales by category, and by category and region |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day73
mvn compile exec:java
```

Or run the main class from your IDE.

## 🧪 How to Test

```bash
cd day73
mvn test
```

Runs `SalesAggregatorTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 74?** You'll learn time series analysis!
