package com.challenges;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.List;

/**
 * <a href="https://leetcode.com/problems/longest-palindromic-substring/description/">Link to challenge</a>
 */
public class LongestPalindromicSubstring extends CodeChallenge<String, String> {
    record Indexes(int start, int end) {
        public int size() {
            return end - start;
        }
    }

    private Indexes findPalindrome(String input, int center) {
        int start = Math.max(center-1, 0);
        int end = Math.min(center+1, input.length()-1);

        char centerChar = input.charAt(center);
        if (centerChar == input.charAt(start)) {
            end = center;
        } else if (centerChar == input.charAt(end)) {
            start = center;
        }

        while (start > 0 && end < input.length()-1 && input.charAt(start-1) == input.charAt(end+1)) {
            start--;
            end++;
        }
        return new Indexes(start, end);
    }

    @Override
    protected String test(String input) {
        Indexes longest = null;
        for(int i = 0; i < input.length(); i++) {
            var palindrome = findPalindrome(input, i);
            if (longest == null || palindrome.size() > longest.size()) {
                longest = palindrome;
            }
        }
        return input.substring(longest.start, longest.end+1);
    }

    @Override
    protected List<InputOutput<String, String>> getInputAndDesiredOutputs() {
        return List.of(
//            new InputOutput<>("babad", "bab"),
//            new InputOutput<>("cbbd", "bb"),
//            new InputOutput<>("a", "a"),
//            new InputOutput<>("ac", "a"),
//            new InputOutput<>("aaaa", "aaaa"),
//            new InputOutput<>("xabax", "xabax"),
            new InputOutput<>("ccc", "ccc")
        );
    }
}
