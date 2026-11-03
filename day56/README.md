# Day 56 - Web Pages From Spring Boot: Static Files and Thymeleaf

> An API is for apps. People want pages. Today Spring Boot serves both kinds: files that never change, and templates it fills in for every visitor.

## 📖 The story

Zawadi runs a hair salon in Kitengela, and her customers keep asking on WhatsApp: "What time do you open? How much are box braids?" She wants a small website: a home page, a price list and a contact page, all with the same header and footer, that she can show on her phone.

There are two kinds of page in it:

- **Static files** never change: the logo, the stylesheet, a script, the "about us" page. Spring Boot serves anything in `src/main/resources/static/` exactly as it is: `static/css/site.css` is at `/css/site.css`.
- **Templates** are filled in for each request. The price list comes from a Java `List`, so when Zawadi changes a price in one place, the page follows. Spring Boot uses **Thymeleaf** for this: ordinary HTML files in `src/main/resources/templates/`, with `th:` attributes saying where the data goes.

And because every page shares the header and footer, they're written once, as **fragments**, and included everywhere.

## 🎯 Learning Objectives
- Serve static files from `src/main/resources/static/`, and know how their addresses are made
- Map extra folders and cache rules with a `WebMvcConfigurer`
- Return a Thymeleaf template from a `@Controller`, and pass it data through a `Model`
- Fill a template with `th:text` and `th:each`
- Share a header and footer between pages with `th:fragment` and `th:replace`, including a parameter for the current page
- Know the difference between `@Controller` (returns a page name) and `@RestController` (returns data)

## 🧰 What you'll use

**A page controller** returns the template's name, not data:

```java
@Controller
public class PageController {
    @GetMapping("/prices")
    public String prices(Model model) {
        model.addAttribute("services", services);   // available in the template as ${services}
        return "prices";                             // templates/prices.html
    }
}
```

**A template** is HTML that still opens in a browser on its own. The text inside is a placeholder that Thymeleaf replaces:

```html
<tr th:each="s : ${services}">
    <td th:text="${s.name}">Box braids</td>
    <td th:text="|KES ${#numbers.formatInteger(s.shillings, 1, 'COMMA')}|">KES 3,500</td>
</tr>
```

**Fragments.** Write the header once in `templates/fragments/header.html`:

```html
<header th:fragment="header(active)">
    <a th:href="@{/}" th:attr="aria-current=${active == 'home'} ? 'page'">Home</a>
    ...
</header>
```

and include it on every page with `<div th:replace="~{fragments/header :: header('prices')}"></div>`.

**Static files with rules.** A `WebMvcConfigurer` can serve another folder at another address, and tell browsers how long to keep the files (`CacheControl.maxAge(1, TimeUnit.DAYS)`), so a phone on slow data doesn't download the logo on every visit.

## 🪜 Steps

Each exercise is a small Maven project with its own README. The starter compiles and its tests fail until you're done; `solution/` holds one answer for each file you change, at the same path. Run `mvn test` inside the exercise folder.

1. **[Exercise 1: A juice kiosk's page, from static files](exercise1/README.md)** (`exercise1/`): a phone-friendly `static/index.html` with its CSS, script and logo, plus a custom resource handler with a cache header.
2. **[Exercise 2: One header for every page](exercise2/README.md)** (`exercise2/`): Thymeleaf fragments with a parameter that marks the current page, and a tours table with formatted prices.
3. **Project: Zawadi's Salon** (your own new project, starting from Day 54's `pom.xml` plus `spring-boot-starter-thymeleaf`).
   1. `static/`: a stylesheet, a logo and an `about.html`.
   2. `templates/fragments/`: a header with a parameter for the current page, and a footer with the opening hours.
   3. `templates/index.html`, `prices.html` and `contact.html`, each using both fragments.
   4. A `PageController` with a `Service` list (name, minutes, price in whole shillings) for the price table.
   5. Tests with MockMvc: each page answers 200, and the price page shows a price with its comma.

## 🧪 Run it and test it

Needs JDK 21 and Maven. For an exercise:

```bash
cd day56/exercise2
mvn test
mvn spring-boot:run
```

Then open http://localhost:8080/tours. Stop it with Ctrl+C.

## 🆘 Stuck?

- **The page shows `prices` as text**: the class is a `@RestController`. Pages need `@Controller`.
- **`TemplateInputException: Error resolving template`**: the name you returned doesn't match a file in `templates/`. Leave off `.html`.
- **The CSS 404s**: the address doesn't include `static`. `static/css/site.css` is at `/css/site.css`.
- **Changes to a template don't show**: Thymeleaf caches templates. Restart, or set `spring.thymeleaf.cache=false` while you work.
- **The fragment's parameter is always empty**: check the call passes it: `header('prices')`, with quotes.

## ✅ Checklist
- [ ] Served static files, and know their addresses
- [ ] Returned a Thymeleaf page from a `@Controller` with data from a `Model`
- [ ] Shared a header and footer between pages, with the current page marked
- [ ] Built Zawadi's salon site
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 57 goes deeper into Thymeleaf: conditions, loops with counters, dates, links built from ids, and why it escapes what people type.
