package com.challenges.base;

public class ProgressBar {
    private final long total;
    private long current;
    private long previousPercentage;
    private long percentage;

    public ProgressBar(long total) {
        this.total = total;
    }

    private long calculatePercentage() {
        return (int)(current * 100 / total);
    }

    public void setCurrent(long current) {
        this.current = current;
        previousPercentage = percentage;
        percentage = calculatePercentage();
        if (percentage > previousPercentage) {
            System.out.println(percentage + "% complete.");
        }
    }
}
