import java.util.Optional;
import org.openqa.selenium.WebDriver;

public class ClickerBot {

    // Click in bursts; after each burst, buy whatever the strategy says (unless it's worth saving up).
    public static int play(GamePage game, int bursts, int clicksPerBurst) {
        int bought = 0;
        // TODO: for each burst: click, then keep buying Strategy.bestBuy(...) until there's nothing
        //       affordable or Strategy.shouldSaveFor(...) says wait. Count what you buy.
        return bought;
    }

    public static void main(String[] args) throws Exception {
        boolean watch = args.length > 0 && args[0].equals("--watch");
        try (PracticeSite site = new PracticeSite()) {
            WebDriver driver = watch ? Browsers.visibleChrome() : Browsers.headlessChrome();
            try {
                driver.get(site.url("/").toString());
                GamePage game = new GamePage(driver);
                long start = System.currentTimeMillis();
                int bought = play(game, 20, 25);
                System.out.printf("In %.1f seconds: %d upgrades bought, %d chapatis in the pan, %d a second.%n",
                        (System.currentTimeMillis() - start) / 1000.0, bought, game.chapatis(), game.ratePerSecond());
            } finally {
                driver.quit();
            }
        }
    }
}
