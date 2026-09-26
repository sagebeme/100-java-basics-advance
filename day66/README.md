# Day 66 - Building RESTful APIs

## 📚 Learning Objectives
- Design RESTful APIs
- Implement API best practices
- Handle API versioning
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

### Step 1: RESTful Design
Design proper REST endpoints:

```java
@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.getUserById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.save(user));
    }
}
```

### Step 2: API Documentation
Add Swagger/OpenAPI:

```java
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
            .build();
    }
}
```

## 🎮 Project: RESTful API

### Requirements
Create RESTful API:
1. Proper endpoints
2. HTTP methods
3. Status codes
4. Error handling
5. API documentation

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE.

## ✅ Checklist
- [ ] Can design REST APIs
- [ ] Can use proper HTTP methods
- [ ] Can handle errors
- [ ] Can document APIs
- [ ] Completed RESTful API
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/ProductRestController.java` | A REST API at `/api/v1/products`: list, get, create, update, delete |
| `exception/ApiError.java` | The JSON error body: status, message and timestamp |
| `exception/ApiExceptionHandler.java` | Turns not-found and validation errors into 404 and 400 responses |
| `exception/ProductNotFoundException.java` | Thrown when a product id doesn't exist |
| `model/Product.java` | A JPA entity with validation rules |
| `repository/ProductRepository.java` | Spring Data repository for products |

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

Runs `ProductRestControllerTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 67?** You'll add RESTful routing to your blog!
