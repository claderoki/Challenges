package com.challenges;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.List;
import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/add-two-numbers/">Link to challenge</a>
 */
public class AddTwoNumbers extends CodeChallenge<AddTwoNumbers.AddTwoNumbersInput, AddTwoNumbers.ListNode> {
    public record AddTwoNumbersInput(ListNode l1, ListNode l2) {}

    @Override
    public boolean isEqual(ListNode output1, ListNode output2) {
        return output1.format().equals(output2.format());
    }

    @Override
    public String format(ListNode output) {
        return output.format();
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public String format() {
            StringBuilder stringBuilder = new StringBuilder("[");
            ListNode node = this;
            while (node != null) {
                stringBuilder.append(node.val);
                node = node.next;
                if (node != null) {
                    stringBuilder.append(",");
                }
            }
            return stringBuilder.append("]").toString();
        }
    }

    public Stack<Integer> toStack(ListNode node) {
        Stack<Integer> numbers = new Stack<>();
        while (node != null) {
            numbers.insertElementAt(node.val, 0);
            node = node.next;
        }
        return numbers;
    }

    private ListNode into(String numbers) {
        ListNode child = null;
        for (char number: numbers.toCharArray()) {
            child = new ListNode(Integer.parseInt(String.valueOf(number)), child);
        }
        return child;
    }

    private ListNode reverse(ListNode node) {
        ListNode child = null;
        while (node != null) {
            child = new ListNode(node.val, child);
            node = node.next;
        }
        return child;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> first = toStack(l1);
        Stack<Integer> second = toStack(l2);

        int remainder = 0;
        ListNode child = null;
        while (!first.isEmpty() || !second.isEmpty()) {
            var lastOfFirst = first.isEmpty() ? 0 : first.pop();
            var lastOfSecond = second.isEmpty() ? 0 : second.pop();

            int sum = lastOfFirst + lastOfSecond + remainder;
            if (sum > 9) {
                remainder = sum / 10;
                sum = sum % 10;
            } else {
                remainder = 0;
            }
            child = new ListNode(sum, child);
        }

        if (remainder > 0) {
            child = new ListNode(remainder, child);
        }
        return reverse(child);
    }

    public ListNode test(AddTwoNumbersInput input) {
        return addTwoNumbers(input.l1, input.l2);
    }

    private AddTwoNumbersInput input(String l1, String l2) {
        return new AddTwoNumbersInput(into(l1), into(l2));
    }

    @Override
    protected List<InputOutput<AddTwoNumbersInput, ListNode>> getInputAndDesiredOutputs() {
        return List.of(
            new InputOutput<>(input("1000000000000000000000000000001", "564"), into("1000000000000000000000000000565")),
            new InputOutput<>(input("243", "564"), into("807")),
            new InputOutput<>(input("9999999", "9999"), into("10009998"))
        );
    }
}
