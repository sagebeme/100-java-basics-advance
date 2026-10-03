import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneBookTest {

    private final Map<String, String> book = Map.of(
            "Wanjiru", "0712 345 678 ",
            "Otieno", " 0722 111 222");

    @Test
    void findsANumberAndTrimsIt() {
        assertEquals("0712 345 678", PhoneBook.phoneFor(book, "Wanjiru"));
    }

    @Test
    void someoneNotInTheBookDoesntCrashTheProgram() {
        assertEquals("no number on file", PhoneBook.phoneFor(book, "Achieng"));
    }

    @Test
    void findsANameTypedInLowerCase() {
        assertEquals("0722 111 222", PhoneBook.phoneFor(book, "otieno"));
    }
}
