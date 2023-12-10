package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import com.challenges.base.RunTimeTracker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * <a href="https://adventofcode.com/2023/day/7">Link to challenge</a>
 */
public class AocY2023D7 extends AdventOfCode<Long> {
    public AocY2023D7() {
        super(2023, 7);
    }

    enum CardType {
        /** where all cards' labels are distinct: 23456 */
        HIGH_CARD,
        /** where two cards share one label, and the other three cards have a different label from the pair and each other: A23A4 */
        ONE_PAIR,
        /** where two cards share one label, two other cards share a second label, and the remaining card has a third label: 23432 */
        TWO_PAIR,
        /** where three cards have the same label, and the remaining two cards are each different from any other card in the hand: TTT98 */
        THREE_OF_A_KIND,
        /** where three cards have the same label, and the remaining two cards share a different label: 23332 */
        FULL_HOUSE,
        /** where four cards have the same label and one card has a different label: AA8AA */
        FOUR_OF_A_KIND,
        /** where all five cards have the same label: AAAAA */
        FIVE_OF_A_KIND
    }

    private static int getValue(char card) {
        return switch (card) {
            case 'A' -> 1;
            case 'K' -> 2;
            case 'Q' -> 3;
            case 'J' -> 4;
            case 'T' -> 5;
            case '9' -> 6;
            case '8' -> 7;
            case '7' -> 8;
            case '6' -> 9;
            case '5' -> 10;
            case '4' -> 11;
            case '3' -> 12;
            case '2' -> 13;
            default -> throw new IllegalStateException("Unexpected value: " + card);
        };
    }

    record Hand(char[] cards, CardType cardType, long bid) implements Comparable<Hand> {
        @Override
        public int compareTo(Hand o) {
            int typeCompare = cardType.compareTo(o.cardType);
            if (typeCompare != 0) {
                return typeCompare;
            }
            for(int i = 0; i < 5; i++) {
                var card1 = getValue(cards[i]);
                var card2 = getValue(o.cards[i]);
                if (card1 > card2) {
                    return -1;
                } else if (card2 > card1) {
                    return 1;
                }
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

        var max = c.values().stream().mapToInt(p->p).max().getAsInt();
        if (max == 4) {
            return CardType.FOUR_OF_A_KIND;
        }
        if (max == 3) {
            return c.size() > 2 ? CardType.THREE_OF_A_KIND : CardType.FULL_HOUSE;
        }
        if (max == 2) {
            return c.size() == 4 ? CardType.ONE_PAIR : CardType.TWO_PAIR;
        }
        throw new RuntimeException("Can't really be the case.");
    }

    private Hand parse(String line) {
        var split = line.split(" ");
        var cards = split[0].toCharArray();
        return new Hand(cards, calculateCardType(cards), Long.parseLong(split[1]));
    }

    public Long part1(String input) {
        var hands = input
            .lines()
            .map(this::parse)
            .sorted()
            .toList();

        long sum = 0;
        for (int i = 0;i < hands.size(); i++) {
            var hand = hands.get(i);
            sum += hand.bid() * (i+1);
        }
        return sum;
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