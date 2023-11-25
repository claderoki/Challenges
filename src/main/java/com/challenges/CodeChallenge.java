package com.challenges;

import java.util.Map;

public abstract class CodeChallenge<I, O> {
    public abstract O test(I input);
    public abstract Map<I, O> getInputAndDesiredOutputs();

    public boolean isEqual(O output1, O output2) {
        return output1.equals(output2);
    }

    public void testAll() {
        getInputAndDesiredOutputs().forEach((i,o) -> {
            var output = test(i);
            if (!isEqual(output, o)) {
                throw new RuntimeException(output + " != " + o);
            }
        });
    }
}