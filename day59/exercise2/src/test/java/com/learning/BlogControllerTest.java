package com.learning;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// PostService is replaced by a Mockito stand-in, so each test decides what the "database" holds.
@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;

    private String page(int page, int totalPages) throws Exception {
        when(postService.totalPages(3)).thenReturn(totalPages);
        when(postService.getPage(page, 3)).thenReturn(List.of(new Post(page, "Story on page " + page, "News")));
        return mockMvc.perform(get("/posts").param("page", String.valueOf(page)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    // The <li> around the first link with this text, so its own classes can be checked.
    private static String itemAround(String html, String linkText) {
        int text = html.indexOf(">" + linkText + "</a>");
        assertTrue(text >= 0, "no link saying " + linkText);
        return html.substring(html.lastIndexOf("<li", text), html.indexOf("</li>", text));
    }

    @Test
    void showsTheRequestedPage() throws Exception {
        assertTrue(page(2, 3).contains("Story on page 2"));
    }

    @Test
    void hasALinkForEveryPage() throws Exception {
        String html = page(1, 3);
        assertTrue(html.contains("<nav aria-label=\"Story pages\">") || html.contains("class=\"pagination\""));
        assertTrue(html.contains("href=\"/posts?page=1\""));
        assertTrue(html.contains("href=\"/posts?page=2\""));
        assertTrue(html.contains("href=\"/posts?page=3\""));
    }

    @Test
    void previousIsDisabledOnTheFirstPageOnly() throws Exception {
        assertTrue(itemAround(page(1, 3), "Previous").contains("disabled"));
        assertFalse(itemAround(page(2, 3), "Previous").contains("disabled"));
    }

    @Test
    void nextIsDisabledOnTheLastPageOnly() throws Exception {
        assertTrue(itemAround(page(3, 3), "Next").contains("disabled"));
        assertFalse(itemAround(page(2, 3), "Next").contains("disabled"));
    }

    @Test
    void theCurrentPageIsActiveAndAnnounced() throws Exception {
        String item = itemAround(page(2, 3), "2");
        assertTrue(item.contains("active"), item);
        assertTrue(item.contains("aria-current=\"page\""), item);
        assertFalse(itemAround(page(2, 3), "1").contains("active"));
    }

    @Test
    void pagesOutsideTheRangeRedirect() throws Exception {
        when(postService.totalPages(3)).thenReturn(3);
        mockMvc.perform(get("/posts").param("page", "0")).andExpect(redirectedUrl("/posts"));
        mockMvc.perform(get("/posts").param("page", "9")).andExpect(redirectedUrl("/posts?page=3"));
    }

    @Test
    void deletingRedirectsWithAMessage() throws Exception {
        when(postService.delete(5)).thenReturn(true);
        mockMvc.perform(post("/posts/5/delete"))
                .andExpect(redirectedUrl("/posts"))
                .andExpect(flash().attribute("message", "Story deleted."));
        verify(postService).delete(5);
    }

    @Test
    void deletingTwiceSaysItWasAlreadyGone() throws Exception {
        when(postService.delete(5)).thenReturn(false);
        mockMvc.perform(post("/posts/5/delete"))
                .andExpect(flash().attribute("message", "That story was already gone."));
    }

    @Test
    void aGetCanNeverDelete() throws Exception {
        mockMvc.perform(get("/posts/5/delete")).andExpect(status().isMethodNotAllowed());
    }
}
