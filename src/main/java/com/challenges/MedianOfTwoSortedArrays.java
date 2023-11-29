package com.challenges;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.List;

/**
 * <a href="https://leetcode.com/problems/median-of-two-sorted-arrays/">Link to challenge</a>
 */
public class MedianOfTwoSortedArrays extends CodeChallenge<MedianOfTwoSortedArrays.Input, Double> {
    record Input(int[] nums1, int[] nums2) {}

    @Override
    protected Double test(Input input) {
        return null;
    }

    @Override
    protected List<InputOutput<Input, Double>> getInputAndDesiredOutputs() {
        return List.of(
            io(new Input(array(1,3), array(2)), 2.00000D)
        );
    }


}
