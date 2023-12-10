package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import com.challenges.base.RunTimeTracker;

/**
 * <a href="https://adventofcode.com/2023/day/1">Link to challenge</a>
 */
public class AocY2023D10 extends AdventOfCode<Long> {
    public AocY2023D10() {
        super(2023, 10);
    }

    enum Direction {
        NORTH,
        WEST,
        EAST,
        SOUTH
    }

    record Pipe(Direction first, Direction second) {}

    private Pipe parsePipe(char character) {
        return switch (character) {
            case '|' -> new Pipe(Direction.NORTH, Direction.SOUTH);
            case '-' -> new Pipe(Direction.EAST, Direction.WEST);
            case 'L' -> new Pipe(Direction.NORTH, Direction.EAST);
            case 'J' -> new Pipe(Direction.NORTH, Direction.WEST);
            case '7' -> new Pipe(Direction.SOUTH, Direction.WEST);
            case 'F' -> new Pipe(Direction.SOUTH, Direction.EAST);
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
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