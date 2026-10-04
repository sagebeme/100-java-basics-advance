package com.learning.maktaba;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// The bottom of the pyramid: no server, no browser, milliseconds. Most of the rules are checked here.
class LibraryServiceTest {

    private final LibraryService library = new LibraryService(
            Clock.fixed(Instant.parse("2026-10-05T06:00:00Z"), ZoneId.of("Africa/Nairobi")));

    @Test
    void borrowingTakesACopyAndSetsTheDueDate() {
        Loan loan = library.borrow(1, "M-1234", 14);
        assertEquals("MK-1-1001", loan.code());
        assertEquals(LocalDate.of(2026, 10, 19), loan.due());
        assertEquals(2, library.find(1).orElseThrow().getCopiesLeft());
    }

    @Test
    void theLastCopyCanGoButNoMore() {
        library.borrow(3, "M-1234", 7);
        assertThrows(NoCopiesLeftException.class, () -> library.borrow(3, "M-5678", 7));
    }

    @Test
    void resetPutsTheShelvesBack() {
        library.borrow(1, "M-1234", 7);
        library.reset();
        assertEquals(3, library.find(1).orElseThrow().getCopiesLeft());
    }
}
