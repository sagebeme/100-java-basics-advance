import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriceWatchTest {

    private static Path folder() throws Exception {
        return Path.of(PriceWatchTest.class.getResource("/listings-1.html").toURI()).getParent();
    }

    @Test
    void cleansPricesWrittenManyWays() {
        assertEquals(1200, Cleaning.priceKes("KES 1,200"));
        assertEquals(2500, Cleaning.priceKes("Ksh 2,500"));
        assertEquals(850, Cleaning.priceKes("850/="));
        assertEquals(1800, Cleaning.priceKes("KES 1.8k"));
        assertEquals(-1, Cleaning.priceKes("SOLD"));
        assertEquals("Air Force 1 sneakers (EU 43)", Cleaning.collapseSpaces("  Air Force 1 sneakers  (EU 43) "));
    }

    @Test
    void readsOnePageAndSkipsSoldItems() {
        String html = "<li class=listing data-sku=a><h3 class=title> Boots </h3><span class=price>SOLD</span></li>"
                + "<li class=listing data-sku=b><h3 class=title>Shirt</h3><span class=price>KES 700</span><span class=tag>Shirts</span></li>";
        List<Listing> found = PriceWatch.readPage(Jsoup.parse(html));
        assertEquals(1, found.size());
        assertEquals(new Listing("b", "Shirt", "shirts", "", 700), found.get(0));
    }

    @Test
    void followsNextLinksThroughEveryPageWithoutDuplicates() throws Exception {
        try (PracticeShop shop = new PracticeShop(folder())) {
            List<Listing> all = new PriceWatch(0, 10).crawl(shop.base().resolve("/listings?page=1"));
            assertEquals(9, all.size()); // 11 cards, minus one repeat and one sold item
            assertEquals(4, shop.requests()); // robots.txt + 3 pages, nothing fetched twice
            assertEquals(1, all.stream().filter(l -> l.sku().equals("m104")).count());
        }
    }

    @Test
    void stopsAtMaxPages() throws Exception {
        try (PracticeShop shop = new PracticeShop(folder())) {
            assertEquals(4, new PriceWatch(0, 1).crawl(shop.base().resolve("/listings?page=1")).size());
        }
    }

    @Test
    void obeysRobotsTxt() {
        RobotsRules rules = new RobotsRules("User-agent: Googlebot\nDisallow: /listings\n\nUser-agent: *\nDisallow: /admin # private\n");
        assertTrue(rules.isAllowed("/listings"));
        assertFalse(rules.isAllowed("/admin/users"));
    }

    @Test
    void findsTheCheapestInEachCategoryAndTheAverage() throws Exception {
        try (PracticeShop shop = new PracticeShop(folder())) {
            List<Listing> all = new PriceWatch(0, 10).crawl(shop.base().resolve("/listings?page=1"));
            Map<String, Listing> cheapest = PriceWatch.cheapestPerCategory(all);
            assertEquals(List.of("jackets", "shirts", "shoes"), List.copyOf(cheapest.keySet()));
            assertEquals("Football jersey, Harambee Stars", cheapest.get("shirts").title());
            assertEquals(900, cheapest.get("shoes").priceKes());
            assertEquals(1333, PriceWatch.averagePrice(all)); // 12,000 / 9
        }
    }
}
