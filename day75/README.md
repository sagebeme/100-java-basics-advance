# Day 75 - Dashboard: One Dataset, Four Charts

> A dashboard is one set of numbers seen several ways at once. Today one `Dashboard` object holds the data, filters it, and exports bar, line, pie and area charts in a single call.

## 📖 The story

Chebet (Day 72) liked her chart so much that her chama wants one for their monthly contributions. But the treasurer wants bars, the chair wants a line, and someone always asks "what share was each month?", which is a pie. Making four charts by hand every month isn't going to happen.

So today's `Dashboard` takes the data once and does the rest:

```
$ mvn -q compile exec:java
bar: .../day75/dashboard-charts/bar.png (27697 bytes)
line: .../day75/dashboard-charts/line.png (23269 bytes)
pie: .../day75/dashboard-charts/pie.png (30491 bytes)
area: .../day75/dashboard-charts/area.png (27870 bytes)
```

(Your byte counts will differ a little.) And `filterAbove(1000)` narrows the dashboard to the good months, after which every chart shows only those.

## 🎯 Learning Objectives
- Keep the data in one object and draw several views of it
- Build bar, line, pie and area charts with JFreeChart
- Change what the dashboard shows by filtering its data, keeping the order
- Export a set of files in one call, and create the folder if it's missing
- Test generated files by their contents, using JUnit's `@TempDir`

## 🧰 What you'll use

**One object, many views.** The `Dashboard` holds a `Map<String, Double>`, and each chart method builds its dataset from that map. Filter the map and every chart follows:

```java
Dashboard dashboard = new Dashboard(contributions);
dashboard.filterAbove(1000);
dashboard.exportAll(new File("dashboard-charts"), "Contributions");
```

**An area chart** is a line chart with the space under it filled in. It's good for amounts that build up over time.

**Folders.** `mkdirs()` creates a folder and any missing parents, and returns `false` if it couldn't. Turn that into an `IOException`, so a failure doesn't look like success.

**`@TempDir`** gives each test a fresh empty folder, and deletes it afterwards, so tests never leave PNGs lying around:

```java
@Test
void exportAllProducesFourRealPngFiles(@TempDir File tempDir) throws IOException {
    Map<String, File> files = dashboard.exportAll(tempDir, "Sales");
    // ...check each file starts with the PNG signature
}
```

## 🪜 Steps

**Project: The chama dashboard** (the `day75/` folder is a finished version: build your own first, then compare).

1. `Dashboard(Map<String, Double> data)`, using a `LinkedHashMap` so the months stay in order.
2. `bar`, `line`, `pie` and `area`, each taking a title.
3. `filterAbove(threshold)`: keep values at or above it, in order.
4. `exportAll(directory, baseTitle)`: four PNGs, named by chart type, and their files returned.
5. Tests: filtering, each chart's title, four real PNGs, and exporting after filtering.
6. Add `resetFilter()`, which brings back all the data. (Hint: keep the original map as well as the filtered one.)

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day75
mvn test
mvn -q compile exec:java
```

The charts go into `day75/dashboard-charts/`, which is in `.gitignore`.

## 🆘 Stuck?

- **The filter can't be undone**: `filterAbove` replaced the data. Keep the full map separately and filter from it.
- **`FileNotFoundException` when saving**: the folder doesn't exist. Call `mkdirs()` first.
- **A test passes even though no chart was saved**: check the file's first bytes, not just that a `File` object exists.
- **Pie slices are tiny and unreadable**: too many categories. Show the top few and group the rest as "Other".

## ✅ Checklist
- [ ] One dashboard object, four chart types
- [ ] A filter that keeps the order
- [ ] All four charts exported in one call
- [ ] Tests that use `@TempDir` and check real PNG files
- [ ] Added a way to reset the filter
- [ ] Committed code to Git

## ➡️ Next

Day 76 builds data structures yourself: a stack, a queue and a binary search tree.
