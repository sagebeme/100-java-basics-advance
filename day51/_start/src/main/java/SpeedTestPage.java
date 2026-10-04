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
        // TODO: click #go; wait until #results has data-state="done" and #go is enabled again;
        //       read data-down, data-up and data-ping
        return new SpeedResult(0, 0, 0);
    }
}
