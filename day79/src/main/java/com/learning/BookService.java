package com.learning;

import java.util.List;
import java.util.NoSuchElementException;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public void checkOut(long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("No book with id " + bookId));
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book " + bookId + " is already checked out");
        }
        book.setAvailable(false);
        bookRepository.save(book);
    }

    public void returnBook(long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("No book with id " + bookId));
        book.setAvailable(true);
        bookRepository.save(book);
    }
}
