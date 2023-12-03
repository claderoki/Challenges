package com.challenges.aoc;

import com.challenges.base.AdventOfCode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Completed
 * <a href="https://adventofcode.com/2023/day/1">...</a>
 */
public class AocY2023D3 extends AdventOfCode<Integer> {
    public AocY2023D3() {
        super(2023, 3);
    }

    record Digit(int line, int index, Integer value) {}

    private Optional<Digit> findDigits(List<String> lines, int lineIndex, int center) {
        StringBuilder digits = new StringBuilder();
        if (lineIndex < 0 || lineIndex >= lines.size()) {
            return Optional.empty();
        }
        String line = lines.get(lineIndex);
        if (center > 0 && center < line.length() && !Character.isDigit(line.charAt(center))) {
            return Optional.empty();
        }
        center = Math.min(Math.max(center, 0), line.length()-1);

        int start = center;
        for(int i = center; i <= line.length()-1; i++) {
            char character = line.charAt(i);
            if (Character.isDigit(character)) {
                digits.append(character);
            } else {
                break;
            }
        }
        for(int i = center-1; i >= 0; i--) {
            char character = line.charAt(i);
            if (Character.isDigit(character)) {
                digits.insert(0, character);
                start = i;
            } else {
                break;
            }
        }

        if (digits.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new Digit(lineIndex, start, Integer.parseInt(digits.toString())));
    }

    public Integer part1(String input) {
        List<Digit> partNumbers = new ArrayList<>();
        List<String> lines = input.lines().toList();
        for (int j = 0; j < lines.size(); j++) {
            System.out.println("Line " + (j+1));
            final String line = lines.get(j);
            for (int i = 0; i < line.length(); i++) {
                char character = line.charAt(i);
                boolean isSymbol = character != '.' && character != '\n' && !Character.isDigit(character);
                if (isSymbol) {
                    int LINE = j;
                    int DOWN = LINE+1;
                    int UP = LINE-1;
                    int HERE = i;
                    int RIGHT = HERE+1;
                    int LEFT = HERE-1;

                    var found = Stream.of(
                            findDigits(lines, UP, HERE),
                            findDigits(lines, UP, LEFT),
                            findDigits(lines, UP, RIGHT),
                            findDigits(lines, DOWN, HERE),
                            findDigits(lines, DOWN, LEFT),
                            findDigits(lines, DOWN, RIGHT),
                            findDigits(lines, LINE, LEFT),
                            findDigits(lines, LINE, RIGHT)
                        )
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toSet());

                    var readable = found.stream().map(c -> c.value.toString()).collect(Collectors.joining(", "));
                    System.out.printf("numbers near %s found: %s%n", character, readable);

                    partNumbers.addAll(found);
                }
            }
        }
        return partNumbers.stream().mapToInt(c -> c.value).sum();
    }

    public Integer part2(String input) {
        long total = 0;
        List<String> lines = input.lines().toList();
        for (int j = 0; j < lines.size(); j++) {
            final String line = lines.get(j);
            for (int i = 0; i < line.length(); i++) {
                char character = line.charAt(i);
                boolean isSymbol = character != '.' && character != '\n' && !Character.isDigit(character);
                if (isSymbol) {
                    int LINE = j;
                    int DOWN = LINE+1;
                    int UP = LINE-1;
                    int HERE = i;
                    int RIGHT = HERE+1;
                    int LEFT = HERE-1;

                    var found = Stream.of(
                            findDigits(lines, UP, HERE),
                            findDigits(lines, UP, LEFT),
                            findDigits(lines, UP, RIGHT),
                            findDigits(lines, DOWN, HERE),
                            findDigits(lines, DOWN, LEFT),
                            findDigits(lines, DOWN, RIGHT),
                            findDigits(lines, LINE, LEFT),
                            findDigits(lines, LINE, RIGHT)
                        )
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .distinct()
                        .map(Digit::value)
                        .toList();

                    if (character == '*') {
                        if (found.size() == 2) {
                            total += ((long) found.get(0) * found.get(1));
                            System.out.printf("numbers near %s found: %s * %s%n", character, found.get(0), found.get(1));
                        }
                    }
                }
            }
        }
        return Math.toIntExact(total);
    }

    public void run() {
        // 533784 == correct
        System.out.println("Part 1 result => " + part1(input()));
//        78826761 == correct
        System.out.println("Part 2 result => " + part2(input()));
    }
}
