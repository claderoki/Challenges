package com.challenges;

import java.util.Map;

public abstract class CodeChallenge<I, O> {
    public abstract O test(I input);
    public abstract Map<I, O> getInputAndDesiredOutputs();

    public boolean isEqual(O output1, O output2) {
        return output1.equals(output2);
    }

    public void testAll() {
        int i = 0;
        for(var entry: getInputAndDesiredOutputs().entrySet()) {
            var output = test(entry.getKey());
            if (!isEqual(output, entry.getValue())) {
                throw new RuntimeException(output + " != " + entry.getValue());
            }
            i++;
        }
        System.out.printf("All test cases succeeded (%s)%n", i);
    }
}