import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StructuredPriceTest {

    private static Document shoes() throws IOException {
        try (InputStream in = StructuredPriceTest.class.getResourceAsStream("/shoes.html")) {
            return Jsoup.parse(new String(in.readAllBytes(), StandardCharsets.UTF_8));
        }
    }

    @Test
    void trustsTheStructuredDataOverTheBanner() throws IOException {
        assertEquals(1499, StructuredPrice.price(shoes()));
        assertEquals("KES", StructuredPrice.currency(shoes()));
    }

    @Test
    void fallsBackToTheVisiblePrice() {
        assertEquals(2350, StructuredPrice.price(Jsoup.parse("<p class=price>KES 2,350</p>")));
    }

    @Test
    void noPriceAtAllIsMinusOne() {
        assertEquals(-1, StructuredPrice.price(Jsoup.parse("<p>Call for price</p>")));
        assertEquals(-1, StructuredPrice.price(Jsoup.parse("<p class=price>Call us</p>")));
    }

    @Test
    void assumesShillingsWhenTheCurrencyIsMissing() {
        assertEquals("KES", StructuredPrice.currency(Jsoup.parse("<p>x</p>")));
        assertEquals("USD", StructuredPrice.currency(Jsoup.parse("<meta itemprop=priceCurrency content=USD>")));
    }
}
