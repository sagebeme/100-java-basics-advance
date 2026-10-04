import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GamePage {

    private final WebDriver driver;
    private final WebElement chapati;

    public GamePage(WebDriver driver) {
        this.driver = driver;
        this.chapati = driver.findElement(By.id("chapati"));
    }

    public void click(int times) {
        for (int i = 0; i < times; i++) chapati.click();
    }

    // The page redraws itself ten times a second. If it redraws while we're reading, the element we
    // found is "stale" (replaced); the fix is to find it again and retry.
    static <T> T retrying(Supplier<T> read) {
        for (int attempt = 1; ; attempt++) {
            try {
                return read.get();
            } catch (StaleElementReferenceException e) {
                if (attempt == 3) throw e;
            }
        }
    }

    public long chapatis() {
        return retrying(() -> Long.parseLong(driver.findElement(By.id("count")).getText()));
    }

    public int ratePerSecond() {
        return retrying(() -> Integer.parseInt(driver.findElement(By.id("rate")).getText()));
    }

    public List<Upgrade> shop() {
        return retrying(this::readShop);
    }

    private List<Upgrade> readShop() {
        List<Upgrade> shop = new ArrayList<>();
        for (WebElement b : driver.findElements(By.cssSelector("button.upgrade"))) {
            shop.add(new Upgrade(b.getDomAttribute("id").substring(4),
                    Integer.parseInt(b.getDomAttribute("data-cost")),
                    Integer.parseInt(b.getDomAttribute("data-rate")),
                    Integer.parseInt(b.getDomAttribute("data-owned"))));
        }
        return shop;
    }

    public void buy(String upgradeId) {
        driver.findElement(By.id("buy-" + upgradeId)).click();
    }
}
