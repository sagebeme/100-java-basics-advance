# Day 58 - Exercise 1: The co-op's home page, with Bootstrap

Mavuno Farmers' Co-op buys produce from six hundred farmers around Meru. Most of them will open the site on a phone, so it has to work at phone width first. Bootstrap gives you a tested grid and components, so you don't write that CSS yourself. Build the home page, `templates/home.html`, with it.

**1. Load Bootstrap 5.3.3 from the jsDelivr CDN,** CSS in the `<head>` and the JavaScript bundle at the very end of `<body>`, each with its `integrity` hash and `crossorigin="anonymous"`:

```
https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css
  sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH
https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js
  sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz
```

The hash is a fingerprint of the file. If the CDN is ever hacked and the file changed, the browser refuses to run it, instead of running someone else's code on your page. The co-op's own `mavuno.css` must come **after** Bootstrap, so its colours win. Add the viewport `<meta>` too, or phones show a shrunken desktop page.

**2. A navbar that folds up on phones:** `navbar-expand-md`, so the links sit in a row from medium screens up, and below that a `navbar-toggler` button opens them. The button's `data-bs-target` and `aria-controls` both point at the `collapse navbar-collapse` element's id, and it needs an `aria-label`, because the icon has no words. Three `nav-link`s: **Prices** (`/prices`), **Collection days** (`/collection`) and **Join** (`/join`).

**3. Three cards in a grid:** in a `.row`, three columns with `class="col-12 col-md-4"`, which is full width on a phone and a third each from medium up. Each holds a `.card`: **Fair prices** ("This week's buying prices are posted every Monday."), **Collection** ("The lorry comes to your village centre twice a week.") and **Paid by M-Pesa** ("Payment lands within 48 hours of delivery.").

## Files

- `src/main/resources/templates/home.html`: your code goes here.
- `HomeController.java` and `static/css/mavuno.css`: done.
- `src/test/java/com/learning/HomeControllerTest.java`: the tests.
- `solution/src/main/resources/templates/home.html`: one answer.

```bash
cd day58/exercise1
mvn test
```

Then `mvn spring-boot:run`, open http://localhost:8080, and narrow the browser window: the menu folds into a button and the cards stack.

## 📌 Stuck?

- The Bootstrap docs' "Navbar" page has a complete responsive example; copy its structure and change the names.
- Write `class="col-12 col-md-4"` exactly, and the viewport tag as `<meta name="viewport" content="width=device-width, initial-scale=1">`; the tests look for those.
- To check a hash yourself: `curl -sL <url> | openssl dgst -sha384 -binary | openssl base64 -A`.
