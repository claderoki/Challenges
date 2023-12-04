package com.challenges.base;

import java.util.List;

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