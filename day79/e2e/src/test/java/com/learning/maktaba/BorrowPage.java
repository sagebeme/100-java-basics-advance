package com.learning.maktaba;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

class BorrowPage {

    private final WebDriver driver;

    BorrowPage(WebDriver driver) {
        this.driver = driver;
    }

    String bookTitle() {
        return driver.findElement(By.id("book-title")).getText();
    }

    // Fills in the form and submits it. The next page is either the loan slip or this form again with errors.
    void submit(String cardNumber, String weeksLabel) {
        WebElement card = driver.findElement(By.id("memberNumber"));
        card.clear();
        card.sendKeys(cardNumber);
        new Select(driver.findElement(By.id("days"))).selectByVisibleText(weeksLabel);
        driver.findElement(By.id("confirm")).click();
    }

    LoanPage borrowAs(String cardNumber, String weeksLabel) {
        submit(cardNumber, weeksLabel);
        return new LoanPage(driver);
    }

    List<String> errors() {
        return driver.findElements(By.cssSelector(".error, .problem")).stream().map(WebElement::getText).toList();
    }
}
