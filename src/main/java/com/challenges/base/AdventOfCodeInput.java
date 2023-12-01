package com.challenges.base;

import java.io.InputStream;

public class AdventOfCodeInput implements AutoCloseable {
    private final int year;
    private final int day;
    private final InputStream stream;

    public AdventOfCodeInput(int year, int day) {
        this.year = year;
        this.day = day;
        stream = getClass().getResourceAsStream("aoc/" + year + "/" + day + ".input");
    }

    @Override
    public void close() throws Exception {
        stream.close();
    }
}
