# Day 42 - Intermediate HTML/CSS

## 📚 Learning Objectives
- Master advanced CSS techniques
- Learn CSS layouts (Flexbox, Grid)
- Understand responsive design
- Work with CSS frameworks
- Build modern web layouts

## 🎯 Topics Covered
- CSS Flexbox
- CSS Grid
- Responsive design
- Media queries
- CSS animations
- Advanced selectors

## 📝 Step-by-Step Instructions

### Step 1: Flexbox Layout
Create flexible layouts:

```css
.container {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 20px;
}

.item {
    flex: 1;
    padding: 20px;
}
```

### Step 2: CSS Grid
Create grid layouts:

```css
.grid-container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    grid-gap: 20px;
}

.grid-item {
    background-color: #f0f0f0;
    padding: 20px;
}
```

### Step 3: Responsive Design
Use media queries:

```css
/* Mobile first */
.container {
    width: 100%;
    padding: 10px;
}

/* Tablet */
@media (min-width: 768px) {
    .container {
        max-width: 750px;
        margin: 0 auto;
    }
}

/* Desktop */
@media (min-width: 1024px) {
    .container {
        max-width: 1200px;
    }
}
```

### Step 4: CSS Animations
Add animations:

```css
@keyframes fadeIn {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}

.element {
    animation: fadeIn 1s ease-in;
}
```

## 💻 Exercises

### Exercise 1: Flexbox
Create layouts with:
- Flex containers
- Flex items
- Alignment
- Wrapping

### Exercise 2: CSS Grid
Build grids with:
- Multiple columns
- Grid areas
- Responsive grids
- Complex layouts

### Exercise 3: Responsive Design
Make pages responsive:
- Mobile layouts
- Tablet layouts
- Desktop layouts
- Media queries

## 🎮 Project: Modern Portfolio Website

### Requirements
Create a modern portfolio:
1. Responsive navigation
2. Hero section
3. Projects grid
4. About section
5. Contact form
6. Smooth animations
7. Mobile-friendly

### Example Layout
```
[Header with Navigation]
[Hero Section - Full Width]
[Projects Grid - 3 Columns]
[About Section]
[Contact Form]
[Footer]
```

### Starter Code
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Portfolio</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header>
        <nav class="navbar">
            <!-- Navigation -->
        </nav>
    </header>
    <main>
        <section class="hero">
            <!-- Hero content -->
        </section>
        <section class="projects">
            <div class="grid-container">
                <!-- Project cards -->
            </div>
        </section>
    </main>
</body>
</html>
```

## 📚 Resources

### Official Documentation
- [CSS Flexbox - MDN](https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Flexible_Box_Layout)
- [CSS Grid - MDN](https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Grid_Layout)
- [Media Queries - MDN](https://developer.mozilla.org/en-US/docs/Web/CSS/Media_Queries)

### Tutorials
- [Flexbox Guide - CSS-Tricks](https://css-tricks.com/snippets/css/a-guide-to-flexbox/)
- [Grid Guide - CSS-Tricks](https://css-tricks.com/snippets/css/complete-guide-grid/)
- [Responsive Design](https://www.w3schools.com/css/css_rwd_intro.asp)

### Video Resources
- [Flexbox Tutorial](https://www.youtube.com/results?search_query=css+flexbox+tutorial)
- [CSS Grid Tutorial](https://www.youtube.com/results?search_query=css+grid+tutorial)

### Practice Platforms
- [Flexbox Froggy](https://flexboxfroggy.com/)
- [Grid Garden](https://cssgridgarden.com/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| Flexbox | One-dimensional layout | `display: flex` |
| Grid | Two-dimensional layout | `display: grid` |
| Media Queries | Responsive design | `@media (min-width: 768px)` |
| Animations | CSS transitions | `@keyframes`, `animation` |
| Responsive | Mobile-friendly | Flexible layouts |

## ✅ Checklist
- [ ] Understand Flexbox
- [ ] Understand CSS Grid
- [ ] Can create responsive designs
- [ ] Can use media queries
- [ ] Can add animations
- [ ] Completed Portfolio Website
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 42, you should be able to:
- Create modern layouts
- Build responsive websites
- Use advanced CSS
- Design professional pages

**Ready for Day 43?** You'll learn web scraping with Jsoup!

