# Day 66 - RESTful APIs Done Properly: PUT, PATCH and Honest Errors

> On Day 55 you built your first CRUD API. Today's is the kind another team can build an app on without asking you questions: versioned, consistent errors, and PUT and PATCH that each do exactly what they promise.

## 📖 The story

Juma runs a wholesale shop in Eastleigh, and three different people are building things on his product list: a website, a till app and a WhatsApp bot. Each of them has hit the same problems with his first API:

- The till app changed a price with `PUT` and sent only `{"price": 300}`. The product's name vanished.
- Two people added "Unga 2kg" on the same afternoon, and now there are two.
- When something went wrong, sometimes the answer was a JSON error, sometimes an HTML error page, sometimes a stack trace.

Today's API fixes all three:

- `/api/v1/products`: the `v1` means a future `v2` can change things without breaking the apps built on `v1`;
- **PUT** replaces the whole product, so you must send every field, and sending the same PUT twice leaves the same result;
- **PATCH** changes only the fields you send: `{"price": 300}` leaves the name alone;
- a second product with the same name (ignoring capitals) gets **409 Conflict**;
- every error, from any endpoint, has the same JSON shape: `{"status": 404, "message": "No product with id 9"}`.

## 🎯 Learning Objectives
- Version an API in its path, and know why clients need that
- Know the difference between PUT (replace) and PATCH (change some fields), and which methods are idempotent
- Use a record for a PATCH body, where `null` means "not sent"
- Validate request bodies, and answer 400, 404, 409 and 422 for the right reasons
- Give every error the same shape with one `@RestControllerAdvice`
- Build a `Location` header from the current request

## 🧰 What you'll use

**PUT vs PATCH:**

```java
@PutMapping("/{id}")        // every field: a missing one is replaced by nothing
public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product updated) { ... }

@PatchMapping("/{id}")      // only what's sent
public ResponseEntity<Product> patchProduct(@PathVariable Long id, @Valid @RequestBody ProductPatch patch) {
    Product product = find(id);
    if (patch.name() != null) product.setName(patch.name());
    if (patch.price() != null) product.setPrice(patch.price());
    return ResponseEntity.ok(productRepository.save(product));
}
```

`ProductPatch` is a record with `String name` and `Double price`: the wrapper `Double`, not `double`, so "not sent" can be `null` rather than `0`.

**Idempotent** means doing it twice leaves things the same as doing it once. GET, PUT and DELETE are idempotent; POST isn't (two POSTs make two products). Phones on bad networks retry requests, so this matters.

**One error shape.** `ApiExceptionHandler` catches each exception type and answers with an `ApiError`:

| Exception | Status | When |
|---|---|---|
| `ProductNotFoundException` | 404 | no product with that id |
| `DuplicateProductException` | 409 | the name is already taken |
| `HttpMessageNotReadableException` | 400 | the body isn't valid JSON |
| `MethodArgumentNotValidException` | 422 | valid JSON, but it breaks a rule |

## 🪜 Steps

**Project: Juma's Products API** (the `day66/` folder is a finished version: build your own first, then compare).

1. `Product` (an entity with a `@NotBlank` name and a `@Positive` price) and `ProductRepository` with `existsByNameIgnoreCase` and `findByNameIgnoreCase`.
2. The six endpoints under `/api/v1/products`: list, get, POST (201 and `Location`), PUT, PATCH and DELETE (204).
3. `ProductPatch`, with the same rules as `Product` where a value is sent.
4. The exceptions and `ApiExceptionHandler`.
5. Tests: the same PUT twice, a PUT that loses a missing field, a PATCH that keeps it, and a PATCH that breaks a rule.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day66
mvn spring-boot:run
```

Then, from another terminal:

```bash
curl -i -X POST localhost:8080/api/v1/products -H "Content-Type: application/json" -d '{"name":"Unga 2kg","price":210}'
curl -i -X PATCH localhost:8080/api/v1/products/1 -H "Content-Type: application/json" -d '{"price":220}'
curl -i localhost:8080/api/v1/products/99
```

```bash
cd day66
mvn test
```

## 🆘 Stuck?

- **A PATCH sets the price to 0**: the patch's `price` is a `double`, which can't be `null`. Use `Double`.
- **PATCH `{"name": ""}` is accepted**: `@NotBlank` fails on `null` too, so it can't go on a PATCH field. `@Pattern(regexp = ".*\\S.*")` allows `null` (not sent) but refuses blank.
- **Renaming a product to its own name gives 409**: the duplicate check found the product itself. Ignore a match with the same id.
- **Errors come back as an HTML page**: the exception isn't handled in `ApiExceptionHandler`. Add a handler for it.
- **Prices like 209.99999**: `double` can't hold most decimal fractions exactly. Here prices are whole numbers; for real money keep whole shillings (or cents) in a `long`, or use `BigDecimal`.

## ✅ Checklist
- [ ] A versioned API with all six endpoints
- [ ] PUT replaces, PATCH changes only what's sent
- [ ] 201 with a `Location`, 204, 400, 404, 409 and 422 where they belong
- [ ] Every error has the same JSON shape
- [ ] The tests pass
- [ ] Committed code to Git

## ➡️ Next

Day 67 gives the blog its own REST API, so apps can read and write posts too.
