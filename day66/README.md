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

## ✅ Checklist
- [ ] Can design REST APIs
- [ ] Can use proper HTTP methods
- [ ] Can handle errors
- [ ] Can document APIs
- [ ] Completed RESTful API
- [ ] Committed code to Git

## 🚀 Next Steps
**Ready for Day 67?** You'll add RESTful routing to your blog!






