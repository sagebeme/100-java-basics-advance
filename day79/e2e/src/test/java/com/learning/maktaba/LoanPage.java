package com.learning.maktaba;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class LoanPage {

    private final WebDriver driver;

    // Waits for the slip: after a keypress (unlike a click), the next page may not have loaded yet.
    LoanPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.id("slip")));
    }

    String code() {
        return driver.findElement(By.id("loan-code")).getText();
    }

    String title() {
        return driver.findElement(By.id("loan-title")).getText();
    }

    String due() {
        return driver.findElement(By.id("due")).getText();
    }

    CataloguePage backToShelves() {
        driver.findElement(By.id("back")).click();
        return new CataloguePage(driver);
    }
}
