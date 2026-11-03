# Day 88 - Portfolio Project: Todo List Application

> The to-do list is every developer's first full app, and that's exactly why employers look at it: they've seen a hundred and can spot the one built carefully. Today's is small, but every edge is handled.

## 📖 The story

Halima is a nurse at a hospital in Garissa, working long, unpredictable shifts. Sticky notes on the fridge keep falling off: pay the KPLC bill, renew the practising licence, call her mother. She wants a simple list on her phone: type a task, tick it off, see what's left, and clear things away.

```
Todo List
[ What needs doing?            ] [Add]
 All | Active | Completed

 ☐ Renew the practising licence          🗑
 ☑ Pay the KPLC bill                      🗑
```

No JavaScript framework, no app store: just plain HTML forms, which work on any phone.

## 🎯 Learning Objectives
- Build create, update and delete with HTML forms and `POST` + redirect
- Filter a list with an enum request parameter, and keep the filter after every action
- Write derived queries that sort: `findByCompletedOrderByCreatedAtDesc`
- Validate input in the service, and turn failures into friendly messages
- Answer 404 for a todo that doesn't exist, from one `@ControllerAdvice`
- Test the service with mocks, and the whole app with MockMvc and a real database

## 🧰 What you'll use

**Forms for every action.** Browsers' forms can only send `GET` and `POST`, so the "verbs" go in the URL: `POST /todos`, `POST /todos/{id}/toggle`, `POST /todos/{id}/delete`. Each one ends in a redirect back to the list (Post/Redirect/Get), so refreshing never repeats it.

**Keeping the filter.** Each form sends the current filter along in a hidden field, and the redirect goes back to it:

```java
@PostMapping("/todos/{id}/toggle")
public String toggle(@PathVariable Long id, @RequestParam(defaultValue = "ALL") TodoFilter filter) {
    todoService.toggleComplete(id);
    return "redirect:/?filter=" + filter;
}
```

**Validation with a message.** The browser's `required` stops an empty box, but not a box of spaces, and nothing stops someone posting directly. The service refuses blank or overlong titles, and the controller turns that into a flash message instead of a 500 page:

```java
try {
    todoService.addTodo(title);
} catch (IllegalArgumentException e) {
    redirectAttributes.addFlashAttribute("error", e.getMessage());
}
return "redirect:/";
```

**404 in one place.** `TodoExceptionHandler` maps `NoSuchElementException` to 404, so ticking a todo that was already deleted in another tab gives "not found", not a crash.

## 🪜 Steps

**Your brief** (the `day88/` folder is one finished version: build your own first, then compare).

1. `Todo` (title, completed, createdAt) and `TodoRepository` with the two sorted queries.
2. `TodoFilter` and `TodoService`: add (trimmed, not blank, at most 200 characters), list, toggle, delete.
3. `TodoController` and the page, with the filter buttons and an error message.
4. `TodoExceptionHandler` for 404.
5. Tests: the service with a mocked repository, and the app end to end: add, toggle, delete, filter, a missing todo, and blank and overlong titles.
6. Make it yours: due dates (and overdue ones in red), editing a title, or a "clear completed" button.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day88
mvn test
mvn spring-boot:run
```

Open <http://localhost:8080>.

## 🆘 Stuck?

- **The filter resets to All after ticking a box**: send the filter with the form and include it in the redirect.
- **A space-only todo appears**: check `isBlank()` on the server; `required` in the browser isn't enough.
- **Ticking the checkbox does nothing**: a checkbox doesn't submit by itself. `onchange="this.form.submit()"` sends its form.
- **The newest todos are at the bottom**: sort in the query: `OrderByCreatedAtDesc`.

## ✅ Checklist
- [ ] Add, complete, filter and delete, all with forms
- [ ] Blank and overlong titles refused with a message
- [ ] 404 for a todo that doesn't exist
- [ ] Unit and end-to-end tests pass
- [ ] Added due dates, editing or "clear completed"
- [ ] Committed code to Git

## ➡️ Next

Day 89 is a writing app with a twist: stop typing for eight seconds and everything you wrote disappears.
