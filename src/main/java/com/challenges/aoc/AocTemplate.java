package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import com.challenges.base.RunTimeTracker;

/**
 * <a href="https://adventofcode.com/2023/day/1">Link to challenge</a>
 */
public class AocTemplate extends AdventOfCode<Long> {
    public AocTemplate() {
        super(2000, 1);
    }

    public Long part1(String input) {
        return 0L;
    }

    public Long part2(String input) {
        return 0L;
    }

    public void run() {
        String input = input();
        try (var ignore = new RunTimeTracker()) {
            System.out.println("Part 1 result => " + part1(input));
        }
        try (var ignore = new RunTimeTracker()) {
            System.out.println("Part 2 result => " + part2(input));
        }
    }
}