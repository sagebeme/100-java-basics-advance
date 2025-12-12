# Day 47 - Selenium WebDriver Automation

## 📚 Learning Objectives
- Understand browser automation
- Use Selenium WebDriver
- Automate web interactions
- Handle dynamic content
- Build automation scripts

## 🎯 Topics Covered
- Selenium WebDriver
- Browser automation
- Element interaction
- Waits and synchronization
- Form filling
- Screenshot capture

## 📝 Step-by-Step Instructions

### Step 1: Selenium Setup
Add Selenium dependency:

**pom.xml:**
```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.15.0</version>
</dependency>
```

**Download WebDriver:**
- Chrome: [ChromeDriver](https://chromedriver.chromium.org/)
- Firefox: [GeckoDriver](https://github.com/mozilla/geckodriver)

### Step 2: Basic Automation
Automate browser:

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SeleniumExample {
    public static void main(String[] args) {
        // Set driver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        
        WebDriver driver = new ChromeDriver();
        
        // Navigate to page
        driver.get("https://example.com");
        
        // Find and interact with elements
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Java Selenium");
        searchBox.submit();
        
        // Close browser
        driver.quit();
    }
}
```

### Step 3: Element Interaction
Interact with page elements:

```java
// Find elements
WebElement element = driver.findElement(By.id("username"));
WebElement button = driver.findElement(By.className("submit-btn"));
List<WebElement> links = driver.findElements(By.tagName("a"));

// Interact
element.sendKeys("text");
element.clear();
element.click();
button.click();

// Get text
String text = element.getText();
String value = element.getAttribute("value");
```

### Step 4: Waits
Handle dynamic content:

```java
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

// Wait for element
WebElement element = wait.until(
    ExpectedConditions.presenceOfElementLocated(By.id("dynamic-content"))
);

// Wait for clickable
WebElement button = wait.until(
    ExpectedConditions.elementToBeClickable(By.id("submit"))
);
```

## 💻 Exercises

### Exercise 1: Basic Automation
Automate:
- Page navigation
- Form filling
- Button clicking
- Text extraction

### Exercise 2: Dynamic Content
Handle:
- Loading waits
- Dynamic elements
- AJAX content
- JavaScript rendering

### Exercise 3: Complex Interactions
Implement:
- Multiple pages
- Dropdowns
- Checkboxes
- File uploads

## 🎮 Project: Form Automation Bot

### Requirements
Create form automation:
1. Navigate to form page
2. Fill form fields
3. Submit form
4. Verify submission
5. Handle errors
6. Take screenshots

### Example Output
```
Form Automation Bot

Navigating to form...
Filling form fields...
- Name: John Doe
- Email: john@example.com
- Message: Test message

Submitting form...
✓ Form submitted successfully!

Screenshot saved: form_submission.png
```

### Starter Code
```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.File;

public class FormBot {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        
        try {
            driver.get("https://example.com/form");
            
            // Fill form
            driver.findElement(By.id("name")).sendKeys("John Doe");
            driver.findElement(By.id("email")).sendKeys("john@example.com");
            driver.findElement(By.id("message")).sendKeys("Test message");
            
            // Submit
            driver.findElement(By.id("submit")).click();
            
            // Screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            screenshot.renameTo(new File("form_submission.png"));
            
        } finally {
            driver.quit();
        }
    }
}
```

## 📚 Resources

### Official Documentation
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [WebDriver API](https://www.selenium.dev/selenium/docs/api/java/)
- [ChromeDriver](https://chromedriver.chromium.org/)

### Tutorials
- [Selenium Java Tutorial](https://www.selenium.dev/documentation/webdriver/)
- [Selenium Guide - Baeldung](https://www.baeldung.com/java-selenium-webdriver)
- [Selenium Examples](https://www.guru99.com/selenium-java-tutorial.html)

### Video Resources
- [Selenium Tutorial](https://www.youtube.com/results?search_query=selenium+java+tutorial)
- [WebDriver Automation](https://www.youtube.com/results?search_query=selenium+webdriver+java)

### Practice Platforms
- [Selenium Practice](https://the-internet.herokuapp.com/)

## 🔑 Key Concepts Summary

| Concept | Description | Example |
|---------|-------------|---------|
| WebDriver | Browser control | `ChromeDriver`, `FirefoxDriver` |
| Element Location | Find elements | `By.id()`, `By.className()` |
| Waits | Synchronization | `WebDriverWait`, `ExpectedConditions` |
| Actions | Interact | `sendKeys()`, `click()`, `submit()` |
| Screenshots | Capture | `TakesScreenshot` |

## ✅ Checklist
- [ ] Understand Selenium
- [ ] Can automate browsers
- [ ] Can interact with elements
- [ ] Can handle waits
- [ ] Can take screenshots
- [ ] Completed Form Bot
- [ ] Committed code to Git

## 🚀 Next Steps
After completing Day 47, you should be able to:
- Automate web browsers
- Interact with web pages
- Handle dynamic content
- Build automation tools

**Ready for Day 48?** You'll build a Game Playing Bot!

