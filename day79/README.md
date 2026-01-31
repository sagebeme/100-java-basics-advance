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

**When you're stuck:** Re-read **Step-by-Step Instructions** above; use main README for structure. **Quick reference:** `mvn test` for tests; run app from IDE or `mvn spring-boot:run`.

## ✅ Checklist
- [ ] Can write unit tests
- [ ] Can use mocking
- [ ] Can test integration
- [ ] Completed test suite
- [ ] Committed code to Git

## 💻 How to Run

**Run:** `mvn test` to run tests; `mvn spring-boot:run` or run main Application from IDE for the app.

## 🚀 Next Steps
**Ready for Day 80?** You'll build the House Price Prediction capstone!






