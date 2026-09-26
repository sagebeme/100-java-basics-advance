# Day 79 - Testing and Quality Assurance

## 📚 Learning Objectives
- Write unit tests
- Use JUnit and Mockito
- Implement test coverage
- Perform integration testing
- Ensure code quality

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

## 📌 Notes & reference (use these when stuck)

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** `mvn test` for tests; coverage report in `target/site/jacoco/index.html`.

## ✅ Checklist
- [ ] Can write unit tests
- [ ] Can use mocking
- [ ] Can test integration
- [ ] Completed test suite
- [ ] Committed code to Git

## 📂 What's in this folder

A finished, working version of today's project, with tests. Build your own first, then compare, or read it when you're stuck.

| File | What it does |
|---|---|
| `Book.java` | A library book |
| `BookRepository.java` | Where books are stored, as an interface so tests can swap it |
| `BookService.java` | Adds, lists, checks out and returns books |
| `InMemoryBookRepository.java` | A simple in-memory version |

There's no app to run today: the tests *are* the project. JaCoCo measures how much of the code they cover.

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
