package com.challenges.base;

public class RunTimeTracker implements AutoCloseable {
    private final long start;

    public RunTimeTracker() {
        this.start = System.nanoTime();
    }

    @Override
    public void close() {
        System.out.printf("Total time elapsed (ms): %s%n", ((System.nanoTime() - start) / 1000_000));
    }
}
