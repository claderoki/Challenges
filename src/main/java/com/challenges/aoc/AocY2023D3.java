package com.challenges.aoc;

import com.challenges.base.AdventOfCode;

import java.util.*;
import java.util.stream.Stream;

/** Completed
 * <a href="https://adventofcode.com/2023/day/1">...</a>
 */
public class AocY2023D3 extends AdventOfCode<Integer> {
    public AocY2023D3() {
        super(2023, 3);
    }

    record Digit(int line, int index, Integer value) {}

    record PartNumberGroup(Character symbol, List<Integer> numbers) {}

    private Optional<Digit> findDigits(String[] lines, int lineIndex, int center) {
        if (lineIndex < 0 || lineIndex >= lines.length || center < 0) {
            return Optional.empty();
        }

        String line = lines[lineIndex];
        if (center >= line.length() || !Character.isDigit(line.charAt(center))) {
            return Optional.empty();
        }

        int start = center;
        StringBuilder digits = new StringBuilder();
        for (int i = center; i < line.length() && Character.isDigit(line.charAt(i)); i++) {
            digits.append(line.charAt(i));
        }
        for (int i = center - 1; i >= 0 && Character.isDigit(line.charAt(i)); i--) {
            digits.insert(0, line.charAt(i));
            start = i;
        }

        if (digits.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new Digit(lineIndex, start, Integer.parseInt(digits.toString())));
    }

    private Stream<PartNumberGroup> streamGroups(String input) {
        List<PartNumberGroup> groups = new ArrayList<>();
        String[] lines = input.split("\n");
        for (int j = 0; j < lines.length; j++) {
            final String line = lines[j];
            for (int i = 0; i < line.length(); i++) {
                char character = line.charAt(i);
                if (character != '.' && !Character.isDigit(character)) {
                    int LINE = j;
                    int DOWN = LINE+1;
                    int UP = LINE-1;
                    int HERE = i;
                    int RIGHT = HERE+1;
                    int LEFT = HERE-1;

                    List<Integer> found = Stream.of(
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
                    groups.add(new PartNumberGroup(character, found));
                }
            }
        }
        return groups.stream();
    }

    public Integer part1(String input) {
        return streamGroups(input)
            .flatMap(c -> c.numbers.stream())
            .mapToInt(c -> c)
            .sum();
    }

    public Integer part2(String input) {
        return streamGroups(input)
            .filter(c -> c.symbol() == '*' && c.numbers.size() == 2)
            .peek((c) -> System.out.printf("numbers near %s found: %s * %s%n", c.symbol, c.numbers.get(0), c.numbers().get(1)))
            .mapToInt(c -> c.numbers().get(0) * c.numbers().get(1))
            .sum();
    }

    public void run() {
        // 533784 == correct
        System.out.println("Part 1 result => " + part1(input()));
//        78826761 == correct
        System.out.println("Part 2 result => " + part2(input()));
    }
}
