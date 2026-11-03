# Day 57 - Thymeleaf in Depth: Mtaa News, the First Edition

> Yesterday's templates filled in a table. Today they decide things: show this if, hide that unless, number the rows, format the dates, and never let a reader's comment turn into code.

## 📖 The story

Kevo and Shiru want to start **Mtaa News**, a neighbourhood news site for Kayole: the new footbridge, the water rationing timetable, the Under-14 football final. Newspapers in Nairobi don't cover their estate; they will. They need:

- a front page listing every story, newest first, with its date and section;
- a page for each story;
- a form to write a new story, and to edit one;
- the same header and footer everywhere.

Every one of those needs Thymeleaf to do more than print a value: format a date as `3 Nov 2026`, show "No stories yet" when the list is empty, build `/posts/7` from a story's id, mark the current page in the menu, and keep a form filled in when it's sent back.

## 🎯 Learning Objectives
- Write Thymeleaf expressions: `${...}` for data, `*{...}` for a form's object, `@{...}` for links, `|...|` for text with values in it
- Make decisions with `th:if`, `th:unless` and `th:switch`
- Loop with `th:each`, and use its status (`count`, `first`, `last`, `odd`)
- Format dates and numbers with `#temporals` and `#numbers`
- Bind a form to an object with `th:object` and `th:field`, and handle it with `@ModelAttribute`
- Understand why `th:text` escapes HTML, and when `th:utext` is dangerous

## 🧰 What you'll use

**Links built from data.** `@{/posts/{id}(id=${post.id})}` becomes `/posts/7`, with the app's context path added if it has one:

```html
<article th:each="post, row : ${posts}" th:classappend="${row.first} ? 'lead-story'">
    <h2><a th:href="@{/posts/{id}(id=${post.id})}" th:text="${post.title}">Title</a></h2>
    <small th:text="${#temporals.format(post.published, 'd MMM yyyy')}">3 Nov 2026</small>
</article>
<p th:if="${#lists.isEmpty(posts)}">No stories yet.</p>
```

**A form bound to an object.** `th:object` names the object; `th:field="*{title}"` fills in the input's `name`, `id` and current value:

```html
<form th:action="@{/posts}" th:object="${post}" method="post">
    <input th:field="*{title}">
    <textarea th:field="*{body}"></textarea>
    <button>Publish</button>
</form>
```

```java
@PostMapping("/posts")
public String create(@ModelAttribute Post post) {
    postService.save(post);
    return "redirect:/posts";     // after a POST, send the browser to a GET page
}
```

**Escaping is a feature.** If a reader names themselves `<script>alert('hi')</script>`, `th:text` prints those characters as text. `th:utext` would run it in every visitor's browser. Use `th:utext` only for HTML you wrote yourself.

**Redirect after POST.** Returning `redirect:/posts` means refreshing the page doesn't send the story a second time.

## 🪜 Steps

Each exercise is a small Maven project with its own README. The starter compiles and its tests fail until you're done; `solution/` holds one answer for each file you change, at the same path. Run `mvn test` inside the exercise folder.

1. **[Exercise 1: Harambee cards](exercise1/README.md)** (`exercise1/`): variable and text expressions, dates and numbers, links built from ids, and why `th:text` escapes what people type.
2. **[Exercise 2: A Sacco member's page](exercise2/README.md)** (`exercise2/`): `th:switch`, `th:if`/`th:unless`, and `th:each` with its row counter and a class on some rows.
3. **Project: Mtaa News** (your own new project, with `spring-boot-starter-web` and `spring-boot-starter-thymeleaf`).
   1. A `Post` (id, title, section, body, published date) and a `PostService` that keeps them in memory, newest first.
   2. `GET /posts`: the list, with dates, sections and an empty message.
   3. `GET /posts/{id}`: one story.
   4. `GET /posts/new` and `POST /posts`: write a story; `GET /posts/{id}/edit` and `POST /posts/{id}`: change one.
   5. A header and footer fragment on every page, and MockMvc tests for each address.

Keep this project: Day 59 upgrades it.

## 🧪 Run it and test it

Needs JDK 21 and Maven. For an exercise:

```bash
cd day57/exercise1
mvn test
mvn spring-boot:run
```

Then open http://localhost:8080/card/1. Stop it with Ctrl+C.

## 🆘 Stuck?

- **`Neither BindingResult nor plain target object for bean name 'post'`**: the form page needs an object to bind to. Add `model.addAttribute("post", new Post())` in the `GET /posts/new` method.
- **The date prints as `2026-11-03T09:15`**: format it with `#temporals.format(...)`.
- **A link comes out as `/posts/{id}`**: the value goes in brackets after the path: `@{/posts/{id}(id=${post.id})}`.
- **Refreshing after "Publish" posts the story twice**: return `redirect:/posts` from the POST method, not the template name.
- **Every field arrives empty**: the `name` attributes don't match the object's fields. `th:field` sets them for you.

## ✅ Checklist
- [ ] Used `th:if`, `th:unless`, `th:switch` and `th:each` with its status
- [ ] Formatted dates and numbers in a template
- [ ] Built links from ids with `@{...}`
- [ ] A form that creates and edits stories, with redirect after POST
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 58 makes pages look good on any screen with Bootstrap: grids, cards, navbars and forms.
