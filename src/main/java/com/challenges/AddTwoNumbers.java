package com.challenges;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/add-two-numbers/">Link to challenge</a>
 */
public class AddTwoNumbers extends CodeChallenge<AddTwoNumbers.AddTwoNumbersInput, AddTwoNumbers.ListNode> {
    public record AddTwoNumbersInput(ListNode l1, ListNode l2) {}
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private ListNode into(int numbers) {
        var builder = String.valueOf(numbers);
        ListNode child = null;
        for (int i = 0; i < builder.length(); i++) {
            child = new ListNode(Integer.parseInt(String.valueOf(builder.charAt(i))), child);
        }
        return child;
    }

    private int collect(ListNode node) {
        StringBuilder digits = new StringBuilder();
        do {
            digits.insert(0, String.valueOf(node.val));
            node = node.next;
        } while (node != null);
        return Integer.parseInt(digits.toString());
    }

    public ListNode test(AddTwoNumbersInput input) {
        return into(collect(input.l1) + collect(input.l2));
    }
    @Override
    protected List<InputOutput<AddTwoNumbersInput, ListNode>> getInputAndDesiredOutputs() {
        return List.of(
//            new InputOutput<>(into(807), into(702))
        );
    }


}
