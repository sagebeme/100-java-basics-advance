package com.learning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test: BookService is wired to a real InMemoryBookRepository (no mocks), so this
 * exercises the two classes working together end to end.
 */
class BookServiceIntegrationTest {

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(new InMemoryBookRepository());
    }

    @Test
    void aFullCheckOutAndReturnCycleWorksAgainstRealStorage() {
        bookService.addBook(new Book(1, "Effective Java"));

        bookService.checkOut(1L);
        assertFalse(bookService.listBooks().get(0).isAvailable());

        bookService.returnBook(1L);
        assertTrue(bookService.listBooks().get(0).isAvailable());
    }

    @Test
    void checkingOutTheSameBookTwiceFailsTheSecondTime() {
        bookService.addBook(new Book(1, "Effective Java"));
        bookService.checkOut(1L);

        assertThrows(IllegalStateException.class, () -> bookService.checkOut(1L));
    }

    @Test
    void listBooksReflectsEveryBookAddedAcrossTheFullStack() {
        bookService.addBook(new Book(1, "Effective Java"));
        bookService.addBook(new Book(2, "Clean Code"));

        assertEquals(2, bookService.listBooks().size());
    }

    @Test
    void operatingOnAnUnknownBookFailsAcrossTheFullStack() {
        assertThrows(NoSuchElementException.class, () -> bookService.checkOut(404L));
    }
}
