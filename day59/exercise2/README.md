# Day 59 - Exercise 2: The editor's desk: pages and deleting

Behind Mtaa News (Exercise 1) is the editor's desk, a page listing every story so old ones can be taken down. With hundreds of stories, one long page is too slow on a phone, so the desk shows three at a time with Bootstrap pagination. Each story card, with its section badge and a delete button that opens a confirmation dialog, is already in `templates/posts.html`.

**1. `PostService`**, which holds seven stories:

| Method | Does |
|---|---|
| `totalPages(pageSize)` | Seven stories at 3 a page is 3 pages. Never less than 1, so an empty site still has a page 1. |
| `getPage(page, pageSize)` | Page 1 is stories 1–3, page 2 is 4–6, page 3 is 7. A page past the end is empty. Page 0 or less is an `IllegalArgumentException`. |
| `delete(id)` | `true` if it was there to delete. |

**2. `BlogController`:**

- `GET /posts?page=2` is written, but a page out of range should redirect: below 1 to `/posts`, past the last page to the last page (`/posts?page=3`).
- `POST /posts/{id}/delete` deletes the story and redirects to `/posts`, with a flash message: `Story deleted.`, or `That story was already gone.` if it wasn't there, which happens when two editors click at once. It's a `POST` on purpose: `GET /posts/5/delete` must do nothing (Spring answers 405), because browsers, link previews and search engines follow `GET` links freely.

**3. The pagination in `templates/posts.html`:** Bootstrap's `pagination` list with **Previous**, a link per page (`/posts?page=1` …), and **Next**. On page 1, Previous's `page-item` is `disabled`; on the last page, Next's is. The current page's item is `active`, and its link has `aria-current="page"` so screen readers say which page you're on.

## Files

- `src/main/java/com/learning/PostService.java` and `BlogController.java`, and `src/main/resources/templates/posts.html`: your code goes here.
- `Post.java` and `static/css/gazeti.css`: done.
- `src/test/java/com/learning/PostServiceTest.java` and `BlogControllerTest.java`: the tests. The controller tests swap the real `PostService` for a Mockito stand-in, so each test decides what it returns.
- `solution/src/...`: one answer for each file you change, at the same paths.

```bash
cd day59/exercise2
mvn test
```

## 📌 Stuck?

- Pages, in whole numbers: `(count + pageSize - 1) / pageSize` rounds up without a `double`.
- `posts.subList(from, Math.min(from + pageSize, posts.size()))`, copied with `List.copyOf` so the caller can't change your list.
- `return "redirect:/posts?page=" + totalPages;` sends the browser elsewhere.
- `redirect.addFlashAttribute("message", ...)` (with a `RedirectAttributes redirect` parameter) keeps the message for exactly one more page view.
- `${#numbers.sequence(1, totalPages)}` gives 1, 2, 3 for `th:each`. `th:classappend="${p == currentPage} ? 'active'"` marks the current one.
