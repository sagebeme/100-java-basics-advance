import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FareTableTest {

    @Test
    void readsPricesWrittenManyWays() {
        assertEquals(70, FareTable.priceKes("KES 70"));
        assertEquals(120, FareTable.priceKes("Ksh. 120"));
        assertEquals(150, FareTable.priceKes(" 150/- "));
        assertEquals(1000, FareTable.priceKes("KES 1,000 (night)"));
        assertEquals(-1, FareTable.priceKes("ask the conductor"));
    }

    @Test
    void keepsOnlyRealFareRows() throws IOException {
        try (InputStream in = FareTableTest.class.getResourceAsStream("/fares.html")) {
            List<FareTable.FareRow> rows = FareTable.read(Jsoup.parse(new String(in.readAllBytes(), StandardCharsets.UTF_8)));
            assertEquals(List.of(
                    new FareTable.FareRow("46", "Kawangware", "Town", 70),
                    new FareTable.FareRow("111", "Ngong", "Town", 120),
                    new FareTable.FareRow("237", "Thika", "Town", 150),
                    new FareTable.FareRow("58", "Buruburu", "Town", 1000)), rows);
        }
    }
}
