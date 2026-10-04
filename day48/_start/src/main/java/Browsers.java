import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browsers {

    // Chrome with no window. Selenium Manager finds (or downloads) the matching driver for you.
    public static WebDriver headlessChrome() {
        ChromeOptions options = new ChromeOptions().addArguments("--headless=new", "--window-size=1280,900");
        return new ChromeDriver(options);
    }

    // A visible Chrome window, so you can watch the bot work.
    public static WebDriver visibleChrome() {
        return new ChromeDriver();
    }
}
