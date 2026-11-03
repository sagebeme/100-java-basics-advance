package com.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String card(int id) throws Exception {
        return mockMvc.perform(get("/card/" + id))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void greetsTheGuestByNameInTheTitleAndHeading() throws Exception {
        String html = card(7);
        assertTrue(html.contains("<title>Harambee card for Mama Atieno</title>"), html);
        assertTrue(html.contains("Karibu, Mama Atieno!"));
    }

    @Test
    void saysWhatItsForWhenAndWhere() throws Exception {
        String html = card(7);
        assertTrue(html.contains("You are invited to a harambee for Achieng&#39;s school fees at Moi University."), html);
        assertTrue(html.contains("Saturday 12 December 2026 at St. Paul&#39;s Hall, Kisumu"), html);
        assertTrue(html.contains("Send by M-Pesa: Paybill 247247, account FEES-ACHIENG"));
    }

    @Test
    void thanksAGuestForTheirPledgeWithACommaInTheAmount() throws Exception {
        assertTrue(card(7).contains("Thank you for pledging KES 5,000."));
        assertFalse(card(8).contains("Thank you for pledging"), "no pledge, no thank-you line");
    }

    @Test
    void buildsTheLinksFromTheGuestsId() throws Exception {
        String html = card(8);
        assertTrue(html.contains("href=\"/card/8/pledge\""), html);
        assertTrue(html.contains("href=\"/rsvp?guest=8&amp;coming=yes\""), html);
    }

    @Test
    void aNameIsShownAsTextNeverRunAsCode() throws Exception {
        String html = card(9);
        assertFalse(html.contains("<script>alert"), "th:text must escape the name");
        assertTrue(html.contains("&lt;script&gt;alert"));
    }

    @Test
    void anUnknownGuestIs404() throws Exception {
        mockMvc.perform(get("/card/1")).andExpect(status().isNotFound());
    }
}
