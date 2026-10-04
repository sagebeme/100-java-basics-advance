# Day 79 - Testing and Quality Assurance

## 📚 Learning Objectives
- Write unit tests
- Use JUnit and Mockito
- Implement test coverage
- Perform integration testing
- Ensure code quality
- Write end-to-end tests that drive a real browser through a running web app (Part 2)

## 🎯 Topics Covered
- Unit testing
- Integration testing
- Test-driven development
- Mocking
- Code coverage

## 📝 Step-by-Step Instructions

### Step 1: Unit Tests
Write unit tests:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3));
    }
}
```

### Step 2: Mocking
Use mocks:

```java
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@Mock
private UserRepository userRepository;

@Test
public void testService() {
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    // Test service
}
```

## 🎮 Project: Test Suite

### Requirements
Create test suite:
1. Unit tests
2. Integration tests
3. Mock dependencies
4. Achieve coverage

## 🎮 Part 2: End-to-end tests for a web app

Unit tests check one class. Integration tests check a few classes working together. Neither proves that a person can open the website, fill in the form and get what they came for. **End-to-end (E2E) tests** do exactly that: they start the whole application on a real port, open a real browser, and click through it like a user.

`e2e/` is a small Spring Boot app, **Maktaba** (Swahili for library): a community library where readers see the shelves, borrow a book with their library card number, and get a loan slip with a due date. It comes with three kinds of test:

| Test | Type | What it checks | Speed |
| --- | --- | --- | --- |
| `LibraryServiceTest` | unit | the borrowing rules, with no server and no browser | milliseconds |
| `BorrowingJourneyTest` | end-to-end | seven whole journeys through Chrome, against the running app | seconds each |

That's the **testing pyramid**: many fast unit tests at the bottom, a few slow end-to-end tests at the top. Rules belong in unit tests; end-to-end tests prove that the pieces are wired together the way a reader experiences them.

### The journeys

```
✓ the shelves show every book and what is left
✓ a reader borrows a book and gets a slip            (code MK-2-1001, due Monday 19 October 2026)
✓ a mistyped card is explained and nothing is borrowed
✓ two readers race for the last copy                  (the second sees "Every copy of "Dust" is out on loan.")
✓ refreshing the slip does not borrow twice           (Post/Redirect/Get)
✓ the whole journey works with the keyboard alone
✓ fits a phone screen
```

### How it works

- **A real server on a random port.** `@SpringBootTest(webEnvironment = RANDOM_PORT)` starts the whole app, with Tomcat, Thymeleaf and all, on a free port. `@Value("${local.server.port}")` tells the test which port that is.
- **A real browser.** Selenium drives headless Chrome, as on Days 47–53. If Chrome isn't installed, the tests are skipped, not failed.
- **Page objects.** `CataloguePage`, `BorrowPage` and `LoanPage` know where the buttons are, so the tests read like the journey: `open(...).borrow("Coming to Birth").borrowAs("M-1234", "2 weeks")`.
- **Frozen time.** A `@TestConfiguration` replaces the app's `Clock` with one fixed on Monday 5 October 2026, so "due in two weeks" is always the same date, whatever day you run the tests.
- **A clean start.** The server keeps running between tests, so each test calls `library.reset()` first. Tests that depend on each other's leftovers are the commonest cause of "it passes alone but fails with the others".
- **Wait for the page, don't sleep.** `LoanPage` waits for the slip to appear. After a click Selenium waits for the next page by itself, but after a keypress it doesn't.

### What the keyboard test taught us

The first version of the keyboard journey pressed Enter while the "For how long?" dropdown had focus, and failed: Enter on a dropdown doesn't submit a form. A real keyboard user presses Tab to reach the button first, and the test now does the same. That's the point of end-to-end tests: they find what only happens when everything runs together, the way people really use it.

### Run them

You need Maven and Google Chrome. From `day79/e2e`:

```bash
mvn test                        # 3 unit tests and 7 end-to-end journeys, about a minute
mvn spring-boot:run             # then open http://localhost:8080 and borrow a book yourself
```

Run just the fast ones with `mvn test -Dtest=LibraryServiceTest`. Most teams run unit tests on every save and end-to-end tests before every merge.

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** `mvn test` for tests; coverage report in `target/site/jacoco/index.html`.

## ✅ Checklist
- [ ] Can write unit tests
- [ ] Can use mocking
- [ ] Can test integration
- [ ] Completed test suite
- [ ] Ran the end-to-end journeys in `e2e/`, and can explain the testing pyramid
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Book.java` | A library book |
| `BookRepository.java` | Where books are stored, as an interface so tests can swap it |
| `BookService.java` | Adds, lists, checks out and returns books |
| `InMemoryBookRepository.java` | A simple in-memory version |

In Part 1 there's no app to run: the tests *are* the project, and JaCoCo measures how much of the code they cover. Part 2's `e2e/` folder is its own Maven project: the Maktaba web app and its end-to-end tests.

## 💻 How to Run

There's no app to start today: the tests are the project. Needs JDK 21 and Maven (see the main README).

## 🧪 How to Test

```bash
cd day79
mvn test
```

Runs `BookServiceIntegrationTest` and `BookServiceUnitTest`. A clean run ends with `BUILD SUCCESS`.

The coverage report is written to `day79/target/site/jacoco/index.html`: open it in a browser to see which lines the tests reach.

## 🚀 Next Steps
**Ready for Day 80?** You'll build the House Price Prediction capstone!
