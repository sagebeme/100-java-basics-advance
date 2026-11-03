# Day 65 - Web Design Done Properly: The Films Site, Rebuilt to Last

> Yesterday's films site works. Today it gets the things users notice without knowing why: it reads well on a phone, a screen reader can describe it, every field has a label, and it shows up fast.

## 📖 The story

Njeri's friend Otieno is blind and uses a screen reader. When he opened the Day 64 site, his phone read out "list, ten items" with no idea what list, and the form's fields as "edit text, edit text, edit text, edit text". He couldn't tell the title box from the year box. On a cheap phone on slow data, the page sat blank while Bootstrap's script downloaded.

Today you rebuild the same site, with the same Java code underneath, so that it works for everyone:

- **semantic HTML**: `<header>` and `<main>` instead of anonymous `<div>`s, and an `<ol>` because the order of the films means something;
- an **accessible name** for the list: `aria-label="Top ten ranked movies"`;
- a `<label for="...">` joined to **every** form field;
- a **viewport** tag and a Bootstrap grid, so the form stacks neatly on a phone;
- the script loaded with **`defer`**, so the page appears before the script has finished downloading.

`MovieControllerTest` checks each one of these in the page the server actually sends.

## 🎯 Learning Objectives
- Use semantic elements (`header`, `main`, `nav`, `ol`) so the page's structure is real, not just visual
- Give every form control a `<label>` joined with `for` and `id`
- Add accessible names with `aria-label` only where visible text isn't enough
- Make a layout respond to screen width with the viewport tag and a grid
- Load scripts without blocking the page, using `defer`
- Test the HTML your server sends, not just your Java

## 🧰 What you'll use

**Structure that means something:**

```html
<header class="container mt-4">
    <h1>My Top 10 Movies</h1>
</header>
<main class="container">
    <ol class="list-group" aria-label="Top ten ranked movies">
        <li class="list-group-item" th:each="movie : ${movies}">...</li>
    </ol>
</main>
```

A screen reader user can jump straight to `main`, and hears "Top ten ranked movies, list, 10 items".

**Labels joined to fields.** Clicking the label focuses the field, and a screen reader reads it out. A `placeholder` is not a label: it disappears as soon as you type.

```html
<label for="year" class="form-label">Year</label>
<input type="number" id="year" th:field="*{year}" class="form-control" required>
```

**`defer`.** A normal `<script>` stops the page while it downloads and runs. `defer` downloads it alongside and runs it after the page is ready, which matters most on slow connections.

**Testing the page.** MockMvc returns the real HTML, so a test can check `containsString("<main")` or that each field's `id` has a matching `for`.

## 🪜 Steps

**Project: Top 10 Films, rebuilt** (the `day65/` folder is a finished version: build your own first, then compare). The Java is the same as Day 64; the work is in `templates/movies.html`.

1. Swap the outer `div`s for `header` and `main`.
2. Label the list, and keep it an `ol`.
3. Give every form field an `id` and a `<label for>`, and lay the form out with Bootstrap's grid (`col-md-4`, `col-md-2`).
4. Add the viewport tag, `lang="en"` and `charset`.
5. Load Bootstrap's script with `defer`.
6. Write the tests in `MovieControllerTest` that prove each of these.
7. Try it with a screen reader: NVDA on Windows, VoiceOver on a Mac or iPhone, or TalkBack on Android.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day65
mvn spring-boot:run
```

Open http://localhost:8080/movies, then try it at phone width in your browser's device mode. Run Lighthouse (in Chrome's DevTools) for an accessibility score. Stop the server with Ctrl+C.

```bash
cd day65
mvn test
```

## 🆘 Stuck?

- **Clicking a label doesn't focus its field**: the `for` doesn't match the field's `id`. `th:field` sets the `id` to the field's name, so `for="year"` matches `th:field="*{year}"`.
- **Bootstrap's dropdowns stop working after adding `defer`**: an inline script that uses Bootstrap ran first. Put your own code in a file loaded with `defer` too, after Bootstrap.
- **Lighthouse says "low contrast"**: light grey text on white is the usual cause. Body text needs a contrast ratio of at least 4.5:1.
- **Everything is labelled with `aria-label`**: visible text is better. Use `aria-label` only where there's no visible text to point to.

## ✅ Checklist
- [ ] The page uses `header`, `main` and a labelled `ol`
- [ ] Every form field has a joined `<label>`
- [ ] The layout works at phone width
- [ ] Scripts load with `defer`
- [ ] Tried the page with a screen reader or Lighthouse
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 66 builds a versioned products API, and shows exactly how PUT and PATCH differ.
