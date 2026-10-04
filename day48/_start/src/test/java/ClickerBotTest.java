import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClickerBotTest extends BrowserTest {

    @Test
    void readsTheGameState() {
        driver.get(site.url("/").toString());
        GamePage game = new GamePage(driver);
        game.click(20);
        assertTrue(game.chapatis() >= 20);
        assertEquals(4, game.shop().size());
        assertEquals(15, game.shop().get(0).cost());
    }

    @Test
    void buyingRaisesThePriceAndTheIncome() {
        driver.get(site.url("/").toString());
        GamePage game = new GamePage(driver);
        game.click(15);
        game.buy("pan");
        assertEquals(1, game.ratePerSecond());
        assertEquals(17, game.shop().get(0).cost()); // 15 × 1.15, rounded
    }

    @Test
    void theBotBuildsUpAnIncome() {
        driver.get(site.url("/").toString());
        GamePage game = new GamePage(driver);
        int bought = ClickerBot.play(game, 8, 25);
        assertTrue(bought >= 3, "bought " + bought);
        assertTrue(game.ratePerSecond() >= 3);
    }
}
