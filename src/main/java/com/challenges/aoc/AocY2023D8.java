package com.challenges.aoc;

import com.challenges.base.AdventOfCode;
import com.challenges.base.RunTimeTracker;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2023/day/8">Link to challenge</a>
 */
public class AocY2023D8 extends AdventOfCode<Long> {
    public AocY2023D8() {
        super(2023, 8);
    }

    static class Node {
        private final String identifier;
        private Node left;
        private Node right;

        public Node(String identifier) {
            this.identifier = identifier;
        }

        public Node move(Character side) {
            return side == 'L' ? left : right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public String getIdentifier() {
            return identifier;
        }
    }
    record Map(List<Character> instructions, List<Node> nodes) {}

    Map parse(String input) {
        String[] lines = input.split("\n");
        List<Character> instructions = new ArrayList<>();
        for(char character: lines[0].toCharArray()) {
            instructions.add(character);
        }

        record RawNode(String left, String right) {}

        HashMap<String, RawNode> rawNodes = new HashMap<>();
        for (int i = 2; i < lines.length; i++) {
            String line = lines[i];
            String left = line.substring(7, 10);
            String right = line.substring(12, 15);
            rawNodes.put(line.substring(0, 3), new RawNode(left, right));
        }

        HashMap<String, Node> nodes = new HashMap<>();
        for(var entry: rawNodes.entrySet()) {
            var node = nodes.computeIfAbsent(entry.getKey(), Node::new);
            node.setLeft(nodes.computeIfAbsent(entry.getValue().left(), Node::new));
            node.setRight(nodes.computeIfAbsent(entry.getValue().right(), Node::new));
        }
        return new Map(instructions, nodes.values().stream().toList());
    }

    public Long part1(String input) {
        Map map = parse(input);

        Node current = map.nodes().stream().filter(c -> c.getIdentifier().equals("AAA")).findFirst().orElseThrow();
        long steps = 0;
        int instructionIndex = 0;
        while (!current.getIdentifier().equals("ZZZ")) {
            current = current.move(map.instructions().get(instructionIndex));
            if (instructionIndex >= map.instructions().size()-1) {
                instructionIndex = 0;
            } else {
                instructionIndex++;
            }
            steps++;
        }
        return steps;
    }

    private static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    private static long lcm(List<Long> input) {
        long result = input.get(0);
        for (var digit: input) {
            result = lcm(result, digit);
        }
        return result;
    }

    public Long part2(String input) {
        Map map = parse(input);

        List<Node> initial = map.nodes().stream().filter(c -> c.getIdentifier().endsWith("A")).toList();

        List<Long> totalSteps = new ArrayList<>();
        for(Node current: initial) {
            long steps = 0;
            int instructionIndex = 0;
            while (!current.getIdentifier().endsWith("Z")) {
                current = current.move(map.instructions().get(instructionIndex));
                if (instructionIndex >= map.instructions().size()-1) {
                    instructionIndex = 0;
                } else {
                    instructionIndex++;
                }
                steps++;
            }
            totalSteps.add(steps);
        }
        System.out.println(totalSteps.stream().map(c -> c.toString()).collect(Collectors.joining(", ")));
        return lcm(totalSteps);
    }

    public void run() {
        String input = input('l');
        try (var ignore = new RunTimeTracker()) {
            System.out.println("Part 1 result => " + part1(input));
        }
        try (var ignore = new RunTimeTracker()) {
            System.out.println("Part 2 result => " + part2(input));
        }
    }
}