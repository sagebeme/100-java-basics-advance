# Day 74 - Time Series: Smoothing the Bumps and Finding the Trend

> Matatu fares go up on rainy days, down on Sundays, and up again at month end. Somewhere under the noise there's a trend. Today you find it with moving averages and a line of best fit.

## 📖 The story

Juma runs a boda boda and writes down what he earns every day. Some days are great (a wedding, rain), some are terrible (a puncture). He wants to know if business is actually growing, and whether to take a loan for a second bike. Five days of his notebook (in shillings):

```
1 Jan  100    2 Jan  110    3 Jan  105    4 Jan  120    5 Jan  130
```

Day by day it jumps about. Today's `TimeSeriesAnalyzer` does what an analyst would:

- **groups** readings by date (averaging two notes on the same day) and by month (totalling them);
- works out a **3-day moving average**, which smooths out single good or bad days;
- fits a **straight line** through the points and reports its slope: how much he gains per day, on average.

```
3-day moving average: [105.0, 111.66666666666667, 118.33333333333333]
Linear trend slope: 7.00 per day
```

The smoothed line rises steadily, and the trend says about KES 7 more each day. The loan looks less scary.

## 🎯 Learning Objectives
- Represent a reading with `LocalDate` and a value
- Group by day, and by month with `YearMonth`
- Calculate a moving average, and know why it starts later than the data
- Fit a least-squares line and read its slope
- Refuse inputs that make no sense, like a window of 0 days

## 🧰 What you'll use

**Dates as keys:**

```java
Collectors.groupingBy(DataPoint::date, Collectors.averagingDouble(DataPoint::value));
Collectors.groupingBy(p -> YearMonth.from(p.date()), Collectors.summingDouble(DataPoint::value));
```

`YearMonth` is "January 2026" with no day, which is exactly the key for a monthly total.

**A moving average.** For a window of 3, each value is the mean of that day and the two days before it. The first two days don't have two days before them, so there is no value for them: five days give three averages. Padding them with a partial average would make the start look smoother than it is.

```
days:      100  110  105  120  130
window:   [100  110  105]                 -> 105.0
               [110  105  120]            -> 111.67
                    [105  120  130]       -> 118.33
```

**A line of best fit.** With `x` = days since the first reading and `y` = the value, the least-squares slope is:

```
slope = (n·Σxy − Σx·Σy) / (n·Σx² − (Σx)²)
```

`ChronoUnit.DAYS.between(start, date)` gives `x`, so a missing day in the notebook still counts as a day of time.

## 🪜 Steps

**Project: Juma's notebook** (the `day74/` folder is a finished version: build your own first, then compare).

1. A `DataPoint(LocalDate date, double value)` record.
2. `aggregateByDate` and `aggregateByMonth`.
3. `movingAverage(points, windowSize)`: one value per full window, and an `IllegalArgumentException` for a window under 1.
4. `linearTrendSlope(points)`: zero for fewer than two points.
5. Tests: two readings on one day, a month's total, the moving average above, a window bigger than the data, and slopes that go up, down and flat.
6. Use the slope to predict day 10, and print it with a warning that it's only a straight line.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day74
mvn test
mvn -q compile exec:java
```

## 🆘 Stuck?

- **`IndexOutOfBoundsException` in the moving average**: the first full window ends at index `windowSize - 1`. Start the outer loop there.
- **The slope is huge or `NaN`**: every reading is on the same date, so all `x` are 0 and the bottom of the fraction is 0. Return 0 when it is.
- **Months come out of order**: `groupingBy` gives a `HashMap`. Collect into a `TreeMap` (`YearMonth` sorts by time).
- **The slope ignores a gap in the data**: use the real number of days between dates, not the position in the list.

## ✅ Checklist
- [ ] Grouped readings by day and by month
- [ ] A moving average that only uses full windows, and refuses a bad window size
- [ ] A trend slope, tested going up, down and flat
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 75 puts it all on one dashboard: several chart types from one dataset, filtered and exported together.
