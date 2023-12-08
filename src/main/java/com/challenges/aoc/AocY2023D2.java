package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2023/day/2">Link to challenge</a>
 */
public class AocY2023D2 extends AdventOfCode<Integer> {
    public AocY2023D2() {
        super(2023, 2);
    }

    private static final Map<Colour, Integer> maximum = Map.of(
        Colour.RED, 12,
        Colour.BLUE, 14,
        Colour.GREEN, 13
    );

    enum Colour {
        GREEN,
        BLUE,
        RED;

        public static Colour from(char character) {
            return switch (character) {
                case 'r' -> RED;
                case 'b' -> BLUE;
                case 'g' -> GREEN;
                default -> null;
            };
        }
    }

    record Cube(Colour colour, int count) {}

    record Game(int id, List<List<Cube>> hands) {}

    private Game toGame(String raw) {
        ArrayList<List<Cube>> hands = new ArrayList<>();
        ArrayList<Cube> cubes = new ArrayList<>();
        Integer id = null;
        for (int i = 0; i < raw.length(); i++) {
            char character = raw.charAt(i);
            if (Character.isDigit(character)) {
                StringBuilder digitsRaw =new StringBuilder();
                do {
                    digitsRaw.append(character);
                    character = raw.charAt(++i);
                } while (Character.isDigit(character));
                int digits = Integer.parseInt(digitsRaw.toString());
                if (id == null) {
                    id = digits;
                } else {
                    Colour colour = Colour.from(raw.charAt(i+1));
                    cubes.add(new Cube(colour, digits));
                    i += colour.name().length();
                }
            } else if (character == ';') {
                hands.add(cubes);
                cubes = new ArrayList<>();
            }
        }
        if (!cubes.isEmpty()) {
            hands.add(cubes);
        }

        return new Game(id, hands);
    }

    private boolean isValid(Game game) {
        for(List<Cube> hand: game.hands) {
            for(Cube cube: hand) {
                if (cube.count > maximum.get(cube.colour)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int toMinimumPower(Game game) {
        HashMap<Colour, Integer> minimums = new HashMap<>();

        for(List<Cube> hand: game.hands) {
            for(Cube cube: hand) {
                Integer min = minimums.get(cube.colour());
                if (min == null || cube.count > min) {
                    minimums.put(cube.colour, cube.count);
                }
            }
        }
        return minimums.get(Colour.GREEN) * minimums.get(Colour.BLUE) * minimums.get(Colour.RED);
    }

    public Integer part1(String input) {
        return input
            .lines()
            .map(this::toGame)
            .filter(this::isValid)
            .mapToInt(Game::id)
            .sum();
    }

    public Integer part2(String input) {
        return input
            .lines()
            .map(this::toGame)
            .mapToInt(this::toMinimumPower)
            .sum();
    }
}
