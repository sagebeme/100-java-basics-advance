import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JobsPage {

    private final WebDriver driver;

    public JobsPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<Job> jobs() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.attributeToBe(By.id("jobs"), "data-ready", "true"));
        List<Job> jobs = new ArrayList<>();
        for (WebElement a : driver.findElements(By.cssSelector("article.job"))) {
            jobs.add(new Job(a.getDomAttribute("data-id"),
                    a.findElement(By.tagName("h2")).getText(),
                    a.findElement(By.className("company")).getText(),
                    a.findElement(By.className("location")).getText(),
                    Boolean.parseBoolean(a.getDomAttribute("data-remote")),
                    a.findElements(By.cssSelector(".tags li")).stream().map(WebElement::getText).toList()));
        }
        return jobs;
    }
}
