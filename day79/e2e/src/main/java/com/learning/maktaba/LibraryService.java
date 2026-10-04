package com.learning.maktaba;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

// The library's shelves and loans, in memory. synchronized, because a web server answers many people at once.
@Service
public class LibraryService {

    private final Clock clock;
    private final Map<Long, Book> books = new LinkedHashMap<>();
    private final Map<String, Loan> loans = new LinkedHashMap<>();

    public LibraryService(Clock clock) {
        this.clock = clock;
        reset();
    }

    // Puts the shelves back the way they start. Tests call this so each one begins from the same state.
    public synchronized void reset() {
        books.clear();
        loans.clear();
        for (Book b : List.of(
                new Book(1, "Weep Not, Child", "Ngũgĩ wa Thiong'o", 3),
                new Book(2, "Coming to Birth", "Marjorie Oludhe Macgoye", 2),
                new Book(3, "Dust", "Yvonne Adhiambo Owuor", 1),
                new Book(4, "The River Between", "Ngũgĩ wa Thiong'o", 0))) {
            books.put(b.getId(), b);
        }
    }

    public synchronized List<Book> catalogue() {
        return new ArrayList<>(books.values());
    }

    public synchronized Optional<Book> find(long id) {
        return Optional.ofNullable(books.get(id));
    }

    public synchronized Loan borrow(long bookId, String memberNumber, int days) {
        Book book = find(bookId).orElseThrow(() -> new IllegalArgumentException("No book " + bookId));
        book.takeOneCopy();
        String code = "MK-" + bookId + "-" + (loans.size() + 1001);
        Loan loan = new Loan(code, bookId, book.getTitle(), memberNumber, LocalDate.now(clock).plusDays(days));
        loans.put(code, loan);
        return loan;
    }

    public synchronized Optional<Loan> loan(String code) {
        return Optional.ofNullable(loans.get(code));
    }
}
