# Day 42 - Exercise 1: A matatu route card (flexbox)

`src/route.html` is a card for one matatu route. Write `src/route.css` so it looks like a route card on a stage noticeboard, using **flexbox**:

- `.card header`: the route number and name on one row, pushed to opposite ends (`justify-content: space-between`) and centred vertically (`align-items: center`)
- `.stops`: the stages in a row that **wraps** onto more lines on a narrow screen (`flex-wrap: wrap`), with a `gap` between them
- `.card footer`: the fare and the button on one row, also pushed apart

## Files

- `src/route.html`: the card (don't change it).
- `src/route.css`: your styles (it starts nearly empty).
- `src/RouteStyleTest.java`: checks you used flexbox in those three places.
- `solution/route.css`: one design.

## 📌 Stuck?

`display: flex` goes on the **container** (the parent), not on the items. `gap: 0.5rem` spaces the items without any margins.
