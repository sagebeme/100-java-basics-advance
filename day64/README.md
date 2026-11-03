# Day 64 - My Top 10 Films: A Ranked List in a Database

> A top 10 list sounds simple until you move number 7 up to number 2. Then numbers 2 to 6 all have to move down one, and none of them can end up sharing a place.

## 📖 The story

Every Friday, Njeri and her friends argue about films at the Westgate cinema café. To settle it, each of them is keeping a ranked top 10 on a little website: title, director, year, a rating out of 10, and a rank. The site lists the films in order and has a form to add a new one, which goes in at the bottom.

The interesting part is **re-ranking**. When Njeri decides *Supa Modo* deserves 2nd place, not 7th:

- *Supa Modo* becomes 2;
- the films that were 2, 3, 4, 5 and 6 each move down one place;
- 1, 8, 9 and 10 don't move at all.

Get it wrong and two films end up at number 3 and none at number 6. `MovieService.setRank` does it in one careful pass, and its tests check moving up, moving down, moving to the same place, and refusing a rank that isn't in the list.

## 🎯 Learning Objectives
- Order query results with a method name: `findAllByOrderByRankAsc`
- Keep a business rule (ranks with no gaps and no ties) in a `@Service`, not the controller
- Update several rows together with `saveAll`, inside a transaction
- Show database data in a Thymeleaf page, and add to it from a form
- Test a service against a real (in-memory) database, rolled back after each test with `@Transactional`

## 🧰 What you'll use

**Sorting in the method name:**

```java
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByOrderByRankAsc();
}
```

**Moving a film up** from `oldRank` to `newRank` shifts everything from `newRank` to `oldRank - 1` down by one:

```java
for (Movie m : all) {
    if (!m.getId().equals(movieId) && m.getRank() >= newRank && m.getRank() < oldRank) {
        m.setRank(m.getRank() + 1);
    }
}
movie.setRank(newRank);
movieRepository.saveAll(all);
```

Moving *down* is the mirror image. Draw five boxes on paper and move one; the two loops will make sense.

**Rolled-back tests.** `@SpringBootTest` with `@Transactional` runs each test inside a transaction and undoes it at the end, so every test starts with an empty database even though they share one.

**`rank` is a column name.** It's a reserved word in some databases. The entity uses `@Column(name = "movie_rank")` to stay out of trouble.

## 🪜 Steps

**Project: Top 10 Films** (the `day64/` folder is a finished version: build your own first, then compare).

1. `Movie`: id, title, director, year, rating and rank.
2. `MovieRepository.findAllByOrderByRankAsc()`.
3. `MovieService`: `getTop10Movies()` (at most ten), `addMovie` (at the bottom) and `setRank` (with the shifting, and an error for a rank outside 1 to the number of films).
4. `MovieController`: `GET /movies` (the list and an empty form) and `POST /movies` (add, then redirect).
5. `templates/movies.html` in a cinema style (`cinema.css`).
6. Add a small "Move to rank" form for each film that calls `setRank`, and show a message if the rank is refused.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day64
mvn spring-boot:run
```

Open http://localhost:8080/movies and add a few films. The database is in memory, so they're gone when you stop the server with Ctrl+C.

```bash
cd day64
mvn test
```

## 🆘 Stuck?

- **Two films share a rank after a move**: the shifting range is off by one. Moving up shifts `newRank` to `oldRank - 1`; moving down shifts `oldRank + 1` to `newRank`.
- **The moved film shifts itself too**: skip it in the loop (`!m.getId().equals(movieId)`).
- **`Syntax error in SQL statement` mentioning RANK**: rename the column with `@Column(name = "movie_rank")`.
- **Tests pass alone but fail together**: they're sharing data. Add `@Transactional` to the test class so each one is rolled back.

## ✅ Checklist
- [ ] Films are listed in rank order from the database
- [ ] A new film goes in at the bottom
- [ ] Moving a film up or down shifts exactly the films in between
- [ ] A rank outside the list is refused
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 65 takes this same site and makes it well built: semantic HTML, labels for every field, a layout that works on phones, and scripts that don't block the page.
