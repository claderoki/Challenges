package com.challenges;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.List;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 *
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
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
            io(new Input(array(1,3), array(2)), 2.00000D),
            io(new Input(array(1,2), array(3,4)), 2.50000D)
        );
    }


}
