package com.learning;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String page(String url) throws Exception {
        return mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    // The <a ...> tags that carry aria-current="page".
    private static String currentLinks(String html) {
        StringBuilder found = new StringBuilder();
        Matcher m = Pattern.compile("<a [^>]*aria-current=\"page\"[^>]*>").matcher(html);
        while (m.find()) {
            found.append(m.group());
        }
        return found.toString();
    }

    @Test
    void everyPageHasTheSameHeaderWithThreeLinks() throws Exception {
        for (String url : new String[] {"/", "/tours", "/about"}) {
            String html = page(url);
            assertTrue(html.contains("Pwani Day Trips"), url);
            assertTrue(html.contains("href=\"/\""), url + " links Home");
            assertTrue(html.contains("href=\"/tours\""), url + " links Tours");
            assertTrue(html.contains("href=\"/about\""), url + " links About");
        }
    }

    @Test
    void onlyTheCurrentPagesLinkIsMarked() throws Exception {
        assertTrue(currentLinks(page("/")).contains("href=\"/\""));
        assertTrue(currentLinks(page("/tours")).contains("href=\"/tours\""));
        assertTrue(currentLinks(page("/about")).contains("href=\"/about\""));
        for (String url : new String[] {"/", "/tours", "/about"}) {
            assertEquals(1, currentLinks(page(url)).split("<a ").length - 1, url + " marks exactly one link");
        }
    }

    @Test
    void everyPageHasTheSameFooter() throws Exception {
        for (String url : new String[] {"/", "/tours", "/about"}) {
            assertTrue(page(url).contains("Pwani Day Trips · Moi Avenue, Mombasa · 0712 345 678"), url);
        }
    }

    @Test
    void theHeaderAndFooterReplaceThePlaceholdersInsteadOfNestingInsideThem() throws Exception {
        String html = page("/about");
        assertEquals(1, html.split("<header").length - 1, "one <header>, not one inside another");
        assertEquals(1, html.split("<footer").length - 1, "one <footer>, not one inside another");
    }

    @Test
    void theToursPageListsEveryTripWithItsPrice() throws Exception {
        String html = page("/tours");
        assertTrue(html.contains("<title>Day trips</title>"));
        assertTrue(html.contains("Fort Jesus and Old Town walk"));
        assertTrue(html.contains("Wasini Island and dolphins"));
        assertTrue(html.contains("Shimoni"));
        assertTrue(html.contains("Haller Park at feeding time"));
        assertTrue(html.contains("KES 7,800"), "prices use a thousands comma");
        assertTrue(html.contains("KES 1,500"));
    }
}
