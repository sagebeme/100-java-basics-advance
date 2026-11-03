# Day 58 - Bootstrap: Pages That Fit Every Phone

> Most of your users will see your site on a phone, many on a small, older one. Bootstrap gives you a grid, a navbar and form styles that fold down to fit, without writing that CSS yourself.

## 📖 The story

The Mavuno farmers' co-operative in Meru has a website that one of the members' sons built in 2014. On a laptop it's fine. On the phones the farmers actually use, the menu runs off the edge, the price table needs sideways scrolling, and the sign-up form's buttons are too small to press. The chairman wants it fixed before the harvest.

**Bootstrap** is a CSS and JavaScript toolkit used on a huge share of the web. You add its stylesheet, give your HTML its class names, and get:

- a 12-column **grid** that rearranges itself at different screen widths: three cards side by side on a laptop, one under the other on a phone;
- a **navbar** that folds into a ☰ button on small screens;
- **cards**, **badges**, **alerts**, **tables** and **forms** that look consistent and are large enough to tap.

Today you build a dashboard for the co-op's office: today's deliveries, prices and a members table, all readable on a phone.

## 🎯 Learning Objectives
- Add Bootstrap from a CDN, with an `integrity` hash so a changed file is refused
- Lay out a page with `container`, `row` and `col-*`, and make it respond with breakpoints (`col-12 col-md-4`)
- Use the navbar, cards, badges, alerts and tables
- Style forms, and show which field is wrong with `is-invalid` and `invalid-feedback`
- Combine Bootstrap with Thymeleaf loops and conditions
- Check a page at phone width, not just on your laptop

## 🧰 What you'll use

**Bootstrap from a CDN, safely.** The `integrity` attribute is a hash of the exact file. If the CDN ever served something else, the browser would refuse to run it:

```html
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
```

**The grid.** A row is 12 columns wide. `col-12 col-md-4` means "all 12 columns on a phone, 4 of them from medium screens up", so three cards sit side by side on a laptop and stack on a phone:

```html
<div class="row g-3">
    <div class="col-12 col-md-4" th:each="crop : ${crops}">
        <div class="card h-100">
            <div class="card-body">
                <h2 class="h5 card-title" th:text="${crop.name}">Coffee</h2>
                <span class="badge text-bg-success" th:if="${crop.priceUp}">Price up</span>
            </div>
        </div>
    </div>
</div>
```

**Forms that point at the problem.** Add `is-invalid` to a broken field and put the message in a `div.invalid-feedback` right after it. Keep what the user typed (`th:value` or `th:field`), so they fix one field instead of retyping all of them.

**The viewport tag.** Without `<meta name="viewport" content="width=device-width, initial-scale=1">`, phones pretend to be a 980-pixel-wide screen and shrink everything. Every page needs it.

## 🪜 Steps

Each exercise is a small Maven project with its own README. The starter compiles and its tests fail until you're done; `solution/` holds one answer for each file you change, at the same path. Run `mvn test` inside the exercise folder.

1. **[Exercise 1: The co-op's home page, with Bootstrap](exercise1/README.md)** (`exercise1/`): Bootstrap from a CDN with integrity hashes, a navbar that folds into a button on phones, and a three-card grid.
2. **[Exercise 2: Prices and a sign-up form, in Bootstrap](exercise2/README.md)** (`exercise2/`): responsive cards and badges from a list, and a form that marks each broken field and keeps what was typed.
3. **Project: The Co-op Office Dashboard** (your own new project, with web and Thymeleaf).
   1. A navbar with the co-op's name and three links, folding on phones.
   2. A row of summary cards: kilos delivered today, members, and the best price this week.
   3. A table of today's deliveries (member, crop, kilos, amount in shillings), wrapped in `table-responsive` so it scrolls inside itself on a phone.
   4. A form to record a delivery, with `is-invalid` errors that keep what was typed, and a success alert after saving.
   5. MockMvc tests for the pages and the form's errors. Then open it at 375 pixels wide in your browser's device mode.

## 🧪 Run it and test it

Needs JDK 21 and Maven. For an exercise:

```bash
cd day58/exercise1
mvn test
mvn spring-boot:run
```

Open http://localhost:8080 and narrow the browser window: the menu folds into a button and the cards stack.

## 🆘 Stuck?

- **The ☰ button does nothing**: Bootstrap's JavaScript isn't loaded. Add `bootstrap.bundle.min.js` before `</body>` (the bundle includes Popper, which dropdowns need).
- **The browser refuses to load Bootstrap**: the `integrity` hash doesn't match the version in the URL. Copy both from the same place.
- **Everything is tiny on a phone**: the viewport `<meta>` tag is missing.
- **Columns don't stack on a phone**: you used `col-4`, which is 4 columns at every size. Use `col-12 col-md-4`.
- **The error message never shows**: `invalid-feedback` is only visible right after an element with `is-invalid`.

## ✅ Checklist
- [ ] Loaded Bootstrap from a CDN with integrity hashes
- [ ] A grid that rearranges itself between phone and laptop
- [ ] A navbar that folds into a button on phones
- [ ] A form that marks broken fields and keeps what was typed
- [ ] Built the co-op dashboard and checked it at phone width
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 59 upgrades Mtaa News from Day 57 with Bootstrap: story pages, paging, and deleting safely.
