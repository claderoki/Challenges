package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import com.challenges.base.RunTimeTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2023/day/7">Link to challenge</a>
 */
public class AocY2023D7 extends AdventOfCode<Long> {
    public AocY2023D7() {
        super(2023, 7);
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