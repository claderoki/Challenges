package com.challenges;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.HashSet;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/valid-sudoku/">Link to challenge</a>
 */
public class ValidSudoku extends CodeChallenge<char[][], Boolean> {
    @Override
    protected Boolean test(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            HashSet<Character> currentRow = new HashSet<>();
            HashSet<Character> currentColumn = new HashSet<>();
            HashSet<Character> currentBlock = new HashSet<>();

            for (int j = 0; j < board.length; j++) {
                char r = board[row][j];
                if (r != '.' && !currentRow.add(r)) {
                    return false;
                }
                char c = board[j][row];
                if (c != '.' && !currentColumn.add(c)) {
                    return false;
                }
                int blockX = (row%3*3)+j/3;
                int blockY = j%3;
                if (row > 0 && row/3 > 0) {
                    blockY += (row/3*3);
                }

                char b = board[blockX][blockY];
                if (b != '.' && !currentBlock.add(b)) {
                    return false;
                }
            }
        }
        return true;
    }

    private char[][] board(char[]... board) {
        return board;
    }

    private char[] row(char... chars) {
        return chars;
    }

    @Override
    protected List<InputOutput<char[][], Boolean>> getInputAndDesiredOutputs() {
        return List.of(
            new InputOutput<>(board(
                row('5','3','.', '.','7','.', '.','.','.'),
                row('6','.','.', '1','9','5', '.','.','.'),
                row('.','9','8', '.','.','.', '.','6','.'),
                row('8','.','.', '.','6','.', '.','.','3'),
                row('4','.','.', '8','.','3', '.','.','1'),
                row('7','.','.', '.','2','.', '.','.','6'),
                row('.','6','.', '.','.','.', '2','8','.'),
                row('.','.','.', '4','1','9', '.','.','5'),
                row('.','.','.', '.','8','.', '.','7','9')
            ), true),
            new InputOutput<>(board(
                row('5','5','.', '.','7','.', '.','.','.'),
                row('6','.','.', '1','9','5', '.','.','.'),
                row('.','9','8', '.','.','.', '.','6','.'),
                row('8','.','.', '.','6','.', '.','.','3'),
                row('4','.','.', '8','.','3', '.','.','1'),
                row('7','.','.', '.','2','.', '.','.','6'),
                row('.','6','.', '.','.','.', '2','8','.'),
                row('.','.','.', '4','1','9', '.','.','5'),
                row('.','.','.', '.','8','.', '.','7','9')
            ), false)
        );
    }
}
