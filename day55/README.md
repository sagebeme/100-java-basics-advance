# Day 55 - A REST API: Create, Read, Update, Delete

> Yesterday's server could only say hello. Today's keeps a list of things, lets an app add, change and remove them, and answers with the status code a client expects.

## 📖 The story

Almost every app on your phone is a screen on top of an **API**: the M-Pesa app asks Safaricom's servers for your balance, a ride app asks for nearby drivers, a shop app asks for today's prices. The app and the server agree on a small set of requests:

| Request | Means |
|---|---|
| `GET /api/todos` | give me all of them |
| `GET /api/todos/3` | give me number 3 |
| `POST /api/todos` | here's a new one, store it |
| `PUT /api/todos/3` | replace number 3 with this |
| `DELETE /api/todos/3` | remove number 3 |

That's **REST**: things ("resources") at addresses, and the HTTP method saying what to do with them. Create, read, update and delete, known as **CRUD**, cover most of what apps need.

The exercises build two real ones, for Mama Njeri's duka and for a chama. Then you build your own from scratch: a to-do list API, so Wanjiru's family can stop arguing about whose turn it is to fetch water.

## 🎯 Learning Objectives
- Map the five CRUD operations with `@GetMapping`, `@PostMapping`, `@PutMapping` and `@DeleteMapping`
- Read JSON from the request with `@RequestBody`, and parts of the address with `@PathVariable` and `@RequestParam`
- Answer with the right status code using `ResponseEntity`: 200, 201 with a `Location`, 204 and 404
- Validate input with Bean Validation (`@NotBlank`, `@Positive`, `@Valid`)
- Turn exceptions into clean error answers with `@RestControllerAdvice`
- Test an API with MockMvc

## 🧰 What you'll use

**A controller for one resource.** `@RequestMapping` sets the shared start of every address:

```java
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @GetMapping("/{id}")
    public ResponseEntity<Todo> get(@PathVariable long id) {
        Todo todo = todos.get(id);
        return todo == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(todo);
    }

    @PostMapping
    public ResponseEntity<Todo> create(@Valid @RequestBody Todo todo) {
        Todo saved = store(todo);
        return ResponseEntity.created(URI.create("/api/todos/" + saved.getId())).body(saved);
    }
}
```

**Status codes are part of the answer.** `201 Created` says "made, and the `Location` header says where". `204 No Content` says "done, nothing to show". `404` says "no such thing". `400` says "your request was wrong", and the body should say how. Answering `200` to everything makes every client read the body to find out what happened.

**Validation.** Put rules on the fields (`@NotBlank String title`), put `@Valid` on the `@RequestBody`, and Spring refuses bad input before your method runs. A `@RestControllerAdvice` class decides what that refusal looks like.

**Storing things for now.** A `ConcurrentHashMap<Long, Todo>` and an `AtomicLong` for the next id. Many requests can arrive at once, which is why they're the thread-safe versions. Everything is lost when the server stops; a real database arrives on Day 63.

## 🪜 Steps

Each exercise is a small Maven project with its own README. The starter compiles and its tests fail until you're done; `solution/` holds one answer for each file you change, at the same path. Run `mvn test` inside the exercise folder.

1. **[Exercise 1: Mama Njeri's stock API](exercise1/README.md)** (`exercise1/`): the five CRUD endpoints with the status codes a client relies on: 201 with a `Location`, 204, and 404.
2. **[Exercise 2: A chama's members API that says what's wrong](exercise2/README.md)** (`exercise2/`): Bean Validation rules, a `@RestControllerAdvice` that turns exceptions into 400, 404 and 409 answers, and a `?name=` search.
3. **Project: Kazi, a to-do API** (your own new project). There's no starter: copy Day 54's `pom.xml` and `Application.java` into a new folder and build it.
   1. A `Todo` with an `id`, a `title` (not blank), an `assignee` and `completed`.
   2. `GET /api/todos`, `GET /api/todos/{id}`, `POST`, `PUT /api/todos/{id}` and `DELETE /api/todos/{id}`, with the status codes from Exercise 1.
   3. `GET /api/todos?completed=false` and `?assignee=Wanjiru` filters, with `@RequestParam(required = false)`.
   4. A `@RestControllerAdvice` that answers 400 with the field errors, as in Exercise 2.
   5. MockMvc tests for each endpoint, including the 404s and a 400.

## 🧪 Run it and test it

Needs JDK 21 and Maven. For an exercise:

```bash
cd day55/exercise1
mvn test
mvn spring-boot:run
```

While it runs, try it from another terminal:

```bash
curl -i http://localhost:8080/api/items
curl -i -X POST http://localhost:8080/api/items -H "Content-Type: application/json" -d '{"name":"Unga 2kg","shillings":210}'
```

`-i` shows the status line and headers, which is where the interesting part of today is.

## 🆘 Stuck?

- **`415 Unsupported Media Type`**: the request has no `Content-Type: application/json` header.
- **`400` with no explanation**: validation failed and nothing turned it into a useful body. That's the job of the `@RestControllerAdvice` in Exercise 2.
- **The POST works, but the new item has id 0**: you returned the item you were sent, not the one you stored with its new id.
- **`@Valid` does nothing**: the project needs `spring-boot-starter-validation` in `pom.xml`.
- **Two quick POSTs got the same id**: use `AtomicLong.incrementAndGet()`, not `count++`.

## ✅ Checklist
- [ ] Wrote all five CRUD endpoints
- [ ] 201 with a `Location`, 204 and 404 where they belong
- [ ] Bad input gets a 400 that says what's wrong
- [ ] Built the to-do API with filters
- [ ] Finished both exercises, and the tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 56 serves web pages as well as JSON: static files, and Thymeleaf templates that share a header and footer.
