# Day 59 - Mtaa News, the Upgrade: Detail Pages, Paging and Deleting

> Mtaa News from Day 57 works, and now it has readers. Today it gets a proper look, a page per story with a real 404, pages of ten instead of one endless list, and a delete button that can't be clicked by accident.

## 📖 The story

Mtaa News has fifty stories now, and Kevo and Shiru are hearing complaints:

- "The front page takes forever on my phone." Fifty stories on one page is too many. Show a few at a time, with **Previous**, **1 2 3** and **Next**.
- "I shared a link to a story and it said 'Whitelabel Error Page'." A story that doesn't exist (deleted, or a typo in the link) should get a proper **404** page, not a crash.
- "The front page is just text." It needs excerpts, the first part of each story, cut at a whole word with "…".
- "I deleted a story by mistake." Deleting needs a confirmation, must be a `POST` (never a link that a search engine could follow), and should say what happened afterwards.

This is the first day of the **Advanced** stage. The code is still a web app, but now you think about what happens when things go wrong, and about the people and programs that use it in ways you didn't plan.

## 🎯 Learning Objectives
- Look things up with `Optional`, and answer a missing one with `ResponseStatusException` and a 404
- Page through a list: page numbers from 1, a page size, the total number of pages, and redirects for pages out of range
- Build Bootstrap pagination with `disabled`, `active` and `aria-current`
- Change data only with `POST`, and redirect afterwards with a flash message (`RedirectAttributes`)
- Write small, careful helpers (an excerpt that never cuts a word) and test their edge cases
- Test a controller with a stand-in service using `@MockitoBean`

## 🧰 What you'll use

**A real 404.** `Optional` makes "maybe there's no such story" part of the method's type, and `orElseThrow` turns it into the right answer:

```java
@GetMapping("/posts/{id}")
public String post(@PathVariable long id, Model model) {
    Post post = postService.find(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No story " + id));
    model.addAttribute("post", post);
    return "post";
}
```

**Paging.** With 7 stories and 3 per page, there are `(7 + 3 - 1) / 3 = 3` pages; page 2 is stories 4 to 6. Ask for page 9 and the controller redirects to the last page, rather than showing an empty one.

**Delete with POST, then redirect.** A `GET` must never change anything: link previews, browser pre-loading and search engines all follow links. So the delete button is a small form:

```java
@PostMapping("/posts/{id}/delete")
public String delete(@PathVariable long id, RedirectAttributes redirect) {
    boolean deleted = postService.delete(id);
    redirect.addFlashAttribute("message", deleted ? "Story deleted." : "That story was already gone.");
    return "redirect:/posts";
}
```

A **flash attribute** survives exactly one redirect, so the message shows once and is gone if the page is refreshed.

**A stand-in for a test.** `@MockitoBean PostService postService` replaces the real service in a controller test, so you can make it return "no such story" or "already gone" whenever you need.

## 🪜 Steps

Each exercise is a small Maven project with its own README. The starter compiles and its tests fail until you're done; `solution/` holds one answer for each file you change, at the same path. Run `mvn test` inside the exercise folder.

1. **[Exercise 1: Mtaa News, one story per page](exercise1/README.md)** (`exercise1/`): a detail page per story with a real 404, an `Optional` lookup, and excerpts that never break a word.
2. **[Exercise 2: The editor's desk: pages and deleting](exercise2/README.md)** (`exercise2/`): Bootstrap pagination with out-of-range redirects, and deleting by `POST` with a flash message, tested with a Mockito stand-in.
3. **Project: Mtaa News, upgraded** (your Day 57 project).
   1. Bootstrap from Day 58: a navbar, cards for the stories, and a readable story page at phone width.
   2. `/posts/{id}` with a 404 for missing stories, and a friendly `templates/error/404.html`.
   3. Excerpts on the front page, and paging with 5 stories a page.
   4. Delete by `POST`, with a confirmation dialog and a flash message.
   5. Tests: the 404, a page out of range, and both delete messages.

## 🧪 Run it and test it

Needs JDK 21 and Maven. For an exercise:

```bash
cd day59/exercise2
mvn test
mvn spring-boot:run
```

Then open http://localhost:8080/posts?page=2, and try page 9. Stop it with Ctrl+C.

## 🆘 Stuck?

- **A missing story gives a 500, not a 404**: `Optional.get()` threw `NoSuchElementException`. Use `orElseThrow` with a `ResponseStatusException`.
- **The flash message never appears**: you returned the template name instead of `redirect:/posts`. Flash attributes only exist after a redirect.
- **Page 0 or page -1 crashes**: redirect anything below 1 to page 1 before asking the service.
- **"Previous" on page 1 still links somewhere**: give its `li` the `disabled` class with `th:classappend="${currentPage == 1} ? 'disabled'"`.
- **The excerpt cuts "Kayole" into "Kay…"**: look for the last space before the limit with `lastIndexOf(' ', max - 1)`, and cut there.

## ✅ Checklist
- [ ] A missing story answers 404 with a friendly page
- [ ] Pages of stories, with redirects for pages out of range
- [ ] Excerpts that never cut a word
- [ ] Deleting is a `POST`, confirmed first, with a flash message after
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 60 is about forms in depth: binding, validation messages, and a registration form that keeps what people typed.
