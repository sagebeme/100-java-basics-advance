package com.learning.maktaba;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Page objects: the tests say what a reader does; these classes know where the buttons are.
class CataloguePage {

    private final WebDriver driver;

    CataloguePage(WebDriver driver) {
        this.driver = driver;
    }

    static CataloguePage open(WebDriver driver, String baseUrl) {
        driver.get(baseUrl + "/");
        return new CataloguePage(driver);
    }

    List<String> titles() {
        return driver.findElements(By.cssSelector("#shelf .book h2")).stream().map(WebElement::getText).toList();
    }

    private WebElement book(String title) {
        return driver.findElements(By.cssSelector("#shelf .book")).stream()
                .filter(b -> b.findElement(By.tagName("h2")).getText().equals(title))
                .findFirst().orElseThrow(() -> new AssertionError("No book called " + title + " on the shelf"));
    }

    String copies(String title) {
        return book(title).findElement(By.className("copies")).getText();
    }

    boolean canBorrow(String title) {
        return !book(title).findElements(By.cssSelector("a.borrow")).isEmpty();
    }

    BorrowPage borrow(String title) {
        book(title).findElement(By.cssSelector("a.borrow")).click();
        return new BorrowPage(driver);
    }
}
