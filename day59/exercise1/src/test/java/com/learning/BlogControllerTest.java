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
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String page(String url) throws Exception {
        return mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void theListShowsExcerptsThatLinkToEachStory() throws Exception {
        String html = page("/posts");
        assertTrue(html.contains("href=\"/posts/1\""));
        assertTrue(html.contains("href=\"/posts/4\""));
        assertTrue(html.contains("…"), "long stories are cut short on the list");
    }

    @Test
    void aStoryHasItsTitleAndByline() throws Exception {
        String html = page("/posts/1");
        assertTrue(html.contains("<title>Kayole&#39;s new footbridge opens · Mtaa News</title>"), html);
        assertTrue(html.contains("Kayole&#39;s new footbridge opens</h1>"));
        assertTrue(html.contains("By Wanjiku Mwangi · 3 November 2026"));
    }

    @Test
    void eachParagraphIsItsOwnParagraph() throws Exception {
        String html = page("/posts/2");
        Matcher m = Pattern.compile("<p>([^<]*)</p>").matcher(html);
        int paragraphs = 0;
        while (m.find()) {
            paragraphs++;
        }
        assertEquals(2, paragraphs, "story 2 has two paragraphs, each in a plain <p>");
        assertTrue(html.contains("<p>Phase 3 gets Tuesday, Thursday and Sunday.</p>"));
    }

    @Test
    void aStoryLinksBackToTheList() throws Exception {
        assertTrue(page("/posts/3").contains("<a href=\"/posts\">← All stories</a>"));
    }

    @Test
    void anUnknownStoryIs404() throws Exception {
        mockMvc.perform(get("/posts/99")).andExpect(status().isNotFound());
    }

    @Test
    void anIdThatIsntANumberIs400() throws Exception {
        mockMvc.perform(get("/posts/footbridge")).andExpect(status().isBadRequest());
    }
}
