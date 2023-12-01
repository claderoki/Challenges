package com.challenges.aoc;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.List;
import java.util.Map;

/** Completed
 * https://adventofcode.com/2023/day/1
 */
public class AocY2023D1 extends CodeChallenge<String, Integer> {
    private static Map<String, String> mapping = Map.of(
        "one", "1",
        "two", "2",
        "three", "3",
        "four", "4",
        "five", "5",
        "six", "6",
        "seven", "7",
        "eight", "8",
        "nine", "9"
    );

    private Integer findSpelledOut(String input, int start, int increment) {
        int end = start;
        while (true) {

        }
    }

    private Integer firstAndLastDigits(String input) {
        StringBuilder digits = new StringBuilder();
        int i = 0;
        int increment = 1;
        while (digits.length() < 2) {
            char character = input.charAt(i);
            if (Character.isDigit(character)) {
                digits.append(character);
                increment = -1;
                i = input.length()-1;
            } else {
                i += increment;
            }
        }
        System.out.println(digits);
        return Integer.parseInt(digits.toString());
    }

    @Override
    protected Integer test(String input) {
        return input
            .lines()
            .map(c -> {
                for(var entry: mapping.entrySet()) {
                    c = c.replace(entry.getKey(), entry.getValue());
                }
                return c;
            })
            .mapToInt(this::firstAndLastDigits)
            .sum();
    }

    @Override
    protected List<InputOutput<String, Integer>> getInputAndDesiredOutputs() {
        return List.of(
            io(aoc(2023, 1, 'e'), 281)
        );
    }
}
