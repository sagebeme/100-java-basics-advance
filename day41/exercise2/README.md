# Day 41 - Exercise 2: Style a matatu ticket

`src/ticket.html` is a finished ticket for a matatu sacco. It has no style at all. Write `src/ticket.css` so it looks like a real printed ticket. The design is up to you, but the test checks that you used today's tools:

- a `:root` rule with **at least three** colour variables (`--something: ...`), used with `var(...)`
- a `.ticket` rule with a `border-radius` and a `box-shadow`
- a `font-family` with a fallback, like `font-family: "Courier New", monospace;`
- the `.stamp` turned slightly with `transform: rotate(...)`
- a `:hover` style somewhere

Open `ticket.html` in a browser while you work.

## Files

- `src/ticket.html`: the ticket (don't change it).
- `src/ticket.css`: your styles (it starts empty).
- `src/TicketStyleTest.java`: the checks.
- `solution/ticket.css`: one design.

## 📌 Stuck?

A dashed line across the ticket, like a tear-off strip: `border-top: 2px dashed var(--ink);`. Round "bites" out of the sides are a classic ticket look: a `radial-gradient` background can do it, but get the basics right first.
