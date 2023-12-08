package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import com.challenges.base.ProgressBar;
import com.challenges.base.RunTimeTracker;

import javax.print.attribute.standard.PageRanges;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <a href="https://adventofcode.com/2023/day/6">Link to challenge</a>
 */
public class AocY2023D6 extends AdventOfCode<Long> {
    public AocY2023D6() {
        super(2023, 6);
    }

    record RaceScore(long time, long distance) {}

    private List<Integer> parseDigits(String line) {
        return Arrays.stream(line.split(" "))
            .map(String::strip)
            .filter(c -> !c.isBlank())
            .filter(c -> Character.isDigit(c.charAt(0)))
            .map(Integer::parseInt)
            .toList();
    }

    private Long parseIntoSingleDigit(String line) {
        return Long.parseLong(line.substring(line.indexOf(":")+1).replace(" ", ""));
    }

    private boolean wouldWin(RaceScore score, long speed) {
        long timeLeft = score.time() - speed;
        return timeLeft * speed > score.distance();
    }

    private long calculateMarginOfError(RaceScore score) {
        ProgressBar progressBar = new ProgressBar(score.distance());
        int marginOfError = 0;
        for (int speed = 1; speed <= score.distance(); speed++) {
            if (wouldWin(score, speed)) {
                marginOfError++;
            }
            progressBar.setCurrent(speed);
        }
        return marginOfError;
    }

    private long calculateFirstWinning(RaceScore score) {
        int marginOfError = 0;
        for (long speed = 1; speed <= score.distance(); speed++) {
            if (wouldWin(score, speed)) {
                return speed-1;
            }
        }
        return marginOfError;
    }

    private long calculateLastWinning(RaceScore score) {
        int marginOfError = 0;
        int i = 0;
        for (long speed = score.distance()-1; speed >= 1; speed--) {
            if (wouldWin(score, speed)) {
                return i;
            }
            i++;
        }
        return marginOfError;
    }

    public Long part1(String input) {
        String[] split = input.split("\n");

        List<Integer> times = parseDigits(split[0]);
        List<Integer> distances = parseDigits(split[1]);

        List<RaceScore> scores = IntStream.range(0, times.size())
            .mapToObj(i -> new RaceScore(times.get(i), distances.get(i)))
            .toList();

        var v = scores.stream()
            .mapToLong(c -> {
                var first = calculateFirstWinning(c);
                var last = calculateLastWinning(c);
                var real = calculateMarginOfError(c);
                var attempt = last + first;
                if (attempt != real) {
                    throw new RuntimeException("HEH " + attempt + " R " + real);
                }

                return attempt;
            })
            .reduce(1, (a,b) -> a*b);

        return scores.stream()
            .mapToLong(this::calculateMarginOfError)
            .reduce(1, (a, b) -> a * b);
    }

    public Long part2(String input) {
        String[] split = input.split("\n");

        Long time = parseIntoSingleDigit(split[0]);
        Long distance = parseIntoSingleDigit(split[1]);
        RaceScore score = new RaceScore(time, distance);

//        var first = calculateFirstWinning(score);
//        var last = calculateLastWinning(score);
//        return 0L;
        return calculateMarginOfError(score);
    }

    public void run() {
        // 840336
//        try (var ignore = new RunTimeTracker()) {
//            System.out.println("Part 1 result => " + part1(input('e')));
//        }
        try (var ignore = new RunTimeTracker()) {
            System.out.println("Part 2 result => " + part2(input()));
        }
    }
}