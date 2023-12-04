package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import com.challenges.base.RunTimeTracker;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/** Completed
 * <a href="https://adventofcode.com/2023/day/1">...</a>
 */
public class AocY2023D4 extends AdventOfCode<Integer> {
    public AocY2023D4() {
        super(2023, 4);
    }

    private int getMatches(String line) {
        int wins = 0;
        boolean passed = false;
        boolean winning = true;
        List<Integer> winningNumbers = new ArrayList<>();
        StringBuilder currentDigit = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            boolean digit = false;
            char character = line.charAt(i);
            if (character == ':') {
                passed = true;
            } else if (character == '|') {
                winning = false;
            } else if (passed && Character.isDigit(character)) {
                currentDigit.append(character);
                digit = true;
            }

            if ((!digit || i == line.length()-1) && !currentDigit.isEmpty()) {
                Integer number = Integer.parseInt(currentDigit.toString());
                currentDigit.delete(0, currentDigit.length());
                if (winning) {
                    winningNumbers.add(number);
                } else if (winningNumbers.contains(number)) {
                    wins++;
                }
            }
        }
        return wins;
    }

    public Integer part1(String input) {
        return input
            .lines()
            .mapToInt(this::getMatches)
            .map(c -> ((1 << c) / 2))
            .sum();
    }

    private int iterate(Map<Integer, Integer> cache, String[] lines, int start, int end) {
        int iterations = 0;
        for(int i = start; i < end; i++) {
            int match = cache.computeIfAbsent(i, k -> getMatches(lines[k]));
            if (match > 0) {
                iterations += iterate(cache, lines, i+1, i+1+match);
            }
            iterations++;
        }
        return iterations;
    }

    public Integer part2(String input) {
        String[] lines = input.split("\n");
        return iterate(new HashMap<>(), lines, 0, lines.length);
    }

    public void run() {
        // 26426 == correct
        System.out.println("Part 1 result => " + part1(input()));
        // 6227972 == correct
        try (var a = new RunTimeTracker()) {
            System.out.println("Part 2 result => " + part2(input()));
        }
    }
}
