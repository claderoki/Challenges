package com.challenges.base;

public class RunTimeTracker implements AutoCloseable {
    private final long start;
    private final String identifier;

    public RunTimeTracker() {
        this.start = System.nanoTime();
        this.identifier = null;
    }

    public RunTimeTracker(String identifier) {
        this.start = System.nanoTime();
        this.identifier = identifier;
    }

    @Override
    public void close() {
        var ms = (System.nanoTime() - start) / 1000_000;
        System.out.printf("Total time elapsed (ms): %s%s%n", ms, identifier == null ? "" : (" for " + identifier));
    }
}