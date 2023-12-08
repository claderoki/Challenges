package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import java.util.Map;
import java.util.Optional;

/**
 * <a href="https://adventofcode.com/2023/day/1">Link to challenge</a>
 */
public class AocY2023D1 extends AdventOfCode<Integer> {
    Map<String, Character> mapped = Map.of(
        "one", '1',
        "two", '2',
        "three", '3',
        "four", '4',
        "five", '5',
        "six", '6',
        "seven", '7',
        "eight", '8',
        "nine", '9'
    );
    private static final int largestPossible = 5;

    protected AocY2023D1() {
        super(2023, 1);
    }

    private Optional<Character> findDigit(String input, int start, boolean forwards, boolean includeSpelledOut) {
        char initial = input.charAt(start);
        if (Character.isDigit(initial)) {
            return Optional.of(initial);
        } else if (!includeSpelledOut) {
            return Optional.empty();
        }

        StringBuilder digits = new StringBuilder();

        int j = 0;
        int i = start;
        while (j <= largestPossible || (i >= 0 && i < input.length()-1)) {
            char character = input.charAt(i);
            if (Character.isDigit(character)) {
                return Optional.empty();
            }
            if (forwards) {
                digits.append(character);
            } else {
                digits.insert(0, character);
            }

            var map = mapped.get(digits.toString());
            if (map != null) {
                return Optional.of(map);
            }

            i += (forwards ? 1 : -1);
            j++;
        }

        return Optional.empty();
    }

    private Integer findCalibrationValue(String input, boolean includeSpelledOut) {
        StringBuilder digits = new StringBuilder();
        int i = 0;
        int increment = 1;
        while (digits.length() < 2) {
            var spelled = findDigit(input, i, increment == 1, includeSpelledOut);
            if (spelled.isPresent()) {
                digits.append(spelled.get());
                increment = -1;
                i = input.length()-1;
            } else {
                i += increment;
            }
        }
        System.out.println(input + " == " + digits);
        return Integer.parseInt(digits.toString());
    }

    @Override
    protected Integer part1(String input) {
        return input
            .lines()
            .mapToInt(c -> findCalibrationValue(c, false))
            .sum();
    }

    @Override
    protected Integer part2(String input) {
        return input
            .lines()
            .mapToInt(c -> findCalibrationValue(c, true))
            .sum();
    }
}
