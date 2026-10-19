# Day 66 - Building RESTful APIs

## 📚 Learning Objectives
- Design RESTful APIs
- Implement API best practices
- Handle API versioning
- Choose the right method (safe, idempotent, PUT vs PATCH) and status code
- Create API documentation
- Build production-ready APIs

## 🎯 Topics Covered
- REST principles
- API design
- HTTP status codes
- API documentation
- Error handling
- API security

## 📝 Step-by-Step Instructions

### Step 1: One resource, one path, the method says what to do

A REST API names *things* (resources) in its paths and uses the HTTP **method** for the action. No `/getProducts` or `/deleteProduct?id=3`:

| Method and path | Does | Success | Can also answer |
|---|---|---|---|
| `GET /api/v1/products` | list | `200` | |
| `GET /api/v1/products/{id}` | read one | `200` | `404` |
| `POST /api/v1/products` | create | `201` + `Location` | `400`, `409`, `415`, `422` |
| `PUT /api/v1/products/{id}` | replace it whole | `200` | `400`, `404`, `409`, `422` |
| `PATCH /api/v1/products/{id}` | change some fields | `200` | `404`, `409`, `422` |
| `DELETE /api/v1/products/{id}` | remove | `204` | `404` |

The `v1` in the path is the **version**. When you must change the API in a way that breaks existing apps, you add `/api/v2` and keep `v1` running until they've moved.

### Step 2: Safe and idempotent

- **Safe** methods (`GET`, `HEAD`, `OPTIONS`) change nothing. Browsers and crawlers call them freely, so a `GET` must never delete anything.
- **Idempotent** methods give the same result whether you send them once or twice: `GET`, `PUT`, `DELETE`. That makes them safe to **retry** after a dropped connection. `POST` is not: "create an order" sent twice makes two orders.

The tests send the same `PUT` twice and get the same product, and delete a product twice: the second `DELETE` answers `404`, but the product is just as gone. Idempotent means the *state* is the same, not the response.

### Step 3: PUT or PATCH?

`PUT` sends the **whole** product. Leave out the price and the request is refused, because a `PUT` can't guess the missing field. `PATCH` sends **only what changes**:

```java
// null means "not sent, leave it alone"
public record ProductPatch(
        @Pattern(regexp = ".*\\S.*", message = "name can't be blank") String name,
        @Positive(message = "price must be positive") Double price) {
}

@PatchMapping("/{id}")
public ResponseEntity<Product> patchProduct(@PathVariable Long id, @Valid @RequestBody ProductPatch patch) {
    Product product = find(id);
    if (patch.name() != null) product.setName(patch.name());
    if (patch.price() != null) product.setPrice(patch.price());
    return ResponseEntity.ok(productRepository.save(product));
}
```

Note the boxed `Double`, not `double`: a primitive can't be `null`, so you couldn't tell "not sent" from zero.

### Step 4: Say where a new thing lives

`201 Created` means "I made it". The `Location` header says where:

```java
URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
return ResponseEntity.created(location).body(saved);
```

### Step 5: Pick the status code that tells the client what to fix

One `@RestControllerAdvice` turns exceptions into the same JSON error shape (`status`, `message`, `timestamp`):

| Code | When | What the client should do |
|---|---|---|
| `400 Bad Request` | the body can't be read: broken JSON, a missing field Jackson can't fill | fix the request format |
| `404 Not Found` | no product with that id | check the id |
| `405 Method Not Allowed` | `DELETE /api/v1/products` (no id). Spring answers this itself, with an `Allow` header listing what *is* allowed | use another method |
| `409 Conflict` | a product with that name already exists | pick another name, or edit the existing one |
| `415 Unsupported Media Type` | the body isn't JSON. Spring answers this itself | send `Content-Type: application/json` |
| `422 Unprocessable Content` | it was read fine but breaks a rule: a blank name, a negative price | fix the values |

`400` or `422`? `400` when the request can't even be read, `422` when the values are wrong. Some APIs use `400` for both; whichever you pick, be consistent.

The full list of methods and status codes, from `1xx` to `5xx`, with when to use each: [HTTP: methods and status codes](https://github.com/sagebeme/100-Days-of-TypeScript/blob/main/reference/http.md) (from the 100 Days of TypeScript course; the HTTP part is the same in every language).

### Step 6: Document the API

Other developers need to know your endpoints. Add [springdoc-openapi](https://springdoc.org/) (`org.springdoc:springdoc-openapi-starter-webmvc-ui`, a version that supports your Spring Boot) and it generates an OpenAPI description and a Swagger UI page at `/swagger-ui.html` from your controllers. Older tutorials show `Docket` from springfox: that project is abandoned and doesn't run on current Spring Boot.

## 🎮 Project: RESTful API

### Requirements
Build the product API above:
1. Resource paths under `/api/v1`, with the method choosing the action
2. `201` with a `Location` header on create, `204` on delete
3. Both `PUT` (whole) and `PATCH` (some fields)
4. `400`, `404`, `409` and `422` in one JSON shape, from one exception handler
5. Tests for every status code, plus a `PUT` and a `DELETE` sent twice

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE.

## ✅ Checklist
- [ ] Can design REST APIs
- [ ] Can use proper HTTP methods, and explain safe, idempotent, and PUT vs PATCH
- [ ] Can pick between 400, 404, 405, 409, 415 and 422
- [ ] Can handle errors
- [ ] Can document APIs
- [ ] Completed RESTful API
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/ProductRestController.java` | A REST API at `/api/v1/products`: list, get, create (with `Location`), replace (`PUT`), change some fields (`PATCH`), delete |
| `exception/ApiError.java` | The JSON error body: status, message and timestamp |
| `exception/ApiExceptionHandler.java` | Turns errors into `400` (unreadable), `404`, `409` (duplicate name) and `422` (broken rules), all in one JSON shape |
| `exception/DuplicateProductException.java` | Thrown when another product already has that name |
| `exception/ProductNotFoundException.java` | Thrown when a product id doesn't exist |
| `model/Product.java` | A JPA entity with validation rules |
| `model/ProductPatch.java` | The fields a `PATCH` may change; `null` means "leave it alone" |
| `repository/ProductRepository.java` | Spring Data repository for products, with a case-insensitive name lookup |

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day66
mvn spring-boot:run
```

Then open http://localhost:8080/api/v1/products. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day66
mvn test
```

Runs `ProductRestControllerTest` (16 tests, one or more for every status code above). A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 67?** You'll add RESTful routing to your blog!
