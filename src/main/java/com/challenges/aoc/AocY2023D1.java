package com.challenges.aoc;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.List;
import java.util.Map;

/** Completed
 * https://adventofcode.com/2023/day/1
 */
public class AocY2023D1 extends CodeChallenge<String, Integer> {
    private static Map<String, Character> mapping = Map.of(
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

    private char findSpelledOut(String input, int start, int increment) {
        int end = start;

        // until a digit is hit or 5 characters are passed.
        while (!mapping.containsKey(input.substring(start, end)) && start-end > 5) {

        }

        return mapping.get(input.substring(start, end));
    }

    private Integer firstAndLastDigits(String input) {
        StringBuilder digits = new StringBuilder();
        int i = 0;
        int increment = 1;
        while (digits.length() < 2) {
            char character = input.charAt(i);
            if (Character.isDigit(character)) {

            } else {

            }


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
