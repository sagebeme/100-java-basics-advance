import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelpDeskPage {

    private final WebDriver driver;

    public HelpDeskPage(WebDriver driver) {
        this.driver = driver;
    }

    public String openTicket(String account, String topic, String details) {
        driver.findElement(By.id("account")).sendKeys(account);
        new Select(driver.findElement(By.id("topic"))).selectByValue(topic);
        driver.findElement(By.id("details")).sendKeys(details);
        driver.findElement(By.id("send")).click();
        String error = driver.findElement(By.id("error")).getText();
        if (!error.isBlank()) throw new IllegalArgumentException(error);
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("ticket-number"))).getText();
    }
}
