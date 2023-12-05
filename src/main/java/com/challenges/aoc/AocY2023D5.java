package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import com.challenges.base.RunTimeTracker;

import java.util.*;
import java.util.function.Supplier;

/** Completed
 * <a href="https://adventofcode.com/2023/day/5">Link to challenge</a>
 */
public class AocY2023D5 extends AdventOfCode<Integer> {
    public AocY2023D5() {
        super(2023, 5);
    }

    record Range(int destinationStart, int sourceStart, int range) {}
    record RangeGroup(List<Range> ranges) {}

    record Almanac(Set<Integer> seeds,
                   RangeGroup seedToSoil,
                   RangeGroup soilToFertiliser,
                   RangeGroup fertiliserToWater,
                   RangeGroup waterToLight,
                   RangeGroup lightToTemperature,
                   RangeGroup temperatureToHumidity,
                   RangeGroup humidityToLocation
    ) {}

    private Almanac parse(String input) {
        Almanac almanac = new Almanac(
            new HashSet<>(),
            new RangeGroup(new ArrayList<>()),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );

        String[] lines = input.split("\n");
        String first = lines[0];
        List<Integer> d = Arrays.stream(first.substring(first.indexOf(":")+1).split(" ")).filter(c -> !c.isBlank()).map((Integer::parseInt)).toList();
        almanac.seeds.addAll(d);
        Supplier<List<Range>> getter = null;
        for(int i = 1; i < lines.length; i++) {
            String line = lines[i];
            boolean mapped = true;
            switch (line) {
                case "seed-to-soil map:" -> getter = almanac::seedToSoil;
                case "soil-to-fertilizer map:" -> getter = almanac::soilToFertiliser;
                case "fertilizer-to-water map:"-> getter = almanac::fertiliserToWater;
                case "water-to-light map:"-> getter = almanac::waterToLight;
                case "light-to-temperature map:"-> getter = almanac::lightToTemperature;
                case "temperature-to-humidity map:"-> getter = almanac::temperatureToHumidity;
                case "humidity-to-location map:"-> getter = almanac::humidityToLocation;
                default -> mapped = false;
            }

            if (!mapped && !line.isBlank()) {
                List<Integer> digits = Arrays.stream(line.split(" ")).map((Integer::parseInt)).toList();
                getter.get().add(
                    new Range(digits.get(0), digits.get(1), digits.get(2))
                );
            }
        }


        return almanac;
    }

    public Integer part1(String input) {
        Almanac almanac = parse(input);
        var a = "";
        return 0;
    }

    public Integer part2(String input) {
        return 0;
    }

    public void run() {
        try (var ignore = new RunTimeTracker()) {
            System.out.println("Part 1 result => " + part1(input('e')));
        }
        try (var ignore = new RunTimeTracker()) {
//            System.out.println("Part 2 result => " + part2(input()));
        }
    }
}
