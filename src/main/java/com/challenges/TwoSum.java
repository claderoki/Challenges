package com.challenges;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/two-sum/
 */
public class TwoSum extends CodeChallenge<TwoSum.TwoSumInput, int[]> {
    record TwoSumInput(int[] nums, int target) {}

    @Override
    protected int[] test(TwoSumInput input) {
        return array(0,1);
    }

    @Override
    public boolean isEqual(int[] output1, int[] output2) {
        return Arrays.equals(output1, output2);
    }

    @Override
    public List<InputOutput<TwoSum.TwoSumInput, int[]>> getInputAndDesiredOutputs() {
        return List.of(
            new InputOutput<>(new TwoSumInput(array(2,7,11,15), 9), array(0,1)),
            new InputOutput<>(new TwoSumInput(array(3,2,4), 6), array(1,2)),
            new InputOutput<>(new TwoSumInput(array(3,3), 6), array(0,1))
        );
    }
}
