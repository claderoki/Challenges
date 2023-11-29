package com.challenges;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.List;

/**
 * <a href="https://leetcode.com/problems/wildcard-matching/description">Link to challenge</a>
 */
public class WildcardMatching extends CodeChallenge<WildcardMatching.Input, Boolean> {
    record Input(String value, String pattern) {}

    @Override
    protected Boolean test(Input input) {
//        int longestLength = i;
        boolean inWildcard = false;
        for (int i = 0; i < input.pattern.length(); i++) {

        }

        return null;
    }

    private Input input(String value, String pattern) {
        return new Input(value, pattern);
    }

    @Override
    protected List<InputOutput<Input, Boolean>> getInputAndDesiredOutputs() {
        return List.of(
            io(input("aa", "a"), false),
            io(input("aa", "*"), true),
            io(input("", "*"), true),
            io(input("adceb", "*a*b"), true),
            io(input("", "?"), false),
            io(input("abc", "a?c"), true),
            io(input("cb", "?a"), false)
        );
    }
}
