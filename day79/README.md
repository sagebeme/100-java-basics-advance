# Day 79 - Testing Properly: Mocks, Integration Tests and Coverage

> You've written tests for weeks. Today is about testing on purpose: which tests to write, what to fake and what to keep real, and how to see the code your tests never touch.

## 📖 The story

The community library in Kakamega lends out books with an exercise book and a pen. Atieno is writing them a system, and a book can be **checked out** only if it's on the shelf, and **returned** to make it available again. She wants two kinds of test:

- **Unit tests** check `BookService`'s own rules, fast, with the storage *faked*. A Mockito **mock** pretends to be the `BookRepository`, returns whatever the test tells it to, and records what was called.
- **Integration tests** check the pieces *together*: the real `BookService` with a real `InMemoryBookRepository`, doing a whole check-out and return.

Then she runs **JaCoCo**, which shows every line the tests ran and every line they missed. The current tests cover about 93% of the code.

## 🎯 Learning Objectives
- Tell unit tests and integration tests apart, and know what each is for
- Depend on an interface (`BookRepository`) so tests can swap in a fake
- Mock with Mockito: `@Mock`, `when(...).thenReturn(...)`, `verify(...)`
- Test the unhappy paths: an unknown book, a book already checked out
- Measure coverage with JaCoCo, and read the report
- Know what coverage can't tell you

## 🧰 What you'll use

**An interface is what makes faking possible:**

```java
public interface BookRepository {
    Optional<Book> findById(long id);
    List<Book> findAll();
    Book save(Book book);
}
```

`BookService` takes one in its constructor. Production code passes a real one; tests pass a mock or an in-memory one.

**A Mockito unit test:**

```java
@ExtendWith(MockitoExtension.class)
class BookServiceUnitTest {
    @Mock
    private BookRepository bookRepository;

    @Test
    void checkOutMarksAnAvailableBookUnavailable() {
        Book book = new Book(1, "Kifo Kisimani");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));   // arrange

        new BookService(bookRepository).checkOut(1L);                       // act

        assertFalse(book.isAvailable());                                    // assert
        verify(bookRepository).save(book);                                  // and it was saved
    }
}
```

**The unhappy paths matter most:**

```java
assertThrows(NoSuchElementException.class, () -> bookService.checkOut(99L));   // no such book
assertThrows(IllegalStateException.class, () -> bookService.checkOut(1L));     // already out
```

**Coverage.** JaCoCo watches the tests run and writes `target/site/jacoco/index.html`: green lines ran, red lines didn't, yellow branches only went one way. A red line is a question nobody has asked yet. But 100% coverage doesn't mean correct: a test that runs a line without checking its result still counts.

## 🪜 Steps

**Project: The Kakamega library** (the `day79/` folder is a finished version: build your own first, then compare).

1. `Book`, the `BookRepository` interface, and `InMemoryBookRepository`.
2. `BookService`: `addBook`, `listBooks`, `checkOut` and `returnBook`, with clear exceptions.
3. Unit tests with a mocked repository, including both failures.
4. Integration tests with the in-memory repository: a full cycle, a double check-out, an unknown book.
5. Run the coverage report and find the lines nobody tests.
6. Decide what returning a book that's already on the shelf should do, write the test first, then make it pass.

## 🧪 Run it and test it

Needs JDK 21 and Maven. From the repository root:

```bash
cd day79
mvn verify
```

Then open `day79/target/site/jacoco/index.html` in a browser.

## 🆘 Stuck?

- **`UnnecessaryStubbingException`**: you told the mock to return something the test never used. Remove that `when(...)`; Mockito is pointing out a test that says more than it checks.
- **The mock returns `null`**: you didn't stub that call, or the argument doesn't match (`1` is an `int`, `1L` is a `long`).
- **No coverage report**: it's written in the `test` phase by the `jacoco-maven-plugin`. Run `mvn verify` (or `mvn test`), not just `mvn compile`.
- **Everything is mocked and the tests still pass when the app is broken**: that's the limit of unit tests. That's why the integration tests exist.

## ✅ Checklist
- [ ] Unit tests with a mocked repository
- [ ] Integration tests with real parts
- [ ] Tests for every failure, not just the happy path
- [ ] Read the coverage report and closed a gap
- [ ] Decided and tested what returning an available book does
- [ ] Committed code to Git

## ➡️ Next

Day 80 is a capstone: a Spring Boot app that predicts house prices with a model you train yourself.
