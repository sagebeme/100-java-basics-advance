# Day 59 - Exercise 1: Mtaa News, one story per page

Mtaa News is a community news site for Kayole, in Nairobi. The front page lists every story with a short excerpt; that page is written. Each excerpt links to `/posts/1`, `/posts/2` and so on, and right now those links lead nowhere. Make them work.

**1. In `PostService`:**

- `find(id)` returns an `Optional<Post>`: the story, or empty.
- `excerpt(text, max)`, which the front page already calls, shortens text without breaking a word:

| Text | `max` | Excerpt |
|---|---|---|
| `Entry is free.` | 120 | `Entry is free.` (it fits, so it's left alone) |
| `one two three four` | 10 | `one two…` |
| `Hello, world and more` | 8 | `Hello…` (punctuation and spaces before the cut are dropped) |
| `first   paragraph`, a blank line, `second` | 50 | `first paragraph second` (runs of spaces and new lines become one space) |
| `abcdefghij` | 5 | `abcd…` (one long word is cut where it must be) |

The excerpt, `…` included, is never longer than `max`.

**2. In `BlogController`:** `GET /posts/{id}` shows the `post` template with that story in the model as `post`. When there's no such story, throw a `ResponseStatusException` with `HttpStatus.NOT_FOUND`, so the answer is a real 404 and not a page that says "not found" with a 200. A visit to `/posts/footbridge` is a 400 without you doing anything: Spring can't turn `footbridge` into a `long`.

**3. In `templates/post.html`:** the page title `Kayole's new footbridge opens · Mtaa News`, the story's title in an `<h1>`, the line `By Wanjiku Mwangi · 3 November 2026`, each paragraph in its own `<p>` (`post.paragraphs` is a list of them), and a link `← All stories` back to `/posts`.

## Files

- `src/main/java/com/learning/PostService.java` and `BlogController.java`, and `src/main/resources/templates/post.html`: your code goes here.
- `Post.java`, `templates/posts.html` and `static/css/gazeti.css`: done.
- `src/test/java/com/learning/PostServiceTest.java` and `BlogControllerTest.java`: the tests.
- `solution/src/...`: one answer for each file you change, at the same paths.

```bash
cd day59/exercise1
mvn test
```

## 📌 Stuck?

- `posts.stream().filter(p -> p.getId() == id).findFirst()` is already an `Optional`.
- In the controller: `postService.find(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))`.
- For `excerpt`: `text.replaceAll("\\s+", " ").trim()` first. Then `lastIndexOf(' ', max - 1)` finds the last space that leaves room for the `…`. If there's none, cut at `max - 1`.
- `#temporals.format(post.date, 'd MMMM yyyy')` gives `3 November 2026`.
