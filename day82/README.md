# Day 82 - Portfolio Project: Personal Website

## 📚 Learning Objectives
- Build personal portfolio website
- Showcase projects
- Create professional design
- Deploy website
- Build online presence

## 🎯 Project Requirements
Create Personal Website:
1. Home/About page
2. Projects portfolio
3. Contact form
4. Responsive design
5. Professional styling

## 📝 Implementation
Use Spring Boot + Thymeleaf + Bootstrap

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** and **Implementation** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE; see Days 54–59 for Spring/Thymeleaf/Bootstrap.

## ✅ Checklist
- [ ] Created website structure
- [ ] Added portfolio section
- [ ] Created contact form
- [ ] Made responsive
- [ ] Deployed website
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/ContactController.java` | `GET` and `POST /contact`, with validation |
| `controller/PageController.java` | The home page and `/projects` |
| `model/ContactForm.java` | The contact form, with validation rules |
| `model/ContactMessage.java` | A received message |
| `model/Project.java` | One portfolio project |
| `service/ContactService.java` | Stores messages |
| `service/ProjectService.java` | The list of projects |

Also in `src/main/resources/`: `templates/contact.html`, `templates/fragments/navbar.html`, `templates/index.html`, `templates/projects.html`.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day82
mvn spring-boot:run
```

Then open http://localhost:8080. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day82
mvn test
```

Runs `ContactControllerTest` and `PageControllerTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 83?** You'll build a Tic Tac Toe Game!
