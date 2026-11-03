# Day 87 - Portfolio Project: Cafe Finder Website

> Where in Nairobi can you work for three hours on one coffee, with Wi-Fi that actually works? Today's site answers that: search cafés by area, filter by rating, Wi-Fi and price, and see them on a map.

## 📖 The story

Njambi is a freelance designer. Her flat in Kilimani loses power whenever it rains, so she works from cafés, and her friends keep asking her which ones are good. She decides to put her list online: name, address, rating, whether there's Wi-Fi, and how pricey it is, searchable by area and shown on a map.

```
http://localhost:8080/?location=westlands&minRating=4&wifiOnly=true&maxPrice=TWO
```

The page lists matching cafés from best-rated down, with pins on an OpenStreetMap map of Nairobi. The eight cafés seeded at startup are example data: the names and areas are real places, but the ratings and descriptions are made up for the demo.

## 🎯 Learning Objectives
- Model data as a JPA entity with an enum column
- Seed a database at startup with a `CommandLineRunner`
- Search with a derived query: `findByAddressContainingIgnoreCaseOrNameContainingIgnoreCase`
- Combine optional filters cleanly: rating, Wi-Fi, price, each only when given
- Keep a search form's values filled in after submitting
- Show data on a Leaflet map: pass it to JavaScript safely, and load the map library with integrity hashes

## 🧰 What you'll use

**Optional request parameters** arrive as `null` when they're missing, so each filter only applies when it was given:

```java
candidates.stream()
        .filter(cafe -> minRating == null || cafe.getRating() >= minRating)
        .filter(cafe -> wifiOnly == null || !wifiOnly || cafe.isHasWifi())
        .filter(cafe -> maxPrice == null || cafe.getPriceLevel().ordinal() <= maxPrice.ordinal())
        .sorted(Comparator.comparing(Cafe::getRating).reversed())
        .toList();
```

**Enums in forms and the database.** `@RequestParam PriceLevel maxPrice` turns `TWO` into `PriceLevel.TWO`. `@Enumerated(EnumType.STRING)` stores the name, not the position, so adding a price level later doesn't scramble the old rows.

**Data into JavaScript.** Thymeleaf's inline JavaScript turns the list into a JSON array, safely escaped:

```html
<script th:inline="javascript">
    const cafes = /*[[${cafes}]]*/ [];
</script>
```

**Text, not HTML, in popups.** Building a popup as `` `<strong>${cafe.name}</strong>` `` would run any HTML hidden in a café's name. Creating elements and setting `textContent` shows it as plain text.

**Integrity hashes.** The map library comes from a CDN. `integrity="sha256-..."` makes the browser refuse the file if its contents don't match, so a hacked CDN can't run code on your site.

## 🪜 Steps

**Your brief** (the `day87/` folder is one finished version: build your own first, then compare).

1. `Cafe` (with `PriceLevel`) and `CafeRepository` with the name-or-address search.
2. `DataSeeder` with your own favourite cafés.
3. `CafeService.search` with all four filters, best-rated first.
4. `CafeController` and `index.html`: the form (keeping its values), the results, a "no cafés found" message, and the map.
5. Tests: the search and each filter in the service, and the page with results, filters, no results, the map and the viewport tag.
6. Make it yours: a form to add a café (with validation!), a JSON API at `/api/cafes`, or "near me" using the browser's location and the distance between coordinates.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day87
mvn test
mvn spring-boot:run
```

Open <http://localhost:8080> and try a search.

## 🆘 Stuck?

- **A search for "westlands" finds nothing**: the query must ignore case: `ContainingIgnoreCase`.
- **The filters are forgotten after searching**: put the values back in the model and use them in the form (`th:value`, `th:checked`, `th:selected`).
- **`?maxPrice=CHEAP` shows an error page**: only `ONE`, `TWO` and `THREE` are valid. A select box only offers valid values; a hand-typed URL gets a 400.
- **The map is grey**: Leaflet needs its CSS as well as its JavaScript, and the map `div` needs a height.
- **The map library won't load after you upgrade it**: the integrity hash is for one exact file. Update the hash with the version.

## ✅ Checklist
- [ ] Cafés stored with JPA and seeded at startup
- [ ] Search by area or name, plus rating, Wi-Fi and price filters
- [ ] Results on a map, with safe popups
- [ ] The tests pass
- [ ] Added a form, an API or "near me"
- [ ] Committed code to Git

## ➡️ Next

Day 88 builds a to-do list REST API: create, update, complete and delete tasks, with proper errors.
