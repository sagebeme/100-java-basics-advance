# Day 54 - Introduction to Spring Boot

## Learning Objectives
- Understand Spring Framework basics
- Set up a Spring Boot project
- Create REST controllers
- Understand dependency injection
- Build a simple web application

## Topics Covered
- Spring Boot fundamentals
- Dependency Injection
- Spring Boot Starter
- REST Controllers
- Application properties
- Auto-configuration

## Project: Hello Spring Boot

Create your first Spring Boot application with REST endpoints.

### Requirements
- Set up Spring Boot project
- Create a REST controller
- Define multiple endpoints
- Return JSON responses
- Use Spring Boot DevTools

### Endpoints to Create
- `GET /` - Welcome message
- `GET /hello` - Simple hello endpoint
- `GET /hello/{name}` - Personalized greeting
- `GET /api/info` - Application information

## Code Structure
```
day54/
├── README.md
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── learning/
│       │           ├── Application.java
│       │           └── controller/
│       │               └── HelloController.java
│       └── resources/
│           └── application.properties
└── pom.xml
```

## Key Concepts
- **Spring Boot**: Framework for building Java applications
- **Dependency Injection**: Inversion of control pattern
- **REST API**: Representational State Transfer
- **@RestController**: Annotation for REST endpoints
- **@RequestMapping**: URL mapping annotation

## Example Controller
```java
@RestController
public class HelloController {
    
    @GetMapping("/")
    public String home() {
        return "Welcome to Spring Boot!";
    }
    
    @GetMapping("/hello/{name}")
    public Map<String, String> hello(@PathVariable String name) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, " + name + "!");
        return response;
    }
}
```

## Next Steps
After completing Day 54, you should understand:
- Spring Boot basics
- REST API creation
- Dependency injection
- Spring Boot configuration
- Building web applications


