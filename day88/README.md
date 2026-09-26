# Day 88 - Portfolio Project: Todo List Application

## 📚 Learning Objectives
- Build full-stack application
- Implement CRUD operations
- Use database
- Create user interface
- Build complete app

## 🎯 Project Requirements
Create Todo List App:
1. Add todos
2. Mark complete
3. Delete todos
4. Filter todos
5. Persist data

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Project Requirements** above; use main README for structure. **Quick reference:** `mvn spring-boot:run` or run from IDE; JPA/DB (Day 63); see Day 38.

## ✅ Checklist
- [ ] Implemented CRUD
- [ ] Added database
- [ ] Created UI
- [ ] Added features
- [ ] Completed app
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Application.java` | Starts Spring Boot |
| `controller/TodoController.java` | `GET /` lists, `POST /todos` adds, and toggle and delete routes |
| `exception/TodoExceptionHandler.java` | Answers 404 when a todo doesn't exist |
| `model/Todo.java` | A JPA entity |
| `repository/TodoRepository.java` | Spring Data repository |
| `service/TodoFilter.java` | All, active or completed |
| `service/TodoService.java` | Adds, lists, toggles and deletes todos |

Also in `src/main/resources/`: `templates/index.html`.

## 💻 How to Run

Needs JDK 21 and Maven (see the main README). From the repository root:

```bash
cd day88
mvn spring-boot:run
```

Then open http://localhost:8080. Stop it with Ctrl+C. Or run `Application` from your IDE.

## 🧪 How to Test

```bash
cd day88
mvn test
```

Runs `TodoControllerTest` and `TodoServiceTest`. A clean run ends with `BUILD SUCCESS`.

## 🚀 Next Steps
**Ready for Day 89?** You'll build a Disappearing Text App!
