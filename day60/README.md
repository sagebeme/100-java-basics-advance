# Day 60 - HTML Forms with Spring Boot

## 📚 Learning Objectives
- Create HTML forms in Spring Boot
- Handle form submissions
- Validate form data
- Process form data
- Build form-based applications

## 🎯 Topics Covered
- HTML form creation
- Form binding
- @ModelAttribute
- Form validation
- Error handling
- Redirect after submit

## 📝 Step-by-Step Instructions

### Step 1: Create Form
Create HTML form:

```html
<form th:action="@{/users}" th:object="${user}" method="post">
    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" th:field="*{name}" required>
    </div>
    <div>
        <label for="email">Email:</label>
        <input type="email" id="email" th:field="*{email}" required>
    </div>
    <button type="submit">Submit</button>
</form>
```

### Step 2: Handle Form Submission
Process form in controller:

```java
@Controller
public class UserController {
    
    @GetMapping("/users/new")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }
    
    @PostMapping("/users")
    public String submitForm(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }
}
```

### Step 3: Form Validation
Add validation:

```java
public class User {
    @NotBlank(message = "Name is required")
    private String name;
    
    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;
}
```

## 🎮 Project: User Registration Form

### Requirements
Create user registration:
1. Registration form
2. Form validation
3. Error display
4. Success redirect
5. Data persistence

## 📚 Resources
- [Spring Form Handling](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-modelattrib-method-args)
- [Form Validation](https://www.baeldung.com/spring-boot-bean-validation)

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- Re-read **Step 1 & 2** for form HTML and Thymeleaf (`th:action`, `th:object`, `th:field`); **Step 3** for validation (`@NotBlank`, `@Email`)
- **@ModelAttribute** binds form data to object; **@GetMapping** shows form, **@PostMapping** processes submit
- **Related days:** Day 59 (Bootstrap/Thymeleaf); Day 61 (advanced forms). **Quick reference:** `mvn spring-boot:run`; run from IDE

## ✅ Checklist
- [ ] Can create HTML forms
- [ ] Can handle submissions
- [ ] Can validate data
- [ ] Can display errors
- [ ] Completed registration form
- [ ] Committed code to Git

## 💻 How to Run

**Spring Boot:** From project root: `mvn spring-boot:run`. Or run the main Application class in your IDE. Open http://localhost:8080 in browser.

## 🚀 Next Steps
**Ready for Day 61?** You'll build advanced forms!






