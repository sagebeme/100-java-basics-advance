import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CleaningTest {

    @Test
    void collapsesSpacesTabsAndNewLines() {
        assertEquals("Denim jacket, size M", Cleaning.collapseSpaces("  Denim   jacket,\n\tsize M "));
    }

    @Test
    void readsPlainPricesWithCommas() {
        assertEquals(1200, Cleaning.priceKes("KES 1,200"));
        assertEquals(2500, Cleaning.priceKes("Ksh 2,500"));
        assertEquals(850, Cleaning.priceKes("850/="));
    }

    @Test
    void readsThousandsWrittenWithK() {
        assertEquals(1800, Cleaning.priceKes("KES 1.8k"));
        assertEquals(2000, Cleaning.priceKes("2K"));
    }

    @Test
    void noNumberMeansNoPrice() {
        assertEquals(-1, Cleaning.priceKes("SOLD"));
        assertEquals(-1, Cleaning.priceKes(""));
    }
}
