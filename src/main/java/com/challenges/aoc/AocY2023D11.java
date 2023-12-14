package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import com.challenges.base.RunTimeTracker;

/**
 * <a href="https://adventofcode.com/2023/day/1">Link to challenge</a>
 */
public class AocY2023D11 extends AdventOfCode<Long> {
    public AocY2023D11() {
        super(2023, 11);
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