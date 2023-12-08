package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import com.challenges.base.RunTimeTracker;

import java.util.*;
import java.util.function.Supplier;

/**
 * <a href="https://adventofcode.com/2023/day/5">Link to challenge</a>
 */
public class AocY2023D5 extends AdventOfCode<Long> {
    public AocY2023D5() {
        super(2023, 5);
    }

    record Range(long destinationStart, long sourceStart, long range) {}

    record RangeGroup(List<Range> ranges) {
        public long destination(long value) {
            for(var range: ranges) {
                if (value >= range.sourceStart && value <= (range.sourceStart+range.range)) {
                    return range.destinationStart + (value - range.sourceStart);
                }
            }
            return value;
        }
    }

    record Almanac(List<Long> seeds,
                   RangeGroup seedToSoil,
                   RangeGroup soilToFertiliser,
                   RangeGroup fertiliserToWater,
                   RangeGroup waterToLight,
                   RangeGroup lightToTemperature,
                   RangeGroup temperatureToHumidity,
                   RangeGroup humidityToLocation
    ) {
        public long findLocation(long seed) {
            var soil = seedToSoil.destination(seed);
            var fertiliser = soilToFertiliser.destination(soil);
            var water = fertiliserToWater.destination(fertiliser);
            var light = waterToLight.destination(water);
            var temperature = lightToTemperature.destination(light);
            var humidity = temperatureToHumidity.destination(temperature);
            return humidityToLocation.destination(humidity);
        }

    }

    private Almanac parse(String input) {
        Almanac almanac = new Almanac(
            new ArrayList<>(),
            new RangeGroup(new ArrayList<>()),
            new RangeGroup(new ArrayList<>()),
            new RangeGroup(new ArrayList<>()),
            new RangeGroup(new ArrayList<>()),
            new RangeGroup(new ArrayList<>()),
            new RangeGroup(new ArrayList<>()),
            new RangeGroup(new ArrayList<>())
        );

        String[] lines = input.split("\n");
        String first = lines[0];
        List<Long> d = Arrays.stream(first.substring(first.indexOf(":")+1)
            .split(" "))
            .filter(c -> !c.isBlank()).map((Long::parseLong)).toList();
        almanac.seeds.addAll(d);
        Supplier<RangeGroup> getter = null;
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
                List<Long> digits = Arrays.stream(line.split(" ")).map((Long::parseLong)).toList();
                getter.get().ranges().add(new Range(digits.get(0), digits.get(1), digits.get(2)));
            }
        }
        return almanac;
    }

    public Long part1(String input) {
        Almanac almanac = parse(input);
        return almanac.seeds.stream().mapToLong(almanac::findLocation).min().getAsLong();
    }

    public int calculatePercentage(long obtained, long total) {
        return (int)(obtained * 100 / total);
    }

    public Long part2(String input) {
        long lowest = Long.MAX_VALUE;
        Almanac almanac = parse(input);
        for (int i = 0; i < almanac.seeds.size(); i+=2) {
            int percentage = 0;
            long start = almanac.seeds.get(i);
            long range = almanac.seeds.get(i+1);
            System.out.println("Processing range %s - %s".formatted(start, start+range));
            long end = start+range;
            for (long j = start; j < end; j++) {
                long loc = almanac.findLocation(j);
                if (loc < lowest) {
                    lowest = loc;
                }
                int currentPercentage = calculatePercentage(j-start, end-start);
                if (currentPercentage != percentage) {
                    System.out.println(currentPercentage + "% finished");
                    percentage = currentPercentage;
                }
            }
        }

        System.out.println("100% finished");
        return lowest;
    }

    public void run() {
        try (var ignore = new RunTimeTracker()) {
            System.out.println("Part 1 result => " + part1(input()));
        }
        try (var ignore = new RunTimeTracker()) {
            System.out.println("Part 2 result => " + part2(input()));
        }
    }
}
