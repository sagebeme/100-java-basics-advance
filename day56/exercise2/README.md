# Day 56 - Exercise 2: One header for every page

Pwani Day Trips, a small tour company in Mombasa, has a site with three pages. On their old site, the header was copied into each page, so when they changed their phone number they missed one page, and customers rang a dead line for a month. Here the header and footer live in one place each, as **Thymeleaf fragments**, and every page pulls them in.

The home and about pages, the controller for them, the `Tour` class and the stylesheet are written. Your jobs:

**1. `templates/fragments/header.html`:** a fragment named `header` that takes one parameter, `active`. It shows **Pwani Day Trips** and three links: Home (`/`), Tours (`/tours`) and About (`/about`). The link for the page being shown gets `aria-current="page"`, so screen readers announce it and the CSS can underline it. The pages already call it like this:

```html
<header th:replace="~{fragments/header :: header('about')}"></header>
```

**2. `templates/fragments/footer.html`:** a fragment named `footer` with the line `Pwani Day Trips · Moi Avenue, Mombasa · 0712 345 678`.

**3. The tours page.** In `WebController`, `GET /tours` shows the `tours` template with the title `Day trips` and these tours:

| Name | Place | KES |
|---|---|---|
| Fort Jesus and Old Town walk | Mombasa | 2500 |
| Wasini Island and dolphins | Shimoni | 7800 |
| Haller Park at feeding time | Bamburi | 1500 |

In `templates/tours.html`, use the header (with `tours` active) and the footer, and a table with a row per tour. Show prices with a thousands comma: `KES 7,800`.

## Files

- `src/main/resources/templates/fragments/header.html`, `fragments/footer.html` and `tours.html`, and `src/main/java/com/learning/WebController.java`: your code goes here.
- `index.html`, `about.html`, `Tour.java` and `static/css/pwani.css`: done.
- `src/test/java/com/learning/WebControllerTest.java`: the tests.
- `solution/src/...`: one answer for each file you change, at the same paths.

```bash
cd day56/exercise2
mvn test
```

## 📌 Stuck?

- `th:fragment="header(active)"` names a fragment and its parameter. Inside it, `${active}` is whatever the page passed.
- `th:replace` swaps the placeholder tag for the fragment; `th:insert` puts the fragment inside the placeholder, giving a `<header>` within a `<header>`. The tests want `replace`.
- `th:attr="aria-current=${active == 'tours'} ? 'page'"` adds the attribute only when the condition is true. With no "else" part, the value is `null`, and Thymeleaf leaves the attribute out.
- `${#numbers.formatInteger(tour.shillings, 1, 'COMMA')}` gives `7,800`. Inside `|...|`, text and expressions mix: `|KES ${...}|`.
