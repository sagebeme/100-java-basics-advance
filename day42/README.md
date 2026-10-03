# Day 42 - Zuhura's Kanga Gallery: Flexbox, Grid and Responsive Design

> A portfolio for a textile designer in Mombasa, laid out with flexbox and CSS grid, and good on every screen from a cheap phone to a wide monitor.

## 📖 The story

Zuhura designs **kangas**, the printed cotton cloths you see all along the Swahili coast. Every kanga has a border (the **pindo**), a central design (the **mji**) and a saying printed across it (the **jina**): *Haba na haba hujaza kibaba*, "little by little fills the measure". People choose kangas for what they *say* as much as how they look.

She wants a portfolio site her customers can open on their phones at the market and her shop buyers can browse on laptops. Today you'll lay it out:

- a sticky header with her name and links
- a hero with a big headline and a kanga beside it (stacked on phones)
- a gallery that shows as many kangas per row as fit, with no breakpoints at all
- an "about" section arranged with named grid areas
- a dark theme for people whose phones are in dark mode

Every kanga on the page is drawn in **pure CSS** with gradients. There isn't a single image file. Open `_end/src/index.html` to see it.

## 🎯 Learning Objectives
- Lay things out in one direction with **flexbox**: `justify-content`, `align-items`, `flex-wrap` and `gap`
- Lay things out in two directions with **CSS grid**: `grid-template-columns`, `fr` units, `span` and `grid-template-areas`
- Build a responsive gallery with `repeat(auto-fit, minmax(...))`
- Write **mobile-first** CSS: one column by default, more columns inside `@media (min-width: ...)`
- Support dark mode with `prefers-color-scheme` and calmer pages with `prefers-reduced-motion`
- Draw patterns with `linear-gradient`, `radial-gradient`, `conic-gradient` and `border-image`

## 🧰 What you'll use

**Flexbox** lines items up in a row (or a column) and shares out the space:

```css
.top { display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 1rem; }
```

**Grid** places items in rows *and* columns at once:

```css
.gallery { display: grid; grid-template-columns: repeat(auto-fit, minmax(18rem, 1fr)); gap: 1.5rem; }
```

"Fit as many columns as you can, each at least 18rem wide, and share out what's left." Three columns on a laptop, one on a phone, and no `@media` needed.

**Named areas** draw a layout in words:

```css
.about-grid { grid-template-areas: "big" "text" "facts"; }          /* phones: one column */
@media (min-width: 56rem) {
    .about-grid {
        grid-template-columns: 1.2fr 1fr;
        grid-template-areas: "big text" "facts facts";               /* wide: two columns */
    }
}
.big { grid-area: big; }
```

**Which to use?** If it's a single row or column, like a nav bar or a list of buttons, use flexbox. If items have to line up both across and down, like a gallery, a page layout or a timetable, use grid.

**Dark mode** is a media query that redefines your colour variables:

```css
@media (prefers-color-scheme: dark) {
    :root { --sand: #14121f; --ink: #f6efe3; }
}
```

## 🪜 Steps

1. **Exercise 1: A matatu route card** (`exercise1/`). Flexbox: rows, wrapping and spacing.
2. **Exercise 2: A weekly timetable** (`exercise2/`). Grid: equal columns, a spanning item and a phone layout.
3. **Project: Zuhura's Kanga Gallery** (`_start/src/`). The HTML is done; `style.css` has a `/* TODO */` for each layout.
   1. The header and nav (flexbox).
   2. The hero (grid: one column, then two on wide screens).
   3. The gallery (`auto-fit` + `minmax`) and tiles that lift on hover.
   4. The about section (`grid-template-areas`) and the facts row (flexbox).
   5. The dark theme. Then draw the kangas: copy the gradients from `_end`, or design your own.
   6. Run `PageCheck` and the tests, and check every width from 320px to 1400px.

## 🧪 Run it and test it

Open `index.html` in a browser. Use your browser's device toolbar (F12, then the phone icon) to try phone sizes. For the checks, from the repo root (on Windows, use `;` instead of `:`):

```bash
javac -cp "lib/*" -d day42/_end/src day42/_end/src/*.java
cd day42/_end/src && java -cp ".:../../../lib/*" PageCheck index.html && cd ../../..
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute -cp "day42/_end/src:lib/*" --scan-classpath
```

## 🆘 Stuck?

- **`justify-content` does nothing**: it goes on the flex *container*, and the container needs `display: flex`.
- **The page scrolls sideways on a phone**: something has a fixed width. Use `width: min(100% - 2rem, 70rem)` instead of `width: 70rem`.
- **The gallery shows one huge tile**: `auto-fit` with `minmax(18rem, 1fr)` stretches a lonely tile to fill the row. That's expected with one item; add more.
- **Dark mode looks wrong**: anything with a hard-coded colour won't change. Use your `var(--...)` colours everywhere.

## ✅ Checklist
- [ ] Used flexbox for the header, nav, facts and buttons
- [ ] Used grid for the hero, gallery and about section
- [ ] Made a gallery that needs no breakpoints
- [ ] Wrote mobile-first CSS with a `min-width` media query
- [ ] Added a dark theme
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Now that you know how HTML is built, Day 43 takes it apart: scrape headlines from a news page with jsoup.
