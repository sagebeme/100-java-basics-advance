# Day 98 - Portfolio Project: Data Analysis Dashboard

> A spreadsheet of sales is just numbers until someone can see which shop, which month and which kind of product is carrying the business. Today's dashboard turns six months of sales into totals, filters, charts and a download.

## 📖 The story

Mauzo is a small chain with shops in Nairobi, Mombasa, Kisumu and Eldoret, selling electronics, clothing, home and kitchen goods, and sports gear. Every Monday the owner, Wanjiku, asks the same questions: how much did we sell, where, and is it going up? Answering means an afternoon in Excel.

She wants a page that answers them at a glance:

- **one number** at the top: total revenue in shillings, and how many sales it comes from;
- **three charts**: revenue by category, by region, and month by month;
- **filters**: show only Electronics, or only Mombasa, or both, and every number and chart follows;
- **export**: download exactly what's on screen as CSV (for Excel) or JSON (for another program);
- **auto-refresh** every 30 seconds, so it can sit on the screen in the office.

The data is `sales-data.csv`: 48 sales from January to June 2026, in whole shillings. Electronics is the big earner (KES 607,511 of KES 1,103,738).

## 🎯 Learning Objectives
- Load a CSV file from the classpath into records
- Group and sum with streams: `groupingBy` with `summingDouble`
- Keep chart keys in a sensible order (alphabetical, or by month)
- Pass Java data to Chart.js safely with Thymeleaf's JavaScript inlining
- Export the filtered data as CSV and JSON that other programs can read on any computer
- Load CDN scripts with an `integrity` hash

## 🧰 What you'll use

**Grouping and summing:**

```java
Map<String, Double> byCategory = new TreeMap<>(records.stream()
        .collect(Collectors.groupingBy(SalesRecord::category,
                 Collectors.summingDouble(SalesRecord::amount))));
```

`groupingBy` returns a `HashMap`, whose order is random, so the chart's bars would shuffle. A `TreeMap` sorts the keys. Months are grouped by `yyyy-MM` text (`2026-01`), which sorts in date order as plain text.

**Java data into JavaScript:**

```html
<script th:inline="javascript">
    const categoryData = /*[[${revenueByCategory}]]*/ {};
</script>
```

Thymeleaf writes the map as a JavaScript object and escapes it, so a category called `</script>` can't break out of the script. The `{}` after the comment is what you see if you open the template straight from disk.

**Exports other programs can read.** Two traps:

- `"%.2f".formatted(66150.0)` gives `66150,00` on a computer set to German or French. In a CSV that comma starts a new column, and in JSON it's a syntax error. The exporter uses `BigDecimal` (always a dot) and a test switches the default locale to prove it.
- Building JSON by gluing strings breaks as soon as a value contains a `"`. The exporter builds it with Jackson's `JsonMapper`, which escapes for you, and wraps CSV values that contain commas or quotes in quotes.

**`Content-Disposition: attachment; filename="sales-data.csv"`** makes the browser download the response instead of showing it.

**Subresource integrity.** Bootstrap and Chart.js come from a CDN. The `integrity="sha384-..."` attribute is a fingerprint of the exact file: if the CDN ever serves something else, the browser refuses to run it.

## 🪜 Steps

**Your brief** (the `day98/` folder is one finished version: build your own first, then compare).

1. `SalesRecord(date, category, region, amount)` and `SalesDataLoader`, which reads the CSV and skips blank lines.
2. `DashboardService`: filter by category and region (blank means "all"), the total, revenue by category, region and month, and the lists for the filter dropdowns.
3. `DashboardExporter`: CSV and JSON, locale-safe and escaped.
4. `DashboardController`: `GET /` with the filters, and `GET /export?format=csv|json` with the same filters.
5. `dashboard.html`: the total and count, the filter form, three Chart.js charts, export links that keep the current filters, and `<meta http-equiv="refresh" content="30">`.
6. Tests: loading, filtering and grouping, both export formats (including a German locale and a value with a quote), and the page and downloads through MockMvc.
7. Make it yours: load your own sales (or M-Pesa statement) CSV through an upload form, add a date-range filter, or show this month against last month as a percentage.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day98
mvn test
mvn spring-boot:run
```

Open <http://localhost:8080>, pick Electronics and Mombasa, then click **Export CSV** and open the file in a spreadsheet.

## 🆘 Stuck?

- **The bars change order on every refresh**: you're using the `HashMap` from `groupingBy`. Wrap it in a `TreeMap`.
- **Months come out as January, April, February...**: group by `yyyy-MM`, not by month name.
- **The CSV opens in one column, or with amounts split in two**: a decimal comma from the locale. Format with `BigDecimal` or `Locale.ROOT`.
- **The charts are empty and the console says `Chart is not defined`**: Chart.js didn't load. Check the URL, and that the `integrity` hash belongs to that exact version.
- **The refresh resets my filters**: it doesn't if the filters are in the URL (`?category=...`), which is why the form uses `GET`.

## ✅ Checklist
- [ ] CSV loaded into records
- [ ] Totals and three charts that follow the filters
- [ ] CSV and JSON exports that work on any computer
- [ ] CDN files loaded with integrity hashes
- [ ] Auto-refresh that keeps the filters
- [ ] Added an upload, a date range or a month-on-month comparison
- [ ] Committed code to Git

## ➡️ Next

Day 99 goes past totals: averages, spread and trends, a simple revenue forecast, and plain-language insights in a downloadable report.
