package io.github.ilyazinkovich.dp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiceThrowProblem {

    /**
     * combinations(1, j) = 1, j < min(sum, faces), 0 j >= min(sum, faces)
     * combinations(i, j) = sum{combinations(i - 1, j - k), k in (1, min(j - 1, faces))}
     */
    public static int solve(int faces, int dices, int sum) {
        int[][] combinations = new int[dices + 1][sum + 1];
        for (int j = 1; j <= Math.min(sum, faces); j++) {
            combinations[1][j] = 1;
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

    @Test
    public void test() {
        assertEquals(0, solve(4, 2, 1));
        assertEquals(2, solve(2, 2, 3));
        assertEquals(21, solve(6, 3, 8));
        assertEquals(4, solve(4, 2, 5));
        assertEquals(6, solve(4, 3, 5));
    }
}
