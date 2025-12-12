# Day 56 - Spring Boot: Static Files and Templates

## 📚 Learning Objectives
- Serve static files in Spring Boot
- Work with templates
- Understand resource handling
- Configure static resources
- Build web applications

## 🎯 Topics Covered
- Static resource configuration
- Template engines
- Resource handlers
- File serving
- CSS and JavaScript
- Images and assets

## 📝 Step-by-Step Instructions

### Step 1: Static Resources Structure
Organize static files:

```
src/main/resources/
├── static/
│   ├── css/
│   │   └── style.css
│   ├── js/
│   │   └── app.js
│   ├── images/
│   │   └── logo.png
│   └── favicon.ico
└── templates/
    └── index.html
```

### Step 2: Serve Static Files
Access static resources:

```java
@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "index"; // Returns templates/index.html
    }
}
```

**templates/index.html:**
```html
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/app.js"></script>
</head>
<body>
    <img src="/images/logo.png" alt="Logo">
    <h1>Welcome</h1>
</body>
</html>
```

### Step 3: Custom Resource Handler
Configure custom paths:

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
            .addResourceLocations("classpath:/static/assets/");
        
        registry.addResourceHandler("/files/**")
            .addResourceLocations("file:/path/to/files/");
    }
}
```

### Step 4: Template Configuration
Configure template engine:

**application.properties:**
```properties
# Thymeleaf configuration
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.cache=false

# Static resources
spring.web.resources.static-locations=classpath:/static/
```

## 💻 Exercises

### Exercise 1: Static Files
Create and serve:
- CSS files
- JavaScript files
- Images
- Fonts

### Exercise 2: Templates
Work with:
- HTML templates
- Template variables
- Template inheritance
- Layout templates

### Exercise 3: Resource Organization
Organize:
- File structure
- Asset management
- Resource paths
- CDN integration

## 🎮 Project: Static Website with Spring Boot

### Requirements
Create a website with static files:
1. HTML templates
2. CSS styling
3. JavaScript functionality
4. Images and assets
5. Proper file organization
6. Resource serving

### Example Structure
```
src/main/resources/
├── static/
│   ├── css/
│   │   ├── bootstrap.min.css
│   │   └── custom.css
│   ├── js/
│   │   ├── jquery.min.js
│   │   └── main.js
│   └── images/
│       └── banner.jpg
└── templates/
    ├── layout.html
    ├── index.html
    └── about.html
```

### Starter Code
```java
@Controller
public class WebController {
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Home");
        return "index";
    }
    
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About");
        return "about";
    }
}
```

## 📚 Resources

### Official Documentation
- [Spring Boot Static Content](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content)
- [Resource Handling](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-static-resources)

### Tutorials
- [Spring Boot Static Resources](https://www.baeldung.com/spring-mvc-static-resources)
- [Template Engines](https://www.baeldung.com/spring-template-engines)

### Video Resources
- [Spring Boot Static Files](https://www.youtube.com/results?search_query=spring+boot+static+files)
- [Spring Boot Templates](https://www.youtube.com/results?search_query=spring+boot+templates)

### Practice Platforms
- [Spring Boot Guides](https://spring.io/guides)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Static Resources | Files served as-is | CSS, JS, images |
| Templates | Dynamic HTML | Thymeleaf, JSP |
| Resource Handler | Configure paths | `addResourceHandlers()` |
| Classpath | Resource location | `classpath:/static/` |
| Template Engine | Render templates | Thymeleaf, Mustache |

## ✅ Checklist
- [ ] Understand static resources
- [ ] Can organize files
- [ ] Can serve static files
- [ ] Can use templates
- [ ] Can configure resources
- [ ] Completed Static Website
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 56, you should be able to:
- Serve static files
- Work with templates
- Organize resources
- Build web applications

**Ready for Day 57?** You'll learn Thymeleaf templating!

