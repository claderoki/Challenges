package com.challenges.base;

import java.util.List;

public abstract class CodeChallenge<I, O> {
    protected abstract O test(I input);
    protected abstract List<InputOutput<I, O>> getInputAndDesiredOutputs();

    public boolean isEqual(O output1, O output2) {
        return output1.equals(output2);
    }

    public void testAll() {
        int i = 0;
        for(var inputOutput: getInputAndDesiredOutputs()) {
            var output = test(inputOutput.input);
            if (!isEqual(output, inputOutput.output)) {
                throw new RuntimeException(output + " != " + inputOutput.output);
            }
            i++;
        }
        System.out.printf("All test cases succeeded (%s/%s)", i, i);
    }

    protected static int[] array(int... values) {
        return values;
    }
}