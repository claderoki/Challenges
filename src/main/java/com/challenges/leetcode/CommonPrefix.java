package com.challenges.leetcode;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.List;

/**

 * <a href="">Link to challenge</a>
 */
public class CommonPrefix extends CodeChallenge<String[], String> {
    @Override
    protected String test(String[] input) {
        StringBuilder prefix = new StringBuilder();

        int i = 0;
        while (true) {
            Character character = null;
            for(String str: input) {
                if (i >= str.length()) {
                    return prefix.toString();
                }
                if (character == null) {
                    character = str.charAt(i);
                } else if (character != str.charAt(i)) {
                    return prefix.toString();
                }
            }
            prefix.append(character);
            i++;
        }
    }

    String[] input(String... input) {return input;}

    @Override
    protected List<InputOutput<String[], String>> getInputAndDesiredOutputs() {
        return List.of(
            io(input("flower", "flow", "flight"), "fl")
        );
    }
}
