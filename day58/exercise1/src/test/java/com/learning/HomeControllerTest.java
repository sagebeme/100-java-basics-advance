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
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String home() throws Exception {
        return mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    // The first tag whose text contains `needle`.
    private static String tagWith(String html, String needle) {
        Matcher m = Pattern.compile("<[a-z]+\\s[^>]*" + Pattern.quote(needle) + "[^>]*>").matcher(html);
        return m.find() ? m.group() : "";
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
    void hasTheViewportTagSoPhonesDontZoomOut() throws Exception {
        assertTrue(home().contains("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"));
    }

    @Test
    void loadsBootstrapCssWithAnIntegrityCheck() throws Exception {
        String link = tagWith(home(), "bootstrap@5.3.3/dist/css/bootstrap.min.css");
        assertTrue(link.startsWith("<link"), "link Bootstrap 5.3.3's CSS from cdn.jsdelivr.net");
        assertTrue(link.contains("integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\""), link);
        assertTrue(link.contains("crossorigin=\"anonymous\""), link);
    }

    @Test
    void loadsBootstrapJavaScriptLastWithAnIntegrityCheck() throws Exception {
        String html = home();
        String script = tagWith(html, "bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js");
        assertTrue(script.startsWith("<script"), "add Bootstrap 5.3.3's JS bundle");
        assertTrue(script.contains("integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\""), script);
        assertTrue(html.indexOf(script) > html.indexOf("</main>"), "the script goes at the end of the body");
    }

    @Test
    void theCoopsStylesheetComesAfterBootstrapSoItWins() throws Exception {
        String html = home();
        assertTrue(html.indexOf("/css/mavuno.css") > html.indexOf("bootstrap.min.css"));
    }

    @Test
    void theNavbarFoldsIntoAButtonOnPhones() throws Exception {
        String html = home();
        assertTrue(tagWith(html, "navbar-expand-md").startsWith("<nav"), "a <nav class=\"navbar navbar-expand-md ...\">");
        String button = tagWith(html, "navbar-toggler");
        assertTrue(button.startsWith("<button"), "a navbar-toggler button");
        Matcher target = Pattern.compile("data-bs-target=\"#([\\w-]+)\"").matcher(button);
        assertTrue(target.find(), "the button names what it opens with data-bs-target");
        String id = target.group(1);
        assertTrue(button.contains("aria-controls=\"" + id + "\""), "aria-controls names the same element");
        assertTrue(button.contains("aria-label="), "the icon-only button needs an aria-label");
        assertTrue(tagWith(html, "id=\"" + id + "\"").contains("collapse navbar-collapse"));
    }

    @Test
    void theMenuHasTheThreeLinks() throws Exception {
        String html = home();
        for (String href : new String[] {"/prices", "/collection", "/join"}) {
            assertTrue(tagWith(html, "href=\"" + href + "\"").contains("nav-link"), href);
        }
    }

    @Test
    void threeCardsShareARowOnALaptopAndStackOnAPhone() throws Exception {
        String html = home();
        assertTrue(html.contains("class=\"row"), "the cards sit in a .row");
        assertEquals(3, count(html, "class=\"col-12 col-md-4\""), "three columns: full width, a third from md up");
        assertEquals(3, count(html, "class=\"card[ \"]"));
        assertTrue(html.contains("Fair prices") && html.contains("Collection") && html.contains("Paid by M-Pesa"));
    }
}
