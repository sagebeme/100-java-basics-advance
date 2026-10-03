# Day 41 - Exercise 1: A recipe page

Write `src/recipe.html`: a page with your family's chapati recipe (or use the one below). It must have:

- `lang="en"` on `<html>`, a `<title>` and the viewport `<meta>`
- exactly one `<h1>` that mentions **Chapati**
- an `<h2>Ingredients</h2>` followed by a `<ul>` with at least **5** items
- an `<h2>Method</h2>` followed by an `<ol>` with at least **5** steps
- the picture `chapati.svg` in an `<img>` with a helpful `alt` text
- at least one `<strong>` for something important ("Don't skip the resting time!")

Ingredients: 3 cups flour, 1 teaspoon salt, 1 tablespoon sugar, 1 cup warm water, 3 tablespoons oil, plus oil for the pan.
Method: mix the dry things, add water and oil, knead for 10 minutes, rest 30 minutes, roll and coil each ball, roll flat, cook on a hot pan with a little oil until golden on both sides.

## Files

- `src/recipe.html`: your page (it starts almost empty).
- `src/chapati.svg`: the picture.
- `src/RecipePageTest.java`: checks your page with jsoup. Run it like any other test in this course.
- `solution/recipe.html`: one answer.

## 📌 Stuck?

`<ul>` is a bulleted list (order doesn't matter) and `<ol>` a numbered one (order matters). Each item goes in an `<li>`.
