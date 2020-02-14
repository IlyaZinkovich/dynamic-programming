package io.github.ilyazinkovich.dp;

import static org.junit.Assert.assertEquals;

public class DiceThrowProblem {

    public static int solve(int dices, int faces, int sum) {
        int[][] combinations = new int[dices + 1][sum + 1];
        for (int i = 1; i <= Math.min(sum, faces); i++) {
            combinations[1][i] = 1;
        }
        for (int i = 2; i <= dices; i++) {
            for (int j = 1; j <= sum; j++) {
                for (int k = 1; k <= Math.min(j - 1, faces); k++) {
                    combinations[i][j] += combinations[i - 1][j - k];
                }
            }
        }
        return combinations[dices][sum];
    }

    public static void main(String[] args) {
        assertEquals(0, solve(4, 2, 1));
        assertEquals(2, solve(2, 2, 3));
        assertEquals(21, solve(6, 3, 8));
        assertEquals(4, solve(4, 2, 5));
    }
}
