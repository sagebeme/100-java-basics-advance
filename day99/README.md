# Day 99 - Portfolio Project: Advanced Analytics

> Yesterday's dashboard said what happened. Today's says what it means and what's likely next: averages and spread, month-on-month growth, a trend-line forecast, plain-language insights, and a PDF to hand to the boss.

## 📖 The story

Wanjiku from Mauzo (Day 98) liked the dashboard, then asked harder questions. Is the business actually growing, or did May just have a good week? What should she budget for July? And can she get it on one page to show the bank, which wants to see a forecast before it approves a stock loan?

So the same six months of sales (48 sales, January to June 2026) now produce:

- **monthly totals**, their **average** and **standard deviation** (how much a month swings);
- **month-on-month growth**, and its average;
- a **forecast** for the next three months from a straight trend line;
- **insights** in sentences, each one backed by a number;
- a **PDF report** with all of it.

From the bundled data: monthly revenue averages KES 183,956, give or take KES 23,899. The trend adds about KES 9,941 a month, so July, August and September come out at roughly KES 218,749, 228,690 and 238,630. Electronics is 55.0% of revenue and Nairobi 37.9%.

## 🎯 Learning Objectives
- Compute mean, sample standard deviation and growth rates
- Fit a straight trend line by least squares and use it to forecast
- Turn numbers into honest sentences
- Generate a PDF with Apache PDFBox
- Make output the same on every computer, whatever its language settings

## 🧰 What you'll use

**Standard deviation** says how far a typical month is from the average. With only a handful of months, divide by `n - 1` (the *sample* standard deviation), not `n`.

**Growth** from one month to the next is `(this - last) / last × 100`. The first month has nothing to compare with, so it gets no growth figure, rather than a made-up 0%.

**The trend line** is the same least-squares fit as Day 80's model, with one input: the month. Put the months on the x-axis as "months since the first month" and the totals on the y-axis:

```
slope     = Σ (x - x̄)(y - ȳ) / Σ (x - x̄)²
intercept = ȳ - slope × x̄
forecast  = intercept + slope × x
```

Count the months by the calendar (`ChronoUnit.MONTHS.between`), not by their position in the list. If a month had no sales at all, it's missing from the totals, and numbering by position would move every later month one step back and bend the line. A test leaves March out to check this.

A trend line is a guess, not a promise: it can't know about a new competitor or a school-fees January. The report says "projected", and the forecast never goes below zero.

**A PDF with PDFBox:**

```java
try (PDDocument document = new PDDocument()) {
    PDPage page = new PDPage();
    document.addPage(page);
    try (PDPageContentStream text = new PDPageContentStream(document, page)) {
        text.beginText();
        text.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
        text.newLineAtOffset(50, 720);   // from the bottom-left corner
        text.showText("Total revenue: KES 1,103,738");
        text.endText();
    }
    document.save(out);
}
```

**The locale trap.** `"%,.0f".formatted(3000)` gives `3,000` in Kenya, `3.000` in Germany, and `3 000` in France, where the gap is a narrow no-break space. PDFBox's built-in Helvetica can't draw that character, so on a French computer the report crashed. The insights and report format with `Locale.ENGLISH`, and a test switches to French to prove it.

## 🪜 Steps

**Your brief** (the `day99/` folder is one finished version: build your own first, then compare).

1. Reuse Day 98's `SalesRecord` and loader.
2. `AdvancedStatistics`: monthly totals in date order, mean, standard deviation, month-on-month growth.
3. `RevenueForecaster`: fit the trend by calendar month, forecast the next months, never below zero, and refuse to fit fewer than two months.
4. `InsightGenerator`: top category and region with their share, strongest and weakest months, average growth, average and spread. An empty dataset gets one sentence, not an exception.
5. `ReportGenerator`: the PDF. `AnalyticsController`: the page (`GET /`) and the download (`GET /report`).
6. Tests: each calculation with numbers you can check by hand, a December-to-January forecast, a missing month, a French locale, and a PDF read back with `PDFTextStripper`.
7. Make it yours: draw the forecast as a band (the trend plus or minus one standard deviation), forecast each region separately, or add a chart image to the PDF.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day99
mvn test
mvn spring-boot:run
```

Open <http://localhost:8080> to see the actual-versus-forecast chart and the insights, then click **Download Report**.

## 🆘 Stuck?

- **The forecast is a flat line at the average**: the slope's denominator is zero, which happens when every month has the same x. Check you're counting months, not using the same index.
- **The standard deviation is a bit smaller than your calculator's**: you divided by `n`. Use `n - 1`.
- **`U+202F is not available in the font Helvetica`**: a locale's number format reached the PDF. Format with `Locale.ENGLISH`.
- **The PDF is blank**: text was drawn outside `beginText()` and `endText()`, or below y = 0. Coordinates start at the bottom-left.
- **Growth is `Infinity`**: a month with zero revenue was divided by. Skip it.

## ✅ Checklist
- [ ] Mean, standard deviation and growth, tested by hand
- [ ] A trend-line forecast by calendar month
- [ ] Insights that quote real numbers
- [ ] A PDF report that works on any computer
- [ ] Added a forecast band, per-region forecasts or a chart in the PDF
- [ ] Committed code to Git

## ➡️ Next

Day 100, the last one: a model trained on 150 people's pay that predicts monthly earnings from experience, education, location and industry, judged on data it never saw, behind a web form and a JSON API.
