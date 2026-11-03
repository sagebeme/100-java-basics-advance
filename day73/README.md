# Day 73 - Grouping and Summarising: Totals per Category

> A list of 10,000 sales tells you nothing. "Electronics made 8,000, mostly in Nairobi" tells you where to put your stock. Today streams group, sum, average and count for you.

## 📖 The story

Wanjiru manages a small chain of shops in Nairobi, Mombasa and Kisumu that sell electronics, groceries and clothes. At the end of the week head office sends her every sale as one long list:

```java
new Sale("Electronics", "Nairobi", 5000)
new Sale("Electronics", "Mombasa", 3000)
new Sale("Groceries",   "Nairobi", 1200)
new Sale("Groceries",   "Kisumu",   800)
new Sale("Clothing",    "Nairobi", 2000)
```

Her boss asks the questions bosses ask: which category brings in the most? What's an average electronics sale? How much did groceries make in each town? Today's `SalesAggregator` answers all of them, and prints a summary:

```
Sales Summary
Clothing: total=2000.00, average=2000.00, count=1
Electronics: total=8000.00, average=4000.00, count=2
Groceries: total=2000.00, average=1000.00, count=2
Top category: Electronics
```

## 🎯 Learning Objectives
- Group a list into a map with `Collectors.groupingBy`
- Summarise each group with a second collector: `summingDouble`, `averagingDouble`, `counting`
- Group in two levels: by category, then by region inside each category
- Find the biggest entry in a map with `max` and a `Comparator`
- Print results in a fixed order, even though a `HashMap` has none

## 🧰 What you'll use

**`groupingBy` on its own** collects each group into a list:

```java
Map<String, List<Sale>> byCategory =
        sales.stream().collect(Collectors.groupingBy(Sale::category));
```

**With a second collector** it summarises each group instead:

```java
Collectors.groupingBy(Sale::category, Collectors.summingDouble(Sale::amount))   // totals
Collectors.groupingBy(Sale::category, Collectors.averagingDouble(Sale::amount)) // averages
Collectors.groupingBy(Sale::category, Collectors.counting())                    // how many (a Long)
```

**Two levels** is a `groupingBy` inside a `groupingBy`, which gives a map of maps:

```java
Map<String, Map<String, Double>> totals = sales.stream().collect(Collectors.groupingBy(
        Sale::category,
        Collectors.groupingBy(Sale::region, Collectors.summingDouble(Sale::amount))));

totals.get("Groceries").get("Kisumu");   // 800.0
```

**The biggest entry:**

```java
totals.entrySet().stream()
        .max(Comparator.comparingDouble(Map.Entry::getValue))
        .map(Map.Entry::getKey)
        .orElse(null);                    // no sales, no top category
```

**Order.** `groupingBy` returns a `HashMap`, so printing it can come out in any order. The summary sorts the category names first, which keeps it the same every run (and makes it testable).

## 🪜 Steps

**Project: Wanjiru's weekly summary** (the `day73/` folder is a finished version: build your own first, then compare).

1. A `Sale(category, region, amount)` record.
2. `groupByCategory`, `totalsByCategory`, `averagesByCategory` and `countByCategory`.
3. `totalsByCategoryAndRegion`: the two-level map.
4. `topCategoryByTotal`, and `generateSummary` in alphabetical order.
5. Tests for each one with the five sales above, worked out by hand first.
6. Add `topRegion()`, and a summary line per region.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day73
mvn test
mvn -q compile exec:java
```

## 🆘 Stuck?

- **`Long` vs `Integer` in a test**: `counting()` gives a `Long`. Compare with `2L`, not `2`.
- **The summary's order changes between runs**: you printed the `HashMap` directly. Sort the keys, or collect into a `TreeMap` with `groupingBy(key, TreeMap::new, downstream)`.
- **`NullPointerException` on `totals.get("Toys")`**: a category with no sales isn't in the map at all. Use `getOrDefault("Toys", 0.0)`.
- **Two categories tie for the top**: `max` returns one of them. If ties matter, say which one wins (for example, alphabetically first) and test that.

## ✅ Checklist
- [ ] Grouped sales by category
- [ ] Totals, averages and counts per category
- [ ] Totals per category and region
- [ ] The top category, and a summary in a fixed order
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 74 adds time: daily and monthly totals, moving averages, and a trend line through the data.
