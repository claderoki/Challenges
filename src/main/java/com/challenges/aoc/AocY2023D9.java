package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import com.challenges.base.RunTimeTracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2023/day/9">Link to challenge</a>
 */
public class AocY2023D9 extends AdventOfCode<Long> {
    public AocY2023D9() {
        super(2023, 9);
    }

    private Long predictNextInSequence(List<Long> values) {
        boolean identical = true;
        List<Long> differences = new ArrayList<>();
        for(int i = 1; i < values.size(); i++) {
            long current = values.get(i);
            long previous = values.get(i-1);
            if (current != previous) {
                identical = false;
            }
            differences.add(current - previous);
        }

        long last = values.get(values.size()-1);
        if (identical) {
            return last;
        } else {
            return last + predictNextInSequence(differences);
        }
    }

    private Long predictPreviousInSequence(List<Long> values) {
        boolean identical = true;
        List<Long> differences = new ArrayList<>();
        for(int i = values.size()-2; i >= 0; i--) {
            long current = values.get(i);
            long previous = values.get(i+1);
            if (current != previous) {
                identical = false;
            }
            differences.add(0, previous - current);
        }

        long last = values.get(0);
        if (identical) {
            return last;
        } else {
            return last - predictPreviousInSequence(differences);
        }
    }

    private List<Long> parse(String line) {
        return Arrays.stream(line.split(" "))
            .map(Long::parseLong)
            .toList();
    }

    public Long part1(String input) {
        return input
            .lines()
            .map(this::parse)
            .mapToLong(this::predictNextInSequence)
            .sum();
    }

    public Long part2(String input) {
        return input
            .lines()
            .map(this::parse)
            .mapToLong(this::predictPreviousInSequence)
            .sum();
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