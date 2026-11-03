# Day 60 - Forms and Validation: The Sign-Up Desk

> A form is where your app meets real people, and real people leave fields empty, mistype their email and press Submit twice. Today the form catches all of it, says what's wrong next to the right field, and keeps everything else they typed.

## 📖 The story

The Kibera Youth Coding Club opens registration every January, and every January the paper forms come back with no email, or an email like `brian@gmail` that bounces. This year it's online: a **Sajili** ("register") page that asks for a name and an email, refuses bad ones politely, and saves the good ones.

```
Name:  [               ]   Name is required
Email: [brian@gmail    ]   Invalid email
[ Register ]
```

When both are right, the member is saved, and the browser moves to the list of everyone who has signed up.

Members are kept in an **H2** database through Spring Data JPA, so you can see how a form, its validation and the database fit together. Days 63 to 65 go into the database part properly; today it's two lines you can take on trust.

## 🎯 Learning Objectives
- Show a form bound to an object with `th:object` and `th:field`
- Put validation rules on the object's fields: `@NotBlank`, `@Email`, with your own messages
- Check them with `@Valid`, read the result from `BindingResult`, and show the same form again on failure
- Show each error next to its field with `#fields.hasErrors` and `th:errors`
- Redirect after a successful POST, so a refresh doesn't submit twice
- Test both paths with MockMvc: a valid form is saved, an invalid one is not

## 🧰 What you'll use

**Rules on the object:**

```java
@NotBlank(message = "Name is required")
private String name;

@NotBlank(message = "Email is required")
@Email(message = "Invalid email")
private String email;
```

**The handler.** `BindingResult` must come right after the object it checks. If there are errors, return the form's template, *not* a redirect, so the user's input and the messages are still there:

```java
@PostMapping("/users")
public String submitForm(@Valid @ModelAttribute User user, BindingResult result) {
    if (result.hasErrors()) {
        return "user-form";            // same page, same input, plus the errors
    }
    userRepository.save(user);
    return "redirect:/users";          // success: a new GET page
}
```

**Errors in the template:**

```html
<input type="email" id="email" th:field="*{email}">
<span th:if="${#fields.hasErrors('email')}" th:errors="*{email}" class="error">Email error</span>
```

**Saving.** `UserRepository extends JpaRepository<User, Long>` gets `save` and `findAll` for free. The `User` table is called `app_user` because `USER` is a reserved word in SQL.

## 🪜 Steps

**Project: Sajili** (the `day60/` folder is a finished version: build your own first, then compare).

1. `User`: id, name and email, with the validation rules and your own messages.
2. `GET /users/new`: an empty form.
3. `POST /users`: validate; on errors show the form again, otherwise save and redirect.
4. `GET /users`: the list of members.
5. The two templates, with an error next to each field, and `sajili.css` for the look.
6. Tests: the empty form, a valid sign-up that's saved, a missing name, a bad email, and a rejected form that saves nothing.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day60
mvn spring-boot:run
```

Open http://localhost:8080/users/new and try to break it. Stop it with Ctrl+C.

```bash
cd day60
mvn test
```

`UserControllerTest` checks the five cases above. A clean run ends with `BUILD SUCCESS`.

## 🆘 Stuck?

- **A `400 Bad Request` page instead of your form with errors**: `BindingResult` isn't the parameter right after the `@Valid` object, so Spring throws instead of handing you the errors.
- **The errors show, but the fields are empty**: you redirected on failure. Return the template so the object (with its input) stays in the model.
- **`@Valid` is ignored**: `spring-boot-starter-validation` is missing from `pom.xml`.
- **`brian@gmail` is accepted**: `@Email` allows addresses without a dot in the domain, because they're legal on private networks. Add `@Pattern(regexp = ".+@.+\\..+")` if you want a dot.
- **`Syntax error in SQL statement ... USER`**: name the table something else with `@Table(name = "app_user")`.

## ✅ Checklist
- [ ] A form bound to an object with `th:field`
- [ ] Validation rules with clear messages
- [ ] Errors shown next to their fields, with the rest of the input kept
- [ ] Redirect after a successful POST
- [ ] Tests for both a valid and an invalid sign-up pass
- [ ] Committed code to Git

## ➡️ Next

Day 61 takes forms further: an order with a customer and a list of items in one form, and file uploads that can't be tricked.
