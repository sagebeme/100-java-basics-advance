# Day 41 - Mama Njeri's Kibanda: Your First Web Page

> Every web app you'll build from Day 54 on ends as HTML and CSS in a browser. Today, build a page by hand for a roadside food kiosk, then check it with Java.

## 📖 The story

Mama Njeri has sold chapati, mandazi and chai at the Kangemi stage for fifteen years. Her customers keep asking, "Do you have a website?" Today she will. A **kibanda** is a small roadside kiosk, often painted in bright reds, yellows and greens so you can spot it from the matatu. The page should feel like that too:

- a painted sign at the top, with the name in big, bold letters
- the menu on a chalkboard, as a real table with prices
- opening hours, directions, and an order form

Open `_end/src/index.html` in your browser to see the finished page, on a computer and on your phone.

Because this is a Java course, you'll also run a **Java program that checks the page**: `PageCheck` uses the jsoup library (in `lib/`) to read your HTML and spot the mistakes that make websites hard to use.

## 🎯 Learning Objectives
- Write a complete HTML document: `<!DOCTYPE>`, `<html lang>`, `<head>` and `<body>`
- Choose elements by meaning: headings, paragraphs, lists, tables, links, images and forms
- Label every form field so everyone can use it, including people using screen readers
- Style a page with an external CSS file: selectors, colours, fonts, spacing and borders
- Name colours once with CSS custom properties (`--red`) and reuse them with `var()`
- Make the layout adapt to wide screens with one `@media` rule
- Check HTML from Java with jsoup

## 🧰 What you'll use

**The skeleton every page needs**:

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mama Njeri's Kibanda · Kangemi</title>
    <link rel="stylesheet" href="style.css">
</head>
<body> ... </body>
</html>
```

**Elements say what something *is***: `<h1>` is the page's main heading (only one), `<h2>` a section heading, `<ul>`/`<ol>` lists, `<table>` rows and columns of data, `<a href>` a link, `<img alt>` a picture *with a description*, and `<form>` with `<label for="x">` + `<input id="x">` pairs.

**CSS says how it looks.** A rule is a selector and some declarations:

```css
:root { --red: #d7263d; }               /* name a colour once */
.sign { background: var(--red); }       /* elements with class="sign" */
nav a:hover { color: var(--yellow); }   /* links in the nav, when hovered */
@media (min-width: 52rem) {             /* only on wide screens */
    main { grid-template-columns: 1fr 1fr; }
}
```

**Fonts.** The finished page uses two free Google Fonts: *Bungee*, which looks like a painted sign, and *Nunito*, which is easy to read. Link them in the `<head>`:

```html
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Bungee&family=Nunito:wght@400;700;900&display=swap">
```

**jsoup** reads HTML in Java the way a browser does, and finds elements with CSS selectors:

```java
Document page = Jsoup.parse(html);
page.title();                       // the <title> text
page.select("img:not([alt])");      // every image without alt text
```

## 🪜 Steps

1. **Exercise 1: A recipe page** (`exercise1/`). Headings, lists, an image and emphasis, checked by a test.
2. **Exercise 2: Style a matatu ticket** (`exercise2/`). CSS only: variables, borders, shadows, fonts and a rotated stamp.
3. **Project: Mama Njeri's Kibanda** (`_start/src/`).
   1. Fill in `index.html` section by section: the sign and nav, the menu table, the hours, the directions and the order form.
   2. Run `PageCheck` after each section and fix what it reports.
   3. Style it in `style.css`: the painted sign, the chalkboard menu, the cards and the form.
   4. Add one `@media` rule for wide screens, then check the page at phone width (your browser's developer tools can pretend to be a phone).

## 🧪 Run it and test it

Open `index.html` in a browser for the page itself. For the checks, from the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day41/_end/src day41/_end/src/*.java
cd day41/_end/src && java -cp ".:../../../lib/*" PageCheck index.html && cd ../../..
java -cp "day41/_end/src:lib/*" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
```

## 🆘 Stuck?

- **The CSS does nothing**: check the `<link href="style.css">` path, and refresh with Ctrl+Shift+R so the browser doesn't use an old copy.
- **A link in the nav doesn't jump anywhere**: `href="#menu"` needs an element with `id="menu"`. `PageCheck` reports these.
- **The text is tiny on a phone**: you're missing the viewport `<meta>` tag.
- **The page is fine on a laptop but squashed on a phone**: put two-column layouts inside a `@media (min-width: ...)` rule, so phones get one column.

## ✅ Checklist
- [ ] Wrote a complete HTML page with one `<h1>`
- [ ] Used a table for the menu and a form with a label for every field
- [ ] Styled it from an external CSS file, using custom properties
- [ ] Made it look good on a phone and on a wide screen
- [ ] `PageCheck` reports no problems
- [ ] Finished both exercises
- [ ] Committed code to Git

## ➡️ Next

Day 42 goes further with layout: flexbox, CSS grid and responsive design, in a portfolio for a kanga designer.
