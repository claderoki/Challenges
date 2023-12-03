package com.challenges.aoc;

import com.challenges.base.AdventOfCode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/** Completed
 * <a href="https://adventofcode.com/2023/day/1">...</a>
 */
public class AocY2023D3 extends AdventOfCode<Integer> {
    public AocY2023D3() {
        super(2023, 3);
    }

    private final List<Character> symbols = List.of(
        '*',
        '#',
        '+',
        '$'
    );

    record Digit(int line, int index, Integer value) {}
    record Indexes(int start, int end) {}

    private Optional<Indexes> findDigits(String input, int center) {
        boolean any = false;
        int end = center;
        int current = input.charAt(end);
        while (end < input.length()-1 && Character.isDigit(current)) {
            any = true;
            end++;
            current = input.charAt(end);
        }

        int start = center;
        current = input.charAt(start);
        while (start > 0 && Character.isDigit(current)) {
            any = true;
            start--;
            current = input.charAt(start);
        }
        if (!any) {
            return Optional.empty();
        }

        return Optional.of(new Indexes(start, end));
    }

    public Integer part1(String input) {
        Set<Digit> partNumbers = new HashSet<>();

        List<String> lines = input.lines().toList();
        for (int j = 0; j < lines.size(); j++) {
            final String line = lines.get(j);
            for (int i = 0; i < line.length(); i++) {
                char character = line.charAt(i);
                if (symbols.contains(character)) {
//                    Function<> seek = () -> {
//                        return null;
//                    };
//
//                    var a = Stream.of(
//                    );


//                    int sameIndex = j * lineHeight + current;
//                    int topIndex = (j-1) * lineHeight + current;
//                    int bottomIndex = (j+1) * lineHeight + current;

//                System.out.println("[%s][%s][%s]".formatted(topLeft, top, topRight));
//                System.out.println("[%s][%s][%s]".formatted(left, character, right));
//                System.out.println("[%s][%s][%s]".formatted(bottomLeft, bottom, bottomRight));

//                    var top = findDigits(lines.get(i-1), topIndex);
//                    var topLeft = findDigits(lines.get(i-1), topIndex - 1);
//                    var topRight = findDigits(line, topIndex + 1);
//                    var bottom = findDigits(line, bottomIndex);
//                    var bottomLeft = findDigits(line, bottomIndex - 1);
//                    var bottomRight = findDigits(line, bottomIndex + 1);
//                    var left = findDigits(line, sameIndex - 1);
//                    var right = findDigits(line, sameIndex + 1);

//                    partNumbers.addAll(Stream.of(top, topLeft, topRight, bottom, bottomLeft, bottomRight, left, right)
//                        .filter(Optional::isPresent)
//                        .map(Optional::get)
//                        .toList());
                }
            }
        }


        Integer lineHeight = null;
        int j = 0;
        for (int i = 0; i < input.length(); i++) {
            int current = i-(j*(lineHeight != null ? lineHeight : 0));
            char character = input.charAt(i);
            if (character == '\n') {
                if (lineHeight == null) {
                    lineHeight = i+1;
                }
                j++;
            } else if (symbols.contains(character)) {
                int sameIndex = j * lineHeight + current;
                int topIndex = (j-1) * lineHeight + current;
                int bottomIndex = (j+1) * lineHeight + current;

//                char top = input.charAt(topIndex);
//                char topLeft = input.charAt(topIndex - 1);
//                char topRight = input.charAt(topIndex + 1);
//                char bottom = input.charAt(bottomIndex);
//                char bottomLeft = input.charAt(bottomIndex - 1);
//                char bottomRight = input.charAt(bottomIndex + 1);
//                char left = input.charAt(sameIndex - 1);
//                char right = input.charAt(sameIndex + 1);

//                System.out.println("[%s][%s][%s]".formatted(topLeft, top, topRight));
//                System.out.println("[%s][%s][%s]".formatted(left, character, right));
//                System.out.println("[%s][%s][%s]".formatted(bottomLeft, bottom, bottomRight));

                var top = findDigits(input, lineHeight, topIndex);
                var topLeft = findDigits(input, lineHeight, topIndex - 1);
                var topRight = findDigits(input, lineHeight, topIndex + 1);
                var bottom = findDigits(input, lineHeight, bottomIndex);
                var bottomLeft = findDigits(input, lineHeight, bottomIndex - 1);
                var bottomRight = findDigits(input, lineHeight, bottomIndex + 1);
                var left = findDigits(input, lineHeight, sameIndex - 1);
                var right = findDigits(input, lineHeight, sameIndex + 1);

                partNumbers.addAll(Stream.of(top, topLeft, topRight, bottom, bottomLeft, bottomRight, left, right)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList());
            }
        }

        return partNumbers.stream().mapToInt(c -> c.value).sum();
    }



    public Integer part2(String input) {
        return null;
    }

    public void run() {
        System.out.println("Part 1 result => " + part1(input('e')));
//        System.out.println("Part 2 result => " + part2(input()));
    }
}
