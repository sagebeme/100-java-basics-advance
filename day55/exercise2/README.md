# Day 55 - Exercise 2: A chama's members API that says what's wrong

A women's chama in Nakuru collects monthly contributions by M-Pesa. Their treasurer's app keeps the member list through this API. Exercise 1's API trusted whatever it was sent. This one refuses bad data, and when it refuses, it says exactly why, in a form the app can show next to the right box.

The CRUD in `MemberController` is mostly written. Your jobs:

**1. The rules, on `Member`,** with Bean Validation annotations, each with this exact message:

| Field | Rule | Message |
|---|---|---|
| `name` | not blank | `Type the member's name` |
| `phone` | present | `A phone number is needed for M-Pesa` |
| `phone` | `0[17]` then 8 digits | `Use a phone like 0712345678` |
| `monthlyShillings` | at least 100 | `The smallest monthly contribution is KES 100` |

Then add `@Valid` in front of `@RequestBody` in `join` and `update`, or the rules are never checked.

**2. The errors, in `MemberController`:** `find` throws `MemberNotFoundException`, and `refuseDuplicatePhone` throws `DuplicatePhoneException` when another member has that phone. Updating a member with their own phone is fine.

**3. The answers, in `GlobalExceptionHandler`.** A `@RestControllerAdvice` catches exceptions from every controller and turns them into JSON, using `ApiError` (already written):

| Exception | Status | Body |
|---|---|---|
| `MemberNotFoundException` | 404 | `{"status":404,"message":"No member with id 9"}` |
| `DuplicatePhoneException` | 409 Conflict | `{"status":409,"message":"0712345678 is already a member"}` |
| `MethodArgumentNotValidException` (from `@Valid`) | 400 | `{"status":400,"errors":{"name":"Type the member's name","phone":"…"}}`: one message per broken field, all at once |
| `HttpMessageNotReadableException` (the body isn't JSON) | 400 | `{"status":400,"message":"The body isn't valid JSON for a member"}` |

**4. Search:** `GET /api/members?name=wanj` lists members whose name contains `wanj`, ignoring case. Without `?name=`, all of them.

## Files

- `src/main/java/com/learning/model/Member.java`, `controller/MemberController.java` and `exception/GlobalExceptionHandler.java`: your code goes here.
- `src/main/java/com/learning/exception/ApiError.java`, `MemberNotFoundException.java` and `DuplicatePhoneException.java`: done.
- `src/test/java/com/learning/controller/MemberControllerTest.java`: the tests.
- `solution/src/...`: one answer for each file you change, at the same paths.

```bash
cd day55/exercise2
mvn test
```

## 📌 Stuck?

- The annotations are in `jakarta.validation.constraints`: `@NotBlank(message = "...")`, `@NotNull`, `@Pattern(regexp = "0[17]\\d{8}", message = "...")` and `@Min(value = 100, message = "...")`. `@Pattern` lets `null` through, which is why the phone also needs `@NotNull`.
- In the validation handler, `e.getBindingResult().getFieldErrors()` lists each failure; `getField()` and `getDefaultMessage()` give the parts. Use `putIfAbsent` so a field with two failures keeps one message.
- `@RequestParam(required = false) String name` is `null` when there's no `?name=`.
- 409 Conflict means "your request is fine, but it clashes with what's already here". It's the right answer for a duplicate, where 400 would wrongly blame the request's format.
