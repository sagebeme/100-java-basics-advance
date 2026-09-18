package com.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;

    @Test
    void rendersABadgeForEveryPostsCategory() throws Exception {
        when(postService.getPage(1, 3)).thenReturn(List.of(new Post(1L, "Learn Java", "Java")));
        when(postService.totalPages(3)).thenReturn(1);

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("badge bg-secondary")))
                .andExpect(content().string(containsString(">Java<")));
    }

    @Test
    void showsPreviousAsDisabledOnTheFirstPage() throws Exception {
        when(postService.getPage(1, 3)).thenReturn(List.of());
        when(postService.totalPages(3)).thenReturn(3);

        String body = mockMvc.perform(get("/posts"))
                .andReturn().getResponse().getContentAsString();

        org.junit.jupiter.api.Assertions.assertTrue(liContaining(body, "Previous").contains("disabled"));
    }

    @Test
    void showsNextAsDisabledOnTheLastPage() throws Exception {
        when(postService.getPage(3, 3)).thenReturn(List.of());
        when(postService.totalPages(3)).thenReturn(3);

        String body = mockMvc.perform(get("/posts").param("page", "3"))
                .andReturn().getResponse().getContentAsString();

        org.junit.jupiter.api.Assertions.assertTrue(liContaining(body, "Next").contains("disabled"));
    }

    /**
     * Returns the nearest enclosing {@code <li ...>...text...</li>} element around the given text,
     * so the test can check the li's own classes rather than guessing at a fixed offset.
     */
    private static String liContaining(String html, String text) {
        int textIndex = html.indexOf(text);
        int liStart = html.lastIndexOf("<li", textIndex);
        int liEnd = html.indexOf("</li>", textIndex);
        return html.substring(liStart, liEnd);
    }

    @Test
    void everyPostHasItsOwnDeleteConfirmationModal() throws Exception {
        when(postService.getPage(1, 3)).thenReturn(List.of(new Post(5L, "Deploy to the Cloud", "DevOps")));
        when(postService.totalPages(3)).thenReturn(1);

        mockMvc.perform(get("/posts"))
                .andExpect(content().string(containsString("data-bs-target=\"#deleteModal-5\"")))
                .andExpect(content().string(containsString("id=\"deleteModal-5\"")))
                .andExpect(content().string(containsString("/posts/5/delete")));
    }

    @Test
    void deletingAKnownPostRedirectsWithASuccessMessage() throws Exception {
        when(postService.deletePost(5L)).thenReturn(true);

        mockMvc.perform(post("/posts/5/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts"))
                .andExpect(flash().attribute("message", "Post deleted"));

        verify(postService).deletePost(5L);
    }

    @Test
    void deletingAnUnknownPostStillRedirectsButWithADifferentMessage() throws Exception {
        when(postService.deletePost(999L)).thenReturn(false);

        mockMvc.perform(post("/posts/999/delete"))
                .andExpect(flash().attribute("message", "Post not found"));
    }

    @Test
    void theAlertOnlyAppearsWhenThereIsAFlashMessage() throws Exception {
        when(postService.getPage(1, 3)).thenReturn(List.of());
        when(postService.totalPages(3)).thenReturn(1);

        mockMvc.perform(get("/posts"))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("alert-success"))));
    }
}
