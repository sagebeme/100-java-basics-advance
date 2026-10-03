# Day 42 - Solution

`src/index.html` and `src/style.css` are Zuhura's finished portfolio; open `index.html` in a browser, then try it at phone width and with your computer in dark mode. `src/GalleryPageTest.java` checks the page with `PageCheck` and checks the stylesheet uses today's tools.

Things worth noticing:

- **Flexbox for one direction, grid for two.** The header, nav, facts and contact buttons are flex rows; the hero, gallery and about section are grids.
- **The gallery has no breakpoints.** `repeat(auto-fit, minmax(18rem, 1fr))` fits as many 18rem columns as there's room for: three on a laptop, one on a phone.
- **`grid-template-areas` draws the layout in words**: `"big text" "facts facts"` on wide screens, one column on phones.
- **Every kanga is pure CSS**: `border-image` for the pindo, layered gradients for the mji, and a `<span>` for the jina. No image files at all.
- **Pictures made of CSS still get descriptions**, through `role="img"` and `aria-label`.
- **Dark mode and reduced motion** are one `@media` block each.
