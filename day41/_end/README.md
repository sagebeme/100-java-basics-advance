# Day 41 - Solution

`src/index.html` and `src/style.css` are the finished kibanda page; open `index.html` in a browser. `src/PageCheckTest.java` runs `PageCheck` against it and checks the stylesheet.

Things worth noticing:

- **The HTML says what things *are*, and the CSS says how they *look*.** There's no `style=` attribute anywhere, and no `<br>` used for spacing.
- **Every form field has a `<label for>`.** Clicking the label focuses the field, and screen readers read it aloud.
- **The menu is a real `<table>`** with a `<caption>` and `<th scope="col">`, because it *is* a table of data.
- **Colours are named once** in `:root` and used with `var(--red)`. Changing the whole page's red is one edit.
- **One `@media` rule** turns the single column into two on wide screens. On a phone, everything stacks.
