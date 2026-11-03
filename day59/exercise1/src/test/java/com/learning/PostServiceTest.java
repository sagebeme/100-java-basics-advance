package com.learning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PostServiceTest {

    private final PostService service = new PostService();

    @Test
    void findsAStoryById() {
        assertEquals("Water rationing: the new timetable", service.find(2).orElseThrow().getTitle());
        assertTrue(service.find(99).isEmpty());
    }

    @Test
    void shortTextIsLeftAlone() {
        assertEquals("Entry is free.", PostService.excerpt("Entry is free.", 120));
        assertEquals("Exactly ten", PostService.excerpt("Exactly ten", 11));
    }

    @Test
    void longTextIsCutBetweenWordsWithAnEllipsis() {
        assertEquals("one two…", PostService.excerpt("one two three four", 10));
    }

    @Test
    void punctuationBeforeTheCutIsDropped() {
        assertEquals("Hello…", PostService.excerpt("Hello, world and more", 8));
    }

    @Test
    void newLinesAndRunsOfSpacesBecomeOneSpace() {
        assertEquals("first paragraph second", PostService.excerpt("first   paragraph\n\nsecond", 50));
    }

    @Test
    void oneLongWordIsCutWhereItMustBe() {
        assertEquals("abcd…", PostService.excerpt("abcdefghij", 5));
    }

    @Test
    void anExcerptIsNeverLongerThanAsked() {
        for (Post post : service.all()) {
            for (int max = 5; max < 200; max += 7) {
                String excerpt = PostService.excerpt(post.getBody(), max);
                assertTrue(excerpt.length() <= max, max + ": " + excerpt);
            }
        }
    }
}
