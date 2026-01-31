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
Improve existing site:
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

## 💻 How to Run

**Run your Spring Boot app:** `mvn spring-boot:run` or run main Application from IDE. Open http://localhost:8080.

## 🚀 Next Steps
**Ready for Day 66?** You'll build RESTful APIs!






