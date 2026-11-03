package com.learning;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PostServiceTest {

    private final PostService service = new PostService();

    private static List<Long> ids(List<Post> posts) {
        return posts.stream().map(Post::getId).toList();
    }

    @Test
    void sevenStoriesAtThreeAPageMakeThreePages() {
        assertEquals(3, service.totalPages(3));
        assertEquals(1, service.totalPages(10));
        assertEquals(7, service.totalPages(1));
    }

    @Test
    void pagesComeInOrder() {
        assertEquals(List.of(1L, 2L, 3L), ids(service.getPage(1, 3)));
        assertEquals(List.of(4L, 5L, 6L), ids(service.getPage(2, 3)));
        assertEquals(List.of(7L), ids(service.getPage(3, 3)));
    }

    @Test
    void aPagePastTheEndIsEmptyAndPageZeroIsAMistake() {
        assertTrue(service.getPage(4, 3).isEmpty());
        assertThrows(IllegalArgumentException.class, () -> service.getPage(0, 3));
    }

    @Test
    void deletingShiftsTheLaterStoriesForward() {
        assertTrue(service.delete(2));
        assertEquals(6, service.count());
        assertEquals(List.of(1L, 3L, 4L), ids(service.getPage(1, 3)));
        assertEquals(2, service.totalPages(3));
    }

    @Test
    void deletingAStoryThatIsntThereSaysSo() {
        assertFalse(service.delete(99));
        assertEquals(7, service.count());
    }

    @Test
    void anEmptySiteStillHasOnePage() {
        for (long id = 1; id <= 7; id++) {
            service.delete(id);
        }
        assertEquals(1, service.totalPages(3));
        assertTrue(service.getPage(1, 3).isEmpty());
    }
}
