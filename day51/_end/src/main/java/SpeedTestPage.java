import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpeedTestPage {

    private final WebDriver driver;

    public SpeedTestPage(WebDriver driver) {
        this.driver = driver;
    }

    // Presses GO and waits for the test to finish. The live number on the dial changes the whole time,
    // so the bot reads the final results from data attributes, not from the animation.
    public SpeedResult runOnce() {
        WebElement results = driver.findElement(By.id("results"));
        driver.findElement(By.id("go")).click();
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(d -> "done".equals(results.getDomAttribute("data-state"))
                && d.findElement(By.id("go")).isEnabled());
        return new SpeedResult(Double.parseDouble(results.getDomAttribute("data-down")),
                Double.parseDouble(results.getDomAttribute("data-up")),
                Integer.parseInt(results.getDomAttribute("data-ping")));
    }
}
