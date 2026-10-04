# Day 53 - Exercise 1: Cleaning listings

Write the three methods in `src/Cleaner.java` (`Rental` is given):

- `rent(text)`: the first number, ignoring commas, or empty: `"KES 65,000 / month"` → `65000`.
- `bedrooms(title)`: `"2BR"`, `"3 bedroom"` and `"1 Bed"` give the number; `"Bedsitter"` and `"Studio"` give `0`; anything else is empty.
- `clean(raw)`: one `Rental` per row (area trimmed) that has both, without duplicates, in the original order.

## 📌 Stuck?

Records compare by their values, so a `LinkedHashSet<Rental>` removes duplicates and keeps the order. A regular expression `(\d+)\s*(?:br|bed|bedroom)` with `CASE_INSENSITIVE` finds a bedroom count. One answer is in `solution/Cleaner.java`.
