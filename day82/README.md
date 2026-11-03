# Day 82 - Portfolio Project: Personal Website

> A CV says "I know Spring Boot". A website you built with it shows it. Today you build your own: a home page, your projects, and a contact form that really works.

## 📖 The story

Wairimu has finished 80 days of Java and is applying for junior developer jobs in Nairobi. Every recruiter asks the same thing: "Do you have a portfolio?" She has projects (the house price predictor, the blog API, the Morse converter), but they're folders on her laptop.

Her site needs three pages:

- **Home**: who she is and what she does, with links to everything else.
- **Projects**: a card per project with a description, the technologies, and a link.
- **Contact**: a form that checks its input, saves the message, and thanks the visitor, without sending the message twice if they refresh the page.

All of it has to look right on a phone, because that's where most recruiters will open it.

## 🎯 Learning Objectives
- Structure a small Spring MVC site: controllers, services, models and templates
- Share a navigation bar between pages with a Thymeleaf fragment
- List data from a service in a template with `th:each`
- Validate a form with Bean Validation, including length limits
- Use Post/Redirect/Get with flash attributes
- Make pages responsive with Bootstrap and a viewport meta tag

## 🧰 What you'll use

**A fragment for the navbar.** Write it once in `templates/fragments/navbar.html` and include it on every page:

```html
<nav th:replace="~{fragments/navbar :: navbar}"></nav>
```

**Projects as records**, served by a `ProjectService` (a list in code for now; a database later):

```java
public record Project(String title, String description, List<String> techStack, String link) {}
```

**Validation with limits.** `@NotBlank` and `@Email` aren't enough on a public form: without `@Size(max = ...)`, anyone can post a 10 MB "message" and fill your memory.

**Post/Redirect/Get.** If the form's POST returns a page, refreshing that page asks the browser to POST again, and the message is sent twice. Instead, redirect to a GET, and carry the "thanks" across with a flash attribute, which lives for exactly one request:

```java
contactService.save(contactForm);
redirectAttributes.addFlashAttribute("submitted", true);
return "redirect:/contact";
```

## 🪜 Steps

**Your brief** (the `day82/` folder is one finished version: build your own first, then compare).

1. `PageController`: `/` and `/projects`. `ContactController`: `GET` and `POST /contact`.
2. The shared navbar fragment, and Bootstrap for the layout.
3. `ProjectService` with your real projects. Replace the `#` links with your GitHub repositories.
4. `ContactForm` with validation messages people can understand, and `ContactService` to keep the messages.
5. Tests: every page renders with the navbar and viewport tag, the projects appear, a valid message is saved and redirects, and missing, invalid or overlong fields are refused.
6. Make it yours: your own words, your own colours, and a photo.
7. Deploy it (Day 70's Dockerfile is a starting point) and put the link on your CV.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day82
mvn test
mvn spring-boot:run
```

Open <http://localhost:8080>, then check it at phone width in your browser's developer tools.

## 🆘 Stuck?

- **The thanks message doesn't appear after the redirect**: use `addFlashAttribute`, not `addAttribute`. A plain attribute ends up in the URL, not the next page's model.
- **The form is empty after a validation error**: on errors, return the view directly (no redirect), so the form object and its errors are still in the model.
- **The navbar is missing on one page**: check the fragment name in `th:replace` matches `th:fragment` exactly.
- **The site looks tiny on a phone**: the page needs `<meta name="viewport" content="width=device-width, initial-scale=1">`.

## ✅ Checklist
- [ ] Home, projects and contact pages with a shared navbar
- [ ] My real projects, with working links
- [ ] A validated contact form using Post/Redirect/Get
- [ ] Looks right at phone width
- [ ] The tests pass
- [ ] Deployed, and the link is on my CV
- [ ] Committed code to Git

## ➡️ Next

Day 83 is a game: Tic Tac Toe in a JavaFX window, with the rules in a class you can test.
