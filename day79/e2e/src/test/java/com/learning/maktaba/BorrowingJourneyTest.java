package com.learning.maktaba;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Whole journeys, through the real server and a real browser. Few of them, because each one is slow.
class BorrowingJourneyTest extends EndToEndTest {

    @Test
    void theShelvesShowEveryBookAndWhatIsLeft() {
        CataloguePage shelves = CataloguePage.open(driver, baseUrl());
        assertEquals(List.of("Weep Not, Child", "Coming to Birth", "Dust", "The River Between"), shelves.titles());
        assertEquals("3 on the shelf", shelves.copies("Weep Not, Child"));
        assertEquals("All out on loan", shelves.copies("The River Between"));
        assertFalse(shelves.canBorrow("The River Between"));
    }

    @Test
    void aReaderBorrowsABookAndGetsASlip() {
        LoanPage slip = CataloguePage.open(driver, baseUrl())
                .borrow("Coming to Birth")
                .borrowAs("M-1234", "2 weeks");
        assertEquals("Coming to Birth", slip.title());
        assertEquals("MK-2-1001", slip.code());
        assertEquals("Monday 19 October 2026", slip.due());
        assertEquals("1 on the shelf", slip.backToShelves().copies("Coming to Birth"));
    }

    @Test
    void aMistypedCardIsExplainedAndNothingIsBorrowed() {
        BorrowPage form = CataloguePage.open(driver, baseUrl()).borrow("Dust");
        form.submit("1234", "1 week");
        assertEquals(List.of("A library card number looks like M-1234."), form.errors());
        assertEquals("1 on the shelf", CataloguePage.open(driver, baseUrl()).copies("Dust"));
    }

    @Test
    void twoReadersRaceForTheLastCopy() {
        // Both open the form while one copy is left; the first to press Borrow wins.
        BorrowPage first = CataloguePage.open(driver, baseUrl()).borrow("Dust");
        String formUrl = driver.getCurrentUrl();
        first.borrowAs("M-1111", "1 week");

        driver.get(formUrl); // the second reader's form, opened before the copy went
        BorrowPage second = new BorrowPage(driver);
        second.submit("M-2222", "1 week");
        assertEquals(List.of("Every copy of \"Dust\" is out on loan."), second.errors());
        assertFalse(CataloguePage.open(driver, baseUrl()).canBorrow("Dust"));
    }

    @Test
    void refreshingTheSlipDoesNotBorrowTwice() {
        CataloguePage.open(driver, baseUrl()).borrow("Weep Not, Child").borrowAs("M-1234", "1 week");
        driver.navigate().refresh(); // a GET, thanks to Post/Redirect/Get
        assertEquals("2 on the shelf", CataloguePage.open(driver, baseUrl()).copies("Weep Not, Child"));
    }

    @Test
    void theWholeJourneyWorksWithTheKeyboardAlone() {
        driver.get(baseUrl() + "/borrow/1");
        // Type the card, Tab to the dropdown and type "3" to pick "3 weeks", Tab to the button, Enter.
        // (Enter on the dropdown itself does nothing: the first version of this test learned that the hard way.)
        driver.findElement(By.id("memberNumber")).sendKeys("M-4321", Keys.TAB, "3", Keys.TAB, Keys.ENTER);
        LoanPage slip = new LoanPage(driver);
        assertEquals("MK-1-1001", slip.code());
        assertEquals("Monday 26 October 2026", slip.due()); // typing "3" chose "3 weeks"
    }

    @Test
    void fitsAPhoneScreen() {
        driver.manage().window().setSize(new Dimension(390, 844));
        CataloguePage.open(driver, baseUrl());
        Long overflow = (Long) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.documentElement.scrollWidth - document.documentElement.clientWidth;");
        assertEquals(0L, overflow, "the page is wider than the phone");
    }
}
