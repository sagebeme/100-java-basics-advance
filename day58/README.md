# Day 58 - Bootstrap Integration

## 📚 Learning Objectives
- Integrate Bootstrap with Spring Boot
- Use Bootstrap components
- Create responsive layouts
- Style Thymeleaf templates
- Build modern web interfaces

## 🎯 Topics Covered
- Bootstrap setup
- Bootstrap components
- Responsive design
- Bootstrap utilities
- Custom styling
- Bootstrap JavaScript

## 📝 Step-by-Step Instructions

### Step 1: Add Bootstrap to Project
Include Bootstrap via CDN:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title th:text="${title}">My App</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom CSS -->
    <link th:href="@{/css/custom.css}" rel="stylesheet">
</head>
<body>
    <!-- Content -->
    
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

### Step 2: Use Bootstrap Components
Create navigation and cards:

```html
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <a class="navbar-brand" th:href="@{/}">My App</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" th:href="@{/}">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" th:href="@{/about}">About</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title" th:text="${card.title}">Card Title</h5>
                    <p class="card-text" th:text="${card.text}">Card text</p>
                    <a th:href="@{/details}" class="btn btn-primary">View Details</a>
                </div>
            </div>
        </div>
    </div>
</div>
```

### Step 3: Bootstrap Forms
Create styled forms:

```html
<form th:action="@{/users}" th:object="${user}" method="post" class="row g-3">
    <div class="col-md-6">
        <label for="name" class="form-label">Name</label>
        <input type="text" class="form-control" id="name" th:field="*{name}" required>
    </div>
    <div class="col-md-6">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" th:field="*{email}" required>
    </div>
    <div class="col-12">
        <button type="submit" class="btn btn-primary">Submit</button>
    </div>
</form>
```

### Step 4: Responsive Grid
Use Bootstrap grid system:

```html
<div class="container">
    <div class="row">
        <div class="col-12 col-md-6 col-lg-4">
            <div class="card">
                <div class="card-body">Content 1</div>
            </div>
        </div>
        <div class="col-12 col-md-6 col-lg-4">
            <div class="card">
                <div class="card-body">Content 2</div>
            </div>
        </div>
        <div class="col-12 col-md-6 col-lg-4">
            <div class="card">
                <div class="card-body">Content 3</div>
            </div>
        </div>
    </div>
</div>
```

### Step 5: Bootstrap Utilities
Use utility classes:

```html
<div class="d-flex justify-content-between align-items-center mb-3">
    <h1 class="text-primary">Dashboard</h1>
    <button class="btn btn-success">Add New</button>
</div>

<div class="alert alert-info" role="alert">
    <strong>Info!</strong> This is an informational message.
</div>

<div class="badge bg-primary">New</div>
<div class="badge bg-success">Active</div>
```

## 💻 Exercises

### Exercise 1: Bootstrap Setup
Set up Bootstrap:
- Add Bootstrap CSS/JS
- Create basic layout
- Test responsiveness

### Exercise 2: Components
Use Bootstrap components:
- Navigation bars
- Cards
- Buttons
- Forms

### Exercise 3: Responsive Design
Create responsive layouts:
- Grid system
- Breakpoints
- Mobile-first design

## 🎮 Project: Bootstrap Dashboard

### Requirements
Create a dashboard with Bootstrap:
1. Responsive navigation
2. Dashboard cards
3. Data tables
4. Forms with validation
5. Alerts and notifications
6. Mobile-friendly design

### Example Template
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Dashboard</a>
        </div>
    </nav>
    
    <div class="container mt-4">
        <div class="row">
            <div class="col-md-3">
                <div class="card text-white bg-primary mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Users</h5>
                        <p class="card-text" th:text="${userCount}">0</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card text-white bg-success mb-3">
                    <div class="card-body">
                        <h5 class="card-title">Posts</h5>
                        <p class="card-text" th:text="${postCount}">0</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

## 📚 Resources

### Official Documentation
- [Bootstrap Documentation](https://getbootstrap.com/docs/5.3/)
- [Bootstrap Components](https://getbootstrap.com/docs/5.3/components/)
- [Bootstrap Grid](https://getbootstrap.com/docs/5.3/layout/grid/)

### Tutorials
- [Bootstrap Tutorial - W3Schools](https://www.w3schools.com/bootstrap5/)
- [Bootstrap with Spring Boot](https://www.baeldung.com/spring-boot-bootstrap)

### Video Resources
- [Bootstrap Tutorial](https://www.youtube.com/results?search_query=bootstrap+tutorial)
- [Bootstrap Spring Boot](https://www.youtube.com/results?search_query=bootstrap+spring+boot)

### Practice Platforms
- [Bootstrap Examples](https://getbootstrap.com/docs/5.3/examples/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Bootstrap | CSS framework | Responsive components |
| Grid System | Layout system | `col-md-6`, `row` |
| Components | UI elements | Cards, buttons, forms |
| Utilities | Helper classes | `mt-4`, `text-center` |
| Responsive | Mobile-friendly | Breakpoints, media queries |

## ✅ Checklist
- [ ] Can add Bootstrap to project
- [ ] Can use Bootstrap components
- [ ] Can create responsive layouts
- [ ] Can style forms
- [ ] Can use utilities
- [ ] Completed Bootstrap Dashboard
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 58, you should be able to:
- Integrate Bootstrap
- Create modern UIs
- Build responsive designs
- Style web applications

**Congratulations!** You've completed the Intermediate+ section!

**Ready for Day 59?** You'll upgrade your blog with Bootstrap!


