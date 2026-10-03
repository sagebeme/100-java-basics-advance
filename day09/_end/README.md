# Day 9 - Solution

`src/FlavourMap.java` is one finished version, checked by `src/FlavourMapTest.java`.

Things worth noticing:

- **Copy before you change.** `sharedNotes` copies one region's notes into a new `TreeSet` before calling `retainAll`. The lists in the map come from `List.of`, which can't be changed, and you wouldn't want to damage the map even if they could.
- **The right collection does the work.** A `TreeSet` both removes duplicates and sorts, so `allNotes` is just "add everything".
- **`LinkedHashMap` keeps the order**, so `regionsWithNote` returns regions in the order they were added, and the tests can rely on it.
- **`containsKey` guards every lookup**, so an unknown region gets a friendly answer instead of a `NullPointerException`.
