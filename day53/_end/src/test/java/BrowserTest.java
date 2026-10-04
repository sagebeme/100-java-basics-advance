import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

// Starts the practice site and a headless Chrome for each test. If Chrome can't start (no Chrome
// installed, or no network to fetch a driver the first time), the browser tests are skipped, not failed.
public abstract class BrowserTest {

    protected PracticeSite site;
    protected WebDriver driver;

    @BeforeEach
    void startBrowser() throws Exception {
        site = new PracticeSite();
        try {
            driver = Browsers.headlessChrome();
        } catch (RuntimeException e) {
            site.close();
            Assumptions.abort("No browser available: " + e.getMessage().lines().findFirst().orElse(""));
        }
    }

    @AfterEach
    void stopBrowser() {
        if (driver != null) driver.quit();
        if (site != null) site.close();
    }
}
