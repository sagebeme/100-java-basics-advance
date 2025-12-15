# Day 57 - Thymeleaf Templating

## 📚 Learning Objectives
- Master Thymeleaf template engine
- Work with Thymeleaf syntax
- Create dynamic templates
- Use Thymeleaf features
- Build template-based applications

## 🎯 Topics Covered
- Thymeleaf syntax
- Expression language
- Conditionals and loops
- Form handling
- Layout fragments
- Internationalization

## 📝 Step-by-Step Instructions

### Step 1: Basic Thymeleaf Template
Create template with variables:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title th:text="${title}">Default Title</title>
</head>
<body>
    <h1 th:text="${message}">Default Message</h1>
    <p th:text="'Hello, ' + ${name}">Hello, User</p>
</body>
</html>
```

### Step 2: Conditionals and Loops
Use conditionals and loops:

```html
<div th:if="${user != null}">
    <p>Welcome, <span th:text="${user.name}">User</span>!</p>
</div>

<div th:unless="${user != null}">
    <p>Please log in</p>
</div>

<ul>
    <li th:each="item : ${items}" th:text="${item.name}">Item</li>
</ul>

<table>
    <tr th:each="user : ${users}">
        <td th:text="${user.id}">1</td>
        <td th:text="${user.name}">John</td>
        <td th:text="${user.email}">john@example.com</td>
    </tr>
</table>
```

### Step 3: Form Handling
Create forms:

```html
<form th:action="@{/users}" th:object="${user}" method="post">
    <input type="text" th:field="*{name}" placeholder="Name">
    <input type="email" th:field="*{email}" placeholder="Email">
    <button type="submit">Submit</button>
</form>
```

### Step 4: Layout Fragments
Use fragments for reusable components:

**fragments/header.html:**
```html
<div th:fragment="header">
    <nav>
        <a th:href="@{/}">Home</a>
        <a th:href="@{/about}">About</a>
    </nav>
</div>
```

**index.html:**
```html
<div th:replace="fragments/header :: header"></div>
```

### Step 5: Controller Integration
Pass data to templates:

```java
@Controller
public class UserController {
    
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("title", "User List");
        return "users";
    }
    
    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-detail";
    }
}
```

## 💻 Exercises

### Exercise 1: Basic Templates
Create templates with:
- Variables
- Text expressions
- Attributes
- Links

### Exercise 2: Conditionals
Use:
- th:if
- th:unless
- th:switch
- Complex conditions

### Exercise 3: Loops
Implement:
- th:each
- Iteration status
- Nested loops
- List rendering

## 🎮 Project: Blog with Thymeleaf

### Requirements
Create a blog using Thymeleaf:
1. List all blog posts
2. Display post details
3. Create new posts (form)
4. Edit posts
5. Use layout fragments
6. Responsive design

### Example Templates
**templates/posts.html:**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title th:text="${title}">Blog Posts</title>
</head>
<body>
    <div th:replace="fragments/header :: header"></div>
    
    <div class="container">
        <h1>Blog Posts</h1>
        <div th:each="post : ${posts}">
            <h2 th:text="${post.title}">Post Title</h2>
            <p th:text="${post.content}">Post content...</p>
            <a th:href="@{/posts/{id}(id=${post.id})}">Read more</a>
        </div>
    </div>
    
    <div th:replace="fragments/footer :: footer"></div>
</body>
</html>
```

### Starter Code
```java
@Controller
public class BlogController {
    
    @GetMapping("/posts")
    public String listPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }
}
```

## 📚 Resources

### Official Documentation
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
- [Thymeleaf Tutorial](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)
- [Spring Boot Thymeleaf](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-spring-mvc-template-engines)

### Tutorials
- [Thymeleaf Guide - Baeldung](https://www.baeldung.com/thymeleaf-in-spring-mvc)
- [Thymeleaf Basics](https://www.geeksforgeeks.org/spring-boot-thymeleaf/)

### Video Resources
- [Thymeleaf Tutorial](https://www.youtube.com/results?search_query=thymeleaf+tutorial)
- [Spring Boot Thymeleaf](https://www.youtube.com/results?search_query=spring+boot+thymeleaf)

### Practice Platforms
- [Thymeleaf Examples](https://github.com/thymeleaf/thymeleafexamples)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Template Engine | Render dynamic HTML | Thymeleaf |
| Expression | Access data | `${variable}` |
| Conditionals | If/else logic | `th:if="${condition}"` |
| Loops | Iterate collections | `th:each="item : ${items}"` |
| Fragments | Reusable components | `th:fragment`, `th:replace` |

## ✅ Checklist
- [ ] Understand Thymeleaf syntax
- [ ] Can use expressions
- [ ] Can use conditionals
- [ ] Can use loops
- [ ] Can create forms
- [ ] Completed Blog with Thymeleaf
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 57, you should be able to:
- Create dynamic templates
- Use Thymeleaf features
- Build template-based apps
- Work with forms

**Ready for Day 58?** You'll integrate Bootstrap with your Spring Boot app!


