package com.challenges.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CodeChallenge<I, O> {
    protected abstract O test(I input);
    protected abstract List<InputOutput<I, O>> getInputAndDesiredOutputs();

    public InputOutput<I, O> io(I input, O output) {
        return new InputOutput<>(input, output);
    }

    public boolean isEqual(O output1, O output2) {
        return output1.equals(output2);
    }

    public String format(O output) {
        return output.toString();
    }

    protected String aoc(int year, int day, Character prefix) {
        var stream = getClass().getResourceAsStream("/aoc/%s/%s%s.input".formatted(year, day, (prefix == null ? "" : "."+prefix)));
        return new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))
            .lines()
            .collect(Collectors.joining("\n"));
    }

    protected String aoc(int year, int day) {
        return aoc(year, day, null);
    }

    public void testAll() {
        int i = 0;
        long totalMillis = 0;
        for(var inputOutput: getInputAndDesiredOutputs()) {
            long startTime = System.currentTimeMillis();
            var output = test(inputOutput.input);
            long endTime = System.currentTimeMillis();
            if (!isEqual(output, inputOutput.output)) {
                throw new RuntimeException(format(output) + " != " + format(inputOutput.output));
            }
            long millis = endTime - startTime;
            totalMillis += millis;
            i++;
            System.out.printf("Test cases succeeded (%s/%s) in %s ms\n", i, i, millis);
        }
        System.out.printf("All test cases succeeded (%s/%s) in %s ms", i, i, totalMillis);
    }

    protected int[] array(int... values) {
        return values;
    }
}