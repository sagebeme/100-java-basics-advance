# Day 62 - CSV In and Out: The Group's Contact List

> Every office runs on spreadsheets. Today your app reads one in and hands one back, and survives the names, commas and formulas real people put in them.

## 📖 The story

The Bidii Women's Group in Machakos has 60 members, and the secretary, Mueni, keeps their contacts in Excel. She wants the group's new web app to:

- **import** her spreadsheet, saved as CSV, and tell her which rows it couldn't use and why;
- **export** everyone back as a CSV she can open in Excel or Google Sheets.

Two things make this harder than `split(",")`:

1. **Commas in values.** `Mama Njeri, Kayole` is one name. CSV handles it with quotes: `"Mama Njeri, Kayole"`, and a quote inside a quoted value is written twice: `"Otieno ""Oti"" Omondi"`.
2. **Formulas.** A spreadsheet treats a cell that starts with `=` as a formula. If a member's "name" is `=HYPERLINK("http://bad.example","Click")`, exporting it unchanged puts a working link in Mueni's spreadsheet. That's **CSV injection**. The export puts a `'` in front of anything a spreadsheet would run, so it shows as text, but leaves phone numbers like `+254 712 345678` alone.

```
Imported 57 contacts.
Some rows were not imported:
  • Row 14: name and email are required
  • Row 33: a quote is never closed
```

## 🎯 Learning Objectives
- Send a file download from a controller: `Content-Type` and `Content-Disposition: attachment`
- Read an uploaded CSV with a `BufferedReader`, row by row, counting rows for error messages
- Write and read CSV correctly: quoting, doubled quotes, and a byte-order mark from Excel
- Report bad rows instead of silently dropping or silently importing them
- Defend against CSV (formula) injection
- Test that what you write, you can read back

## 🧰 What you'll use

**A download.** Writing straight to the response, with headers that make the browser save a file:

```java
@GetMapping("/contacts/export")
public void exportContacts(HttpServletResponse response) throws IOException {
    response.setContentType("text/csv; charset=UTF-8");
    response.setHeader("Content-Disposition", "attachment; filename=contacts.csv");
    response.getWriter().print(csvService.toCsv(contacts));
}
```

**One safe cell:**

```java
static String cell(String value) {
    String text = value;
    if (startsLikeAFormula(text) && !looksLikeAPhoneNumber(text)) text = "'" + text;
    if (text.contains(",") || text.contains("\"") || text.contains("\n")) {
        text = "\"" + text.replace("\"", "\"\"") + "\"";
    }
    return text;
}
```

**Reading a line with quotes** means walking it one character at a time and remembering whether you're inside quotes: a comma inside quotes is part of the value, a comma outside ends it. `CsvService.splitLine` does this, and returns `null` when a quote never closes, so that row can be reported.

**A report, not a guess.** `parseCsv` returns an `ImportResult` with the contacts it could read *and* a list of errors with row numbers. The controller passes both to the page as flash attributes.

## 🪜 Steps

**Project: Bidii Contacts** (the `day62/` folder is a finished version: build your own first, then compare).

1. `Contact`: name, email, phone.
2. `CsvService.toCsv`: a header, then one safe line per contact.
3. `CsvService.parseCsv`: skip the header, split each line with quotes in mind, and collect contacts and errors.
4. `ContactController`: `GET /contacts` (the page), `GET /contacts/export` (the download) and `POST /contacts/import` (the upload, then a redirect with what happened).
5. `templates/contacts.html`: the table, the messages, the import form and the export link.
6. Tests: quoting, the formula guard, a full write-then-read round trip, bad rows, an unclosed quote, and the page showing an import's results.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day62
mvn spring-boot:run
```

Open http://localhost:8080/contacts, download the CSV, open it in a spreadsheet, add a row with a comma in the name, and import it back. Stop it with Ctrl+C.

```bash
cd day62
mvn test
```

## 🆘 Stuck?

- **The download opens in the browser instead of saving**: the `Content-Disposition: attachment` header is missing.
- **`Mama Njeri, Kayole` turns into two columns**: the value wasn't quoted on the way out, or the reader splits at every comma on the way in.
- **The first name in Excel's CSV starts with `ï»¿`**: that's the byte-order mark Excel adds to UTF-8 files. Strip `﻿` from the start of the first line.
- **The flash messages never appear**: flash attributes only reach a page you *redirect* to, and the page has to show them.
- **Phone numbers lose their `+`**: your formula guard is too eager. A `+` followed only by digits and spaces is a number, not a formula.

## ✅ Checklist
- [ ] Exported contacts as a CSV download
- [ ] Imported a CSV and reported bad rows by number
- [ ] Commas and quotes survive a round trip
- [ ] A formula is exported as text, and a phone number is exported unchanged
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 63 puts the data in a real database with Spring Data JPA: entities, repositories, and queries written as method names.
