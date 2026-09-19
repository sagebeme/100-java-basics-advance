package com.portfolio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class DisappearingTextEditorTest {

    /**
     * A Clock whose instant() can be advanced on demand, so idle behavior can be tested without
     * real sleeps.
     */
    static class MutableClock extends Clock {
        private Instant now;

        MutableClock(Instant start) {
            this.now = start;
        }

        void advance(Duration duration) {
            now = now.plus(duration);
        }

        @Override
        public ZoneId getZone() {
            return ZoneOffset.UTC;
        }

        @Override
        public Clock withZone(ZoneId zone) {
            return this;
        }

        @Override
        public Instant instant() {
            return now;
        }
    }

    private MutableClock clock;
    private DisappearingTextEditor editor;
    private static final Duration TIMEOUT = Duration.ofSeconds(5);

    @BeforeEach
    void setUp() {
        clock = new MutableClock(Instant.parse("2026-01-01T00:00:00Z"));
        editor = new DisappearingTextEditor(clock, TIMEOUT);
    }

    @Test
    void startsEmptyAndNotIdle() {
        assertEquals("", editor.getContent());
        assertFalse(editor.isIdle());
    }

    @Test
    void settingContentUpdatesItImmediately() {
        editor.setContent("Hello");
        assertEquals("Hello", editor.getContent());
    }

    @Test
    void isNotIdleBeforeTheTimeoutElapses() {
        editor.setContent("still typing");
        clock.advance(Duration.ofSeconds(4));

        assertFalse(editor.isIdle());
    }

    @Test
    void becomesIdleOnceTheTimeoutElapses() {
        editor.setContent("stopped typing");
        clock.advance(Duration.ofSeconds(5));

        assertTrue(editor.isIdle());
    }

    @Test
    void clearIfIdleWipesTheContentOnceIdle() {
        editor.setContent("this will vanish");
        clock.advance(Duration.ofSeconds(6));

        boolean disappeared = editor.clearIfIdle();

        assertTrue(disappeared);
        assertEquals("", editor.getContent());
    }

    @Test
    void clearIfIdleDoesNothingWhileStillActive() {
        editor.setContent("still here");
        clock.advance(Duration.ofSeconds(2));

        boolean disappeared = editor.clearIfIdle();

        assertFalse(disappeared);
        assertEquals("still here", editor.getContent());
    }

    @Test
    void typingAgainResetsTheIdleClock() {
        editor.setContent("first");
        clock.advance(Duration.ofSeconds(4));
        editor.setContent("first second"); // typing again before timeout
        clock.advance(Duration.ofSeconds(4)); // total 8s since first keystroke, but only 4s since last

        assertFalse(editor.isIdle());
        assertEquals("first second", editor.getContent());
    }

    @Test
    void timeUntilDisappearsCountsDownAndNeverGoesNegative() {
        editor.setContent("counting down");
        assertEquals(TIMEOUT, editor.timeUntilDisappears());

        clock.advance(Duration.ofSeconds(3));
        assertEquals(Duration.ofSeconds(2), editor.timeUntilDisappears());

        clock.advance(Duration.ofSeconds(10));
        assertEquals(Duration.ZERO, editor.timeUntilDisappears());
    }

    @Test
    void savingStoresTheCurrentContentAndCountsAsActivity() {
        editor.setContent("important note");
        clock.advance(Duration.ofSeconds(4));

        SavedEntry entry = editor.save();

        assertEquals("important note", entry.content());
        assertEquals(1, editor.getSavedEntries().size());

        clock.advance(Duration.ofSeconds(4)); // only 4s since the save, not since the original typing
        assertFalse(editor.isIdle());
    }

    @Test
    void savingEmptyContentThrows() {
        assertThrows(IllegalStateException.class, () -> editor.save());
    }

    @Test
    void savedTextSurvivesEvenAfterTheLiveContentDisappears() {
        editor.setContent("keep this");
        editor.save();

        clock.advance(Duration.ofSeconds(20));
        editor.clearIfIdle();

        assertEquals("", editor.getContent());
        assertEquals(1, editor.getSavedEntries().size());
        assertEquals("keep this", editor.getSavedEntries().get(0).content());
    }

    @Test
    void clearWipesContentAndResetsTheIdleClock() {
        editor.setContent("draft");
        clock.advance(Duration.ofSeconds(4));

        editor.clear();

        assertEquals("", editor.getContent());
        assertFalse(editor.isIdle());
    }
}
