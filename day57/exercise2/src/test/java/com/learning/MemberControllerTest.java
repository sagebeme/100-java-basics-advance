package com.learning;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String page(int id) throws Exception {
        return mockMvc.perform(get("/members/" + id))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    private static int count(String html, String regex) {
        Matcher m = Pattern.compile(regex).matcher(html);
        int n = 0;
        while (m.find()) {
            n++;
        }
        return n;
    }

    @Test
    void eachRoleGetsItsOwnLineAndOnlyThatOne() throws Exception {
        String chair = page(1);
        assertTrue(chair.contains("Chairperson: you can approve loans."));
        assertFalse(chair.contains("Treasurer:"));
        String treasurer = page(2);
        assertTrue(treasurer.contains("Treasurer: you can record contributions."));
        assertFalse(treasurer.contains("Chairperson:"));
        String member = page(3);
        assertTrue(member.contains("<p>Member</p>"), member);
        assertFalse(member.contains("Chairperson:") || member.contains("Treasurer:"));
    }

    @Test
    void showsSavingsWithACommaForThousands() throws Exception {
        assertTrue(page(1).contains("Savings: KES 84,000"));
        assertTrue(page(3).contains("Savings: KES 0"));
    }

    @Test
    void sixMonthsOfSavingUnlocksALoanOfThreeTimesSavings() throws Exception {
        String chair = page(1);
        assertTrue(chair.contains("You can borrow up to KES 252,000."));
        assertFalse(chair.contains("You can borrow after"));
    }

    @Test
    void beforeSixMonthsItSaysHowLongToGo() throws Exception {
        String treasurer = page(2);
        assertTrue(treasurer.contains("You can borrow after 6 months of saving. 2 to go."));
        assertFalse(treasurer.contains("You can borrow up to"));
    }

    @Test
    void theStatementHasANumberedRowPerEntry() throws Exception {
        String html = page(1);
        assertEquals(3, count(html, "<td>\\d+</td>\\s*<td>2026-"), "one row per entry, each starting with its number");
        assertTrue(html.matches("(?s).*<td>3</td>\\s*<td>2026-10-31</td>.*"), "the third row is numbered 3");
        assertTrue(html.contains("Loan repayment"));
        assertTrue(html.contains("-4,500"));
    }

    @Test
    void onlyMoneyGoingOutIsMarked() throws Exception {
        String html = page(1);
        assertEquals(1, count(html, "<tr class=\"out\">"), "only the repayment row has class out");
    }

    @Test
    void anEmptyStatementSaysSoAndHasNoTable() throws Exception {
        String html = page(3);
        assertTrue(html.contains("No transactions yet."));
        assertFalse(html.contains("<table"));
        assertFalse(page(1).contains("No transactions yet."));
    }
}
