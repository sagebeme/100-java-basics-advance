# Day 58 - Exercise 2: Prices and a sign-up form, in Bootstrap

The Mavuno co-op (Exercise 1) wants two more pages: this week's buying prices, and a form for farmers to join. `PageController` is written, including the form's checks. Your work is the two templates, using Bootstrap's components so they look right on a phone without writing CSS.

**1. `templates/prices.html`**, from `${produce}`, a list of `Produce` (`name`, `unit`, `shillings`, `inSeason`):

- A grid with a column per item: `class="col-12 col-sm-6 col-lg-3"`, which is one across on a phone, two on a tablet and four on a laptop. Each holds a `.card` with the name and the price as `KES 1,200 / crate`.
- A `badge` saying `In season` or `Out of season`.
- After joining, the controller sends a `joined` message. Show it in a green `alert alert-success` at the top, and show no alert when there's no message.

**2. `templates/join.html`**: a form that posts to `/join` with three fields, `name`, `phone` and `county`. Each has a `<label for="...">` matching the field's `id`. The text boxes are `form-control`; the county is a `form-select` with a "Choose…" option and then one option per county in `${counties}`.

When the form comes back with problems, the model has `errors`, a map from field name to message, and `name`, `phone` and `county` as typed. For each field with a message:

- add `is-invalid` to the field, which draws it red;
- right after it, `<div class="invalid-feedback">` with the message.

Fields without a problem are left alone, and every field keeps what the farmer typed, including the chosen county. Nothing is more annoying than a form that clears itself because of one mistake.

## Files

- `src/main/resources/templates/prices.html` and `join.html`: your code goes here.
- `PageController.java`, `Produce.java` and `static/css/mavuno.css`: done.
- `src/test/java/com/learning/PageControllerTest.java`: the tests.
- `solution/src/main/resources/templates/`: one answer for each.

```bash
cd day58/exercise2
mvn test
```

## 📌 Stuck?

- `th:each="item : ${produce}"` goes on the column `div`, so you get one column per item.
- `th:if="${item.inSeason}"` and `th:unless="${item.inSeason}"` choose the badge.
- `th:classappend="${errors.containsKey('phone')} ? 'is-invalid'"` adds the class only when there's an error, and `${errors.phone}` is the message.
- `th:value="${phone}"` puts the typed text back. For the select, `th:selected="${c == county}"` on each option.
- The form has `novalidate` so the browser leaves the checking to the server, which is the check that counts.
