package com.learning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests: the repository is mocked so these test BookService's own logic in isolation,
 * without touching any real storage.
 */
@ExtendWith(MockitoExtension.class)
class BookServiceUnitTest {

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookRepository);
    }

    @Test
    void addBookDelegatesToTheRepository() {
        Book book = new Book(1, "Effective Java");
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.addBook(book);

        assertSame(book, result);
        verify(bookRepository).save(book);
    }

    @Test
    void listBooksReturnsWhateverTheRepositoryHas() {
        List<Book> books = List.of(new Book(1, "Effective Java"), new Book(2, "Clean Code"));
        when(bookRepository.findAll()).thenReturn(books);

        assertEquals(books, bookService.listBooks());
    }

    @Test
    void checkOutMarksAnAvailableBookUnavailable() {
        Book book = new Book(1, "Effective Java");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.checkOut(1L);

        assertFalse(book.isAvailable());
        verify(bookRepository).save(book);
    }

    @Test
    void checkOutThrowsWhenTheBookIsAlreadyCheckedOut() {
        Book book = new Book(1, "Effective Java");
        book.setAvailable(false);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        assertThrows(IllegalStateException.class, () -> bookService.checkOut(1L));
        verify(bookRepository, never()).save(any());
    }

    @Test
    void checkOutThrowsWhenTheBookDoesNotExist() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> bookService.checkOut(99L));
    }

    @Test
    void returnBookMarksItAvailableAgain() {
        Book book = new Book(1, "Effective Java");
        book.setAvailable(false);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.returnBook(1L);

        assertTrue(book.isAvailable());
        verify(bookRepository).save(book);
    }
}
