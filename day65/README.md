# Day 65 - Web Design Best Practices

## 📚 Learning Objectives
- Understand web design principles
- Apply UX best practices
- Create accessible websites
- Optimize performance
- Build user-friendly interfaces

## 🎯 Topics Covered
- UX/UI principles
- Accessibility
- Performance optimization
- Responsive design
- Color theory
- Typography

## 📝 Step-by-Step Instructions

### Step 1: Accessibility
Make sites accessible:

```html
<!-- Use semantic HTML -->
<nav aria-label="Main navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<!-- Add alt text -->
<img src="logo.png" alt="Company Logo">

<!-- Use ARIA labels -->
<button aria-label="Close dialog">×</button>
```

### Step 2: Performance
Optimize performance:

```html
<!-- Lazy load images -->
<img src="image.jpg" loading="lazy" alt="Description">

<!-- Minify CSS/JS -->
<link rel="stylesheet" href="styles.min.css">
```

## 🎮 Project: Apply Best Practices

### Requirements
Improve the Day 64 movies site:
1. Add accessibility
2. Optimize performance
3. Improve UX
4. Enhance design
5. Test responsiveness

## 📌 Notes & reference (use these when stuck)

**When you're stuck:**
- Re-read **Step 1** (accessibility: semantic HTML, aria-label, alt text); **Step 2** (performance: lazy loading, minify)
- **Related days:** Day 64 (movies site); Day 66 (REST APIs). **Quick reference:** Apply to existing Thymeleaf/Spring pages

## ✅ Checklist
- [ ] Understand best practices
- [ ] Can create accessible sites
- [ ] Can optimize performance
- [ ] Can improve UX
- [ ] Applied best practices
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/MovieController.java` | `GET /movies` and `POST /movies`, unchanged from Day 64 |
| `model/Movie.java` | Unchanged from Day 64 |
| `repository/MovieRepository.java` | Unchanged from Day 64 |
| `service/MovieService.java` | Unchanged from Day 64 |

Also in `src/main/resources/`: `templates/movies.html`.

This is Day 64's movies site with web-design fixes applied: semantic `<header>` and `<main>`, `aria-label`s on the ranked list and the form, a `<label for>` on every input, a responsive viewport tag, and Bootstrap's script loaded with `defer`. The tests check each of those in the real page.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day65
mvn spring-boot:run
```

Then open http://localhost:8080/movies. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day65
mvn test
```

Runs `MovieControllerTest` and `MovieServiceTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 66?** You'll build RESTful APIs!
