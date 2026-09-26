# Day 92 - Portfolio Project: Web Scraper

## 📚 Learning Objectives
- Build web scraper
- Extract data from websites
- Handle different sites
- Save scraped data
- Build data collection tool

## 🎯 Project Requirements
Create Web Scraper:
1. Scrape websites
2. Extract data
3. Save to file
4. Handle errors
5. User-friendly interface

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** Jsoup for scraping; run from IDE or `mvn exec:java`; see Day 33 for HTTP.

## ✅ Checklist
- [ ] Can scrape websites
- [ ] Can extract data
- [ ] Can save data
- [ ] Handles errors
- [ ] Completed scraper
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `CsvSaver.java` | Saves the items as CSV |
| `ScrapedItem.java` | One scraped item |
| `ScraperApp.java` | The command-line app |
| `WebScraper.java` | Fetches a page with Jsoup and extracts the items (extraction is tested on saved HTML) |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day92
mvn compile exec:java
```

Or run the main class from your IDE.

## 🧪 How to Test

```bash
cd day92
mvn test
```

Runs `CsvSaverTest`, `LiveFetchTest` and `WebScraperTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 93?** You'll build Game Automation!
