# Day 41 - Introduction to HTML/CSS

## 📚 Learning Objectives
- Understand HTML structure
- Learn basic HTML tags
- Master CSS styling
- Create web pages
- Build foundation for web scraping

## 🎯 Topics Covered
- HTML document structure
- HTML tags (headings, paragraphs, links, images)
- CSS basics (selectors, properties)
- Inline and external CSS
- HTML forms
- Semantic HTML

## 📝 Step-by-Step Instructions

### Step 1: HTML Structure
Basic HTML document:

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My First Web Page</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Welcome to My Website</h1>
    <p>This is a paragraph of text.</p>
    <a href="https://example.com">Visit Example</a>
    <img src="image.jpg" alt="Description">
</body>
</html>
```

### Step 2: Common HTML Tags
Essential HTML elements:

```html
<!-- Headings -->
<h1>Main Heading</h1>
<h2>Subheading</h2>

<!-- Text -->
<p>Paragraph text</p>
<strong>Bold text</strong>
<em>Italic text</em>

<!-- Lists -->
<ul>
    <li>Item 1</li>
    <li>Item 2</li>
</ul>

<ol>
    <li>First</li>
    <li>Second</li>
</ol>

<!-- Links and Images -->
<a href="page.html">Link Text</a>
<img src="photo.jpg" alt="Photo description">
```

### Step 3: CSS Basics
Style with CSS:

```css
/* style.css */
body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
    margin: 0;
    padding: 20px;
}

h1 {
    color: #333;
    text-align: center;
}

p {
    color: #666;
    line-height: 1.6;
}

a {
    color: #007bff;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}
```

### Step 4: HTML Forms
Create forms:

```html
<form action="/submit" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>
    
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
    
    <button type="submit">Submit</button>
</form>
```

## 💻 Exercises

### Exercise 1: Basic HTML
Create pages with:
- Headings and paragraphs
- Links and images
- Lists
- Basic structure

### Exercise 2: CSS Styling
Style pages with:
- Colors and fonts
- Layouts
- Spacing
- Responsive design

### Exercise 3: Forms
Create forms with:
- Text inputs
- Email inputs
- Buttons
- Validation

## 🎮 Project: Personal Website

### Requirements
Create a personal website:
1. Homepage with introduction
2. About page
3. Contact form
4. Styled with CSS
5. Responsive design
6. Links between pages

### Example Structure
```
my-website/
├── index.html
├── about.html
├── contact.html
└── style.css
```

### Starter Code
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Personal Website</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <header>
        <h1>Welcome to My Website</h1>
        <nav>
            <a href="index.html">Home</a>
            <a href="about.html">About</a>
            <a href="contact.html">Contact</a>
        </nav>
    </header>
    <main>
        <!-- Your content here -->
    </main>
</body>
</html>
```

## 📚 Resources

### Official Documentation
- [HTML - MDN](https://developer.mozilla.org/en-US/docs/Web/HTML)
- [CSS - MDN](https://developer.mozilla.org/en-US/docs/Web/CSS)
- [HTML5 Specification](https://html.spec.whatwg.org/)

### Tutorials
- [HTML Tutorial - W3Schools](https://www.w3schools.com/html/)
- [CSS Tutorial - W3Schools](https://www.w3schools.com/css/)
- [HTML & CSS - freeCodeCamp](https://www.freecodecamp.org/)

### Video Resources
- [HTML Tutorial](https://www.youtube.com/results?search_query=html+tutorial)
- [CSS Tutorial](https://www.youtube.com/results?search_query=css+tutorial)

### Practice Platforms
- [CodePen](https://codepen.io/)
- [HTML/CSS Exercises](https://www.w3resource.com/html-exercises/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| HTML | Markup language | `<h1>Heading</h1>` |
| CSS | Styling language | `color: blue;` |
| Tags | HTML elements | `<p>`, `<div>`, `<a>` |
| Selectors | Target elements | `h1`, `.class`, `#id` |
| Forms | User input | `<input>`, `<form>` |

## ✅ Checklist
- [ ] Understand HTML structure
- [ ] Know common HTML tags
- [ ] Can write CSS
- [ ] Can create forms
- [ ] Can link pages
- [ ] Completed Personal Website
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 41, you should be able to:
- Create HTML pages
- Style with CSS
- Build basic websites
- Understand web structure

**Ready for Day 42?** You'll learn intermediate HTML/CSS!



