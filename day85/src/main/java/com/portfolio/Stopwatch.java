package com.portfolio;

public class Stopwatch {

    private long startNanos = -1;
    private long stopNanos = -1;

    public void start() {
        startNanos = System.nanoTime();
        stopNanos = -1;
    }

    public void stop() {
        if (startNanos == -1) {
            throw new IllegalStateException("Stopwatch was never started");
        }
        stopNanos = System.nanoTime();
    }

    public long elapsedMillis() {
        if (startNanos == -1) {
            throw new IllegalStateException("Stopwatch was never started");
        }
        long endNanos = (stopNanos == -1) ? System.nanoTime() : stopNanos;
        return (endNanos - startNanos) / 1_000_000;
    }
}
