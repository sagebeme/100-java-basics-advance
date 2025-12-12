# Day 55 - Spring Boot REST APIs

## 📚 Learning Objectives
- Build RESTful APIs with Spring Boot
- Create REST controllers
- Handle HTTP methods
- Return JSON responses
- Implement API endpoints

## 🎯 Topics Covered
- REST principles
- @RestController annotation
- HTTP methods (GET, POST, PUT, DELETE)
- Request mapping
- Path variables
- Request parameters
- Response entities

## 📝 Step-by-Step Instructions

### Step 1: REST Controller
Create REST controller:

```java
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class UserController {
    
    private List<User> users = new ArrayList<>();
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return users;
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = users.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .orElse(null);
        
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId((long) (users.size() + 1));
        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        // Update logic
        return ResponseEntity.ok(user);
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        users.removeIf(u -> u.getId().equals(id));
        return ResponseEntity.noContent().build();
    }
}
```

### Step 2: User Model
Create data model:

```java
public class User {
    private Long id;
    private String name;
    private String email;
    
    // Constructors, getters, setters
    public User() {}
    
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    // Getters and setters
}
```

### Step 3: Request Parameters
Handle query parameters:

```java
@GetMapping("/users/search")
public List<User> searchUsers(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String email
) {
    return users.stream()
        .filter(u -> name == null || u.getName().contains(name))
        .filter(u -> email == null || u.getEmail().contains(email))
        .collect(Collectors.toList());
}
```

### Step 4: Exception Handling
Handle errors:

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
```

## 💻 Exercises

### Exercise 1: Basic REST API
Create endpoints for:
- GET all items
- GET by ID
- POST new item
- PUT update item
- DELETE item

### Exercise 2: Advanced Features
Implement:
- Query parameters
- Path variables
- Request body validation
- Error handling

### Exercise 3: API Design
Design:
- RESTful URLs
- HTTP status codes
- Response formats
- API documentation

## 🎮 Project: Todo REST API

### Requirements
Create a Todo REST API:
1. GET /api/todos - Get all todos
2. GET /api/todos/{id} - Get todo by ID
3. POST /api/todos - Create new todo
4. PUT /api/todos/{id} - Update todo
5. DELETE /api/todos/{id} - Delete todo
6. GET /api/todos?completed=true - Filter todos

### Example Endpoints
```
GET /api/todos
Response: [{"id":1,"title":"Learn Java","completed":false}]

POST /api/todos
Body: {"title":"Build API","completed":false}
Response: {"id":2,"title":"Build API","completed":false}

PUT /api/todos/2
Body: {"title":"Build API","completed":true}
Response: {"id":2,"title":"Build API","completed":true}
```

### Starter Code
```java
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private List<Todo> todos = new ArrayList<>();
    
    @GetMapping
    public List<Todo> getAllTodos() {
        return todos;
    }
    
    // Add other endpoints
}
```

## 📚 Resources

### Official Documentation
- [Spring Boot REST](https://spring.io/guides/gs/rest-service/)
- [Spring Web MVC](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
- [REST API Design](https://restfulapi.net/)

### Tutorials
- [Spring Boot REST Tutorial](https://www.baeldung.com/spring-boot-rest-api)
- [REST Controllers](https://www.geeksforgeeks.org/spring-boot-rest-controller/)

### Video Resources
- [Spring Boot REST API](https://www.youtube.com/results?search_query=spring+boot+rest+api+tutorial)
- [REST API Java](https://www.youtube.com/results?search_query=rest+api+java+tutorial)

### Practice Platforms
- [REST API Practice](https://restfulapi.net/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| REST | Architectural style | Resource-based URLs |
| @RestController | REST controller | `@RestController` |
| HTTP Methods | GET, POST, PUT, DELETE | `@GetMapping`, `@PostMapping` |
| Path Variable | URL parameter | `@PathVariable Long id` |
| Request Body | JSON payload | `@RequestBody User user` |

## ✅ Checklist
- [ ] Understand REST principles
- [ ] Can create REST controllers
- [ ] Can handle HTTP methods
- [ ] Can use path variables
- [ ] Can handle errors
- [ ] Completed Todo REST API
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 55, you should be able to:
- Build REST APIs
- Handle HTTP methods
- Return JSON responses
- Create API endpoints

**Ready for Day 56?** You'll learn about static files and templates!

