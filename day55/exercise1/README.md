# Day 55 - Exercise 1: Mama Njeri's stock API

Mama Njeri runs a duka in Githurai. Her nephew is building her a phone app to keep track of what's on the shelves and at what price, and the app needs an API to talk to. Write it: a Spring Boot `@RestController` with the five standard operations on `/api/items`.

`Item` (already written) has an `id`, a `name` and a price in whole `shillings`. Items live in a map in memory, so they're gone when the app stops. A database comes on Day 63.

| Request | Answer |
|---|---|
| `GET /api/items` | `200` and every item, in id order. Done for you, as an example. |
| `GET /api/items/7` | `200` and item 7, or `404 Not Found`. |
| `POST /api/items` with `{"name":"Unga 2kg","shillings":210}` | `201 Created`, a `Location: /api/items/8` header saying where the new item lives, and the item with its new id. |
| `PUT /api/items/7` with a name and price | `200` and the changed item, or `404`. |
| `DELETE /api/items/7` | `204 No Content`, or `404` if it isn't there. |

The status codes are the point. A client app looks at the number before anything else: `201` means "made, and here's where", `204` means "done, nothing to show", `404` means "no such thing". Answering `200` to everything forces every client to read the body to find out what happened.

## Files

- `src/main/java/com/learning/controller/ItemController.java`: your code goes here. Each method has its signature; give it the right mapping annotation and parameter annotations, and fill it in.
- `src/main/java/com/learning/model/Item.java`: done.
- `src/test/java/com/learning/controller/ItemControllerTest.java`: the tests.
- `solution/src/main/java/com/learning/controller/ItemController.java`: one answer, at the same path as the file you change.

```bash
cd day55/exercise1
mvn test
```

To try it by hand, `mvn spring-boot:run`, then in another terminal:

```bash
curl -i -X POST localhost:8080/api/items -H 'Content-Type: application/json' -d '{"name":"Unga 2kg","shillings":210}'
curl -i localhost:8080/api/items/1
```

`-i` shows the status and headers as well as the body.

## 📌 Stuck?

- `@GetMapping("/{id}")` on the method plus `@PathVariable Long id` on the parameter reads the id from the URL. `@RequestBody Item item` turns the JSON body into an `Item`.
- `ResponseEntity.created(URI.create("/api/items/" + id)).body(item)` gives 201 with the `Location` header in one go. `ResponseEntity.notFound().build()` and `ResponseEntity.noContent().build()` are the others.
- `Map.remove(id)` returns what it removed, or `null`, which tells `delete` whether the item was there.
