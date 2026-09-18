package com.learning;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PostServiceTest {

    @Test
    void sevenPostsAtThreePerPageMakesThreePages() {
        PostService service = new PostService();
        assertEquals(3, service.totalPages(3));
    }

    @Test
    void firstPageHasAFullPage() {
        PostService service = new PostService();
        List<Post> page = service.getPage(1, 3);
        assertEquals(3, page.size());
        assertEquals("Learn Java", page.get(0).getTitle());
    }

    @Test
    void lastPageHasTheRemainder() {
        PostService service = new PostService();
        List<Post> page = service.getPage(3, 3);
        assertEquals(1, page.size());
        assertEquals("REST Best Practices", page.get(0).getTitle());
    }

    @Test
    void aPageBeyondTheLastOneIsEmpty() {
        PostService service = new PostService();
        assertTrue(service.getPage(4, 3).isEmpty());
    }

    @Test
    void deletingARealPostRemovesItAndShrinksTheCount() {
        PostService service = new PostService();
        assertTrue(service.deletePost(1L));
        assertEquals(6, service.totalCount());
    }

    @Test
    void deletingAnUnknownPostReturnsFalse() {
        PostService service = new PostService();
        assertTrue(!service.deletePost(999L));
    }
}
