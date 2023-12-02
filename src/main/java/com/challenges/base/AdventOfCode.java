package com.challenges.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


public abstract class AdventOfCode<O> {
    private final int year;
    private final int day;

    protected AdventOfCode(int year, int day) {
        this.year = year;
        this.day = day;
    }

    protected abstract O part1(String input);
    protected abstract O part2(String input);

    protected String input(Character prefix) {
        var stream = getClass().getResourceAsStream("/aoc/%s/%s%s.input".formatted(year, day, (prefix == null ? "" : "."+prefix)));
        return new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))
            .lines()
            .collect(Collectors.joining("\n"));
    }

    protected String input() {
        return input(null);
    }

    public void run() {
        System.out.println("Part 1 result => " + part1(input()));
        System.out.println("Part 2 result => " + part2(input()));
    }
}