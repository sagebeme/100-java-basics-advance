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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String page(String url) throws Exception {
        return mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    private String submit(String name, String phone, String county) throws Exception {
        return mockMvc.perform(post("/join").param("name", name).param("phone", phone).param("county", county))
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

    // The opening tag of the element with this id.
    private static String tagWithId(String html, String id) {
        Matcher m = Pattern.compile("<[a-z]+\\s[^>]*id=\"" + id + "\"[^>]*>").matcher(html);
        return m.find() ? m.group() : "";
    }

    @Test
    void aCardForEveryItemInAResponsiveGrid() throws Exception {
        String html = page("/prices");
        assertEquals(4, count(html, "class=\"col-12 col-sm-6 col-lg-3\""), "one column per item: 1, 2 then 4 across");
        assertEquals(4, count(html, "class=\"card[ \"]"));
        assertTrue(html.contains("Avocado (Hass)") && html.contains("French beans"));
    }

    @Test
    void pricesShowTheUnitAndAThousandsComma() throws Exception {
        String html = page("/prices");
        assertTrue(html.contains("KES 45 / kg"));
        assertTrue(html.contains("KES 300 / bundle"));
        assertTrue(html.contains("KES 1,200 / crate"));
    }

    @Test
    void aBadgeSaysWhetherItsInSeason() throws Exception {
        String html = page("/prices");
        assertEquals(3, count(html, "class=\"badge[^\"]*\">In season<"));
        assertEquals(1, count(html, "class=\"badge[^\"]*\">Out of season<"));
    }

    @Test
    void theJoinFormHasLabelledFieldsAndAListOfCounties() throws Exception {
        String html = page("/join");
        assertTrue(html.matches("(?s).*<form[^>]*method=\"post\"[^>]*>.*") && html.contains("action=\"/join\""));
        for (String field : new String[] {"name", "phone", "county"}) {
            assertTrue(html.contains("<label for=\"" + field + "\""), "a label for " + field);
            assertTrue(tagWithId(html, field).contains("name=\"" + field + "\""), field + " is sent as " + field);
        }
        assertTrue(tagWithId(html, "name").contains("form-control"));
        assertTrue(tagWithId(html, "county").contains("form-select"));
        assertTrue(html.contains("<option value=\"Tharaka-Nithi\">Tharaka-Nithi</option>"), html);
        assertFalse(html.contains("is-invalid"), "a fresh form shows no errors");
    }

    @Test
    void aBadFormMarksEachBrokenFieldAndSaysWhy() throws Exception {
        String html = submit("", "0712", "Nairobi");
        assertTrue(tagWithId(html, "name").contains("is-invalid"));
        assertTrue(tagWithId(html, "phone").contains("is-invalid"));
        assertTrue(tagWithId(html, "county").contains("is-invalid"));
        assertTrue(html.contains("<div class=\"invalid-feedback\">Type your name as it is on your ID.</div>"));
        assertTrue(html.contains("<div class=\"invalid-feedback\">Use your M-Pesa number, like 0712 345 678.</div>"));
        assertTrue(html.contains("<div class=\"invalid-feedback\">Choose the county where you farm.</div>"));
    }

    @Test
    void onlyTheBrokenFieldIsMarkedAndWhatWasTypedIsKept() throws Exception {
        String html = submit("Kendi Mwiti", "07123", "Embu");
        assertFalse(tagWithId(html, "name").contains("is-invalid"));
        assertTrue(tagWithId(html, "name").contains("value=\"Kendi Mwiti\""), "keep the name");
        assertTrue(tagWithId(html, "phone").contains("value=\"07123\""), "keep the phone so it can be fixed");
        assertTrue(html.contains("<option value=\"Embu\" selected=\"selected\">Embu</option>"), "keep the county");
        assertEquals(1, count(html, "invalid-feedback\">"));
    }

    @Test
    void aGoodFormGoesToThePricesWithAWelcome() throws Exception {
        mockMvc.perform(post("/join").param("name", "Kendi Mwiti").param("phone", "0712 345 678").param("county", "Meru"))
                .andExpect(redirectedUrl("/prices"))
                .andExpect(flash().attribute("joined", "Karibu, Kendi Mwiti! We'll call you this week."));
        String html = mockMvc.perform(get("/prices").flashAttr("joined", "Karibu, Kendi Mwiti! We'll call you this week."))
                .andReturn().getResponse().getContentAsString();
        assertTrue(html.matches("(?s).*class=\"alert alert-success\"[^>]*>Karibu, Kendi Mwiti! We&#39;ll call you this week\\.<.*"), html);
        assertFalse(page("/prices").contains("alert-success"), "no alert without a welcome");
    }
}
