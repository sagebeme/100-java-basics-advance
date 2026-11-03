# Day 67 - Blog Capstone Part 3: A REST API for Mtaa News

> Mtaa News has web pages. Now a volunteer wants to build an Android app for it, and the app doesn't want HTML; it wants JSON. Today the blog gets an API next to its pages.

## 📖 The story

Mtaa News (Days 57 and 59) is doing well in Kayole. Brian, a university student who reads it every morning, offers to build an Android app so people get stories without opening a browser. He asks Kevo for "an API": addresses that send and receive stories as JSON.

```
GET    /api/posts        every story
GET    /api/posts/7      story 7, or 404
POST   /api/posts        a new story → 201, and Location: /api/posts/8
PUT    /api/posts/7      replace story 7's title and content, or 404
DELETE /api/posts/7      → 204, or 404
```

The routes follow **REST** conventions, so Brian can guess them without asking: the noun (`posts`) is in the address, the HTTP method is the verb, and an id picks one.

There's one trap that every first API falls into. A new story arrives as JSON and is turned straight into a `Post`, and a `Post` has an `id` field. If the app (or an attacker) sends `{"id": 1, "title": "Hijacked", ...}`, saving it *overwrites story 1*. That's **over-posting**, and the fix is one line: the server, not the client, decides a new story's id.

## 🎯 Learning Objectives
- Design RESTful routes: nouns in the path, methods as verbs, ids for one item
- Return `ResponseEntity` with the right status for each outcome: 200, 201, 204, 400 and 404
- Use `Optional.map(...).orElse(...)` to answer found and not-found in one expression
- Validate request bodies with `@Valid`
- Stop over-posting: never let the request body decide fields the server owns
- Test an API end to end with MockMvc and Jackson's `ObjectMapper`

## 🧰 What you'll use

**Found or not found, in one expression:**

```java
@GetMapping("/{id}")
public ResponseEntity<Post> getPost(@PathVariable Long id) {
    return postRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}
```

**Creating, safely:**

```java
@PostMapping
public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
    post.setId(null);                                   // the server picks the id
    Post saved = postRepository.save(post);
    return ResponseEntity.created(URI.create("/api/posts/" + saved.getId())).body(saved);
}
```

A tidier fix in bigger apps is a separate request class (a "DTO") with only the fields a client may send: `record NewPost(String title, String content)`. Then there's no `id` to abuse at all.

**JSON in tests.** `ObjectMapper` (Jackson 3, package `tools.jackson.databind`) turns objects into JSON and reads values back out: `objectMapper.readTree(body).get("id").asLong()`.

## 🪜 Steps

**Project: The Mtaa News API** (the `day67/` folder is a finished version: build your own first, then compare). Add it to your Day 59 project if you like, next to the page controllers.

1. `Post`: an entity with a `@NotBlank` title and content.
2. `PostRestController` at `/api/posts`, with the five routes and their status codes.
3. Clear the id on create, and return a `Location` header.
4. Tests: list, get, a 404, a missing title, an update, a 201 with its `Location`, and a body with someone else's id that must not overwrite them.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day67
mvn spring-boot:run
```

From another terminal:

```bash
curl -i -X POST localhost:8080/api/posts -H "Content-Type: application/json" -d '{"title":"Footbridge opens","content":"At last."}'
curl -i localhost:8080/api/posts
```

```bash
cd day67
mvn test
```

## 🆘 Stuck?

- **POST answers 200, not 201**: `ResponseEntity.ok(...)` is always 200. Use `ResponseEntity.created(location).body(saved)`.
- **A POST changed an existing story**: the body carried an `id`. Clear it before saving, or use a request record without one.
- **`400` with an empty body when the title is missing**: that's validation working. Day 66's `@RestControllerAdvice` shows how to say *what* is missing.
- **`InvalidDefinitionException: Cannot construct instance of Post`**: Jackson and JPA both need a constructor with no arguments.

## ✅ Checklist
- [ ] Five RESTful routes with the right status codes
- [ ] 201 with a `Location` on create
- [ ] A body with an `id` can't overwrite another post
- [ ] Bad input is refused with 400
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 68 adds accounts: registering, logging in with Spring Security, passwords stored as hashes, and then tokens for apps like Brian's.
