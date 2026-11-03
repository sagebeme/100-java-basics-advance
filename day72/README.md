# Day 72 - Data Visualisation: Charts as Images

> A table of numbers convinces nobody at a family meeting. A chart does. Today Java draws bar, line and pie charts and saves them as PNG files you can send on WhatsApp.

## 📖 The story

Chebet's report from Day 71 says sales are going UP. Her sister still isn't convinced, so Chebet wants a picture: six bars, January to June, where anyone can see March dip and June tower over it. She'll put it in the family WhatsApp group.

Today's program uses **JFreeChart**, a long-established Java charting library, to:

- make a **bar chart** (comparing months), a **line chart** (a trend over time) and a **pie chart** (shares of a whole);
- keep only the values above a threshold, for a "best months" chart;
- save any chart as a real PNG file.

```
$ mvn -q compile exec:java
Chart saved to .../day72/sales-chart.png (33634 bytes)
```

Run it, open `sales-chart.png`, and you'll see the six months in order.

## 🎯 Learning Objectives
- Add a library with Maven and use its API from its documentation
- Build bar, line and pie charts from a `Map` of labels and values
- Choose the right chart: bars to compare, lines for change over time, pies for parts of a whole
- Keep data in order with a `LinkedHashMap`
- Save a chart as a PNG, and test that the file really is one

## 🧰 What you'll use

**A dataset, then a chart:**

```java
DefaultCategoryDataset dataset = new DefaultCategoryDataset();
for (Map.Entry<String, Double> e : data.entrySet()) {
    dataset.addValue(e.getValue(), "Sales", e.getKey());
}
JFreeChart chart = ChartFactory.createBarChart(title, "Category", "Value", dataset);
ChartUtils.saveChartAsPNG(file, chart, 800, 600);
```

**Order matters.** A chart draws the bars in the order the map gives them. `Map.of(...)` and `HashMap` have no order, so January might come after May. Use a `LinkedHashMap`, which keeps the order you put things in.

**Is it really a PNG?** Every PNG file starts with the same eight bytes: `89 50 4E 47 0D 0A 1A 0A` (the middle three are the letters "PNG"). The test reads the first bytes of the saved file and checks them, which proves more than "a file exists".

**Charts without a screen.** JFreeChart draws into an image in memory, so it works on a server with no display.

## 🪜 Steps

**Project: Chebet's charts** (the `day72/` folder is a finished version: build your own first, then compare).

1. Add `jfreechart` to `pom.xml`.
2. `createBarChart`, `createLineChart` and `createPieChart`, each from a `Map<String, Double>`.
3. `filterAbove(data, threshold)`: keep values at or above the threshold, in the same order.
4. `exportToPng(chart, file, width, height)`.
5. Tests: each chart has its title, the filter, and a saved file that starts with the PNG signature.
6. Improve the look: name the axes "Month" and "KES (thousands)", and use one plain colour instead of the default gradient.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day72
mvn test
mvn -q compile exec:java
```

`sales-chart.png` appears in `day72/`. It's in `.gitignore`, because it can be made again at any time.

## 🆘 Stuck?

- **The months are in a random order**: the data is in a `HashMap` or `Map.of`. Use a `LinkedHashMap`.
- **`HeadlessException` on a server**: something tried to open a window. Save to a file only, or run Java with `-Djava.awt.headless=true`.
- **A pie chart with negative values looks wrong**: a pie shows shares of a whole, so it only makes sense for positive parts that add up to something.
- **The test for the PNG signature fails**: compare bytes as `(byte) 0x89`; in Java a `byte` is signed, so `0x89` alone is an `int`.

## ✅ Checklist
- [ ] Bar, line and pie charts from a map
- [ ] The months stay in order
- [ ] A filter for the best months
- [ ] Charts saved as PNG, and a test that checks the file's signature
- [ ] Labelled the axes
- [ ] Committed code to Git

## ➡️ Next

Day 73 groups and summarises bigger data: totals, averages and counts per category and per region, with streams.
