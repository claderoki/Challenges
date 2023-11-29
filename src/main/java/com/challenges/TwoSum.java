package com.challenges;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.*;

/** Completed
 * <a href="https://leetcode.com/problems/two-sum/">Link to challenge</a>
 */
public class TwoSum extends CodeChallenge<TwoSum.TwoSumInput, int[]> {
    public record TwoSumInput(int[] nums, int target) {}

    @Override
    protected int[] test(TwoSumInput input) {
        HashMap<Integer, Integer> found = new HashMap<>();
        int i = 0;
        for(int num: input.nums) {
            int remaining = input.target() - num;
            Integer index = found.get(remaining);
            if (index != null) {
                return array(index, i);
            }
            found.put(num, i);
            i++;
        }
        throw new RuntimeException("Shouldn't happen.");
    }

    @Override
    public boolean isEqual(int[] output1, int[] output2) {
        return Arrays.equals(output1, output2);
    }

    @Override
    public List<InputOutput<TwoSumInput, int[]>> getInputAndDesiredOutputs() {
        return List.of(
            new InputOutput<>(new TwoSumInput(array(2,7,11,15), 9), array(0,1)),
            new InputOutput<>(new TwoSumInput(array(3,2,4), 6), array(1,2)),
            new InputOutput<>(new TwoSumInput(array(3,3), 6), array(0,1))
        );
    }
}
