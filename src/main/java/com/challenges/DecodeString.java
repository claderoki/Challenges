package com.challenges;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/decode-string
 */
public class DecodeString extends CodeChallenge<String, String> {
    private String decodeString(String s) {
        StringBuilder output = new StringBuilder();
        StringBuilder currentValue = new StringBuilder();
        StringBuilder digit = new StringBuilder();
        StringBuilder nestedValue = new StringBuilder();
        int bracketStartCount = 0;
        int bracketEndCount = 0;

        for(var character: s.toCharArray()) {
            if (!nestedValue.isEmpty()) {
                nestedValue.append(character);
            }
            if (Character.isDigit(character)) {
                if (bracketStartCount == 0) {
                    if (!currentValue.isEmpty()) {
                        output.append(currentValue);
                        currentValue.delete(0, currentValue.length());
                    }
                    digit.append(character);
                } else if (nestedValue.isEmpty()) {
                    nestedValue.append(character);
                }
            } else if (character == '[') {
                bracketStartCount++;
            } else if (character == ']') {
                bracketEndCount++;
            } else if (nestedValue.isEmpty()) {
                currentValue.append(character);
            }

            if (bracketStartCount > 0 && bracketStartCount == bracketEndCount) {
                if (!nestedValue.isEmpty()) {
                    currentValue.append(decodeString(nestedValue.toString()));
                    nestedValue.delete(0, nestedValue.length());
                }
                if (digit.isEmpty()) {
                    output.append(currentValue);
                } else {
                    output.append(currentValue.toString().repeat(Integer.parseInt(digit.toString())));
                }

                bracketEndCount = 0;
                bracketStartCount = 0;
                digit.delete(0, digit.length());
                currentValue.delete(0, currentValue.length());
            }
        }
        if (!nestedValue.isEmpty()) {
            output.append(decodeString(nestedValue.toString()));
        }
        if (!currentValue.isEmpty()) {
            output.append(currentValue);
        }
        return output.toString();
    }

    public String test(String input) {
        return decodeString(input);
    }

    @Override
    public List<InputOutput<String, String>> getInputAndDesiredOutputs() {
        return List.of(
            new InputOutput<>("3[a]2[bc]", "aaabcbc"),
            new InputOutput<>("3[a2[c]]", "accaccacc"),
            new InputOutput<>("2[abc]3[cd]ef", "abcabccdcdcdef"),
            new InputOutput<>("abc3[cd]xyz", "abccdcdcdxyz"),
            new InputOutput<>("3[z]2[2[y]pq4[2[jk]e1[f]]]ef", "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef"),
            new InputOutput<>("2[ab3[cd]]4[xy]", "abcdcdcdabcdcdcdxyxyxyxy"),
            new InputOutput<>("3[a10[bc]]", "abcbcbcbcbcbcbcbcbcbcabcbcbcbcbcbcbcbcbcbcabcbcbcbcbcbcbcbcbcbc)")
        );
    }

}
