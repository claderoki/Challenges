package com.challenges.aoc;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Completed
 * <a href="https://adventofcode.com/2023/day/1">...</a>
 */
public class AocY2023D1 extends CodeChallenge<String, Integer> {
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

    private Optional<Character> findDigit(String input, int start, boolean forwards) {
        char initial = input.charAt(start);
        if (Character.isDigit(initial)) {
            return Optional.of(initial);
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

    private Integer findCalibrationValue(String input) {
        StringBuilder digits = new StringBuilder();
        int i = 0;
        int increment = 1;
        while (digits.length() < 2) {
            var spelled = findDigit(input, i, increment == 1);
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

    public Integer test(String input) {
        return input
            .lines()
            .mapToInt(this::findCalibrationValue)
            .sum();
    }

    @Override
    protected List<InputOutput<String, Integer>> getInputAndDesiredOutputs() {
        return List.of(
            io(aoc(2023, 1, 'e'), 281)
//            io(aoc(2023, 1), 54100) // note: 54109 is not correct
        );
    }
}
