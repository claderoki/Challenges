package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import com.challenges.base.RunTimeTracker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://adventofcode.com/2023/day/7">Link to challenge</a>
 */
public class AocY2023D7 extends AdventOfCode<Long> {
    public AocY2023D7() {
        super(2023, 7);
    }

    enum CardType {
        // where all cards' labels are distinct: 23456
        HIGH_CARD,
        // where two cards share one label, and the other three cards have a different label from the pair and each other: A23A4
        ONE_PAIR,
        // where two cards share one label, two other cards share a second label, and the remaining card has a third label: 23432
        TWO_PAIR,
        // where three cards have the same label, and the remaining two cards are each different from any other card in the hand: TTT98
        THREE_OF_A_KIND,
        // where three cards have the same label, and the remaining two cards share a different label: 23332
        FULL_HOUSE,
        // where four cards have the same label and one card has a different label: AA8AA
        FOUR_OF_A_KIND,
        // where all five cards have the same label: AAAAA
        FIVE_OF_A_KIND;
    }

    record Hand(char[] cards, CardType cardType, long bid) implements Comparator<Hand> {
        @Override
        public int compare(Hand o1, Hand o2) {
            int typeCompare = o1.cardType.compareTo(o2.cardType);
            if (typeCompare != 0) {
                return typeCompare;
            }
            return 0;
        }
    }

    private CardType calculateCardType(char[] cards) {
        Map<Character, Integer> c = new HashMap<>();
        for (char character: cards) {
            c.merge(character, 1, Integer::sum);
        }
        if (c.size() == 1) {
            return CardType.FIVE_OF_A_KIND;
        } else if (c.size() == 5) {
            return CardType.HIGH_CARD;
        }


        return null;
    }

    private Hand parse(String line) {
        var split = line.split(" ");
        char[] cards = split[0].toCharArray();
        return new Hand(cards, calculateCardType(cards), Long.parseLong(split[1]));
    }

    public Long part1(String input) {
        var hands = input
            .lines()
            .map(this::parse)
            .sorted()
            .toList();
        return 0L;
    }

    public Long part2(String input) {
        return 0L;
    }

    public void run() {
        String input = input('e');
        try (var ignore = new RunTimeTracker()) {
            System.out.println("Part 1 result => " + part1(input));
        }
        try (var ignore = new RunTimeTracker()) {
            System.out.println("Part 2 result => " + part2(input));
        }
    }
}