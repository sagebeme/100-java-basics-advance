package com.portfolio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StopwatchTest {

    @Test
    void measuresRealElapsedTimeAcrossASleep() throws InterruptedException {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        Thread.sleep(100);
        stopwatch.stop();

        long elapsed = stopwatch.elapsedMillis();
        // Allow generous slack for scheduler jitter - this asserts real elapsed time was
        // measured, not that it's precisely 100ms.
        assertTrue(elapsed >= 80, "Expected at least ~100ms elapsed, was " + elapsed);
        assertTrue(elapsed < 5000, "Expected well under 5s elapsed, was " + elapsed);
    }

    @Test
    void elapsedMillisKeepsAdvancingBeforeStopIsCalled() throws InterruptedException {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        long firstReading = stopwatch.elapsedMillis();
        Thread.sleep(50);
        long secondReading = stopwatch.elapsedMillis();

        assertTrue(secondReading > firstReading);
    }

    @Test
    void callingStopBeforeStartThrows() {
        Stopwatch stopwatch = new Stopwatch();
        assertThrows(IllegalStateException.class, stopwatch::stop);
    }

    @Test
    void readingElapsedBeforeStartThrows() {
        Stopwatch stopwatch = new Stopwatch();
        assertThrows(IllegalStateException.class, stopwatch::elapsedMillis);
    }
}
