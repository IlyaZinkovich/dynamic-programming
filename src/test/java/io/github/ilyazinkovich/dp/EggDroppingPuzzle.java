package io.github.ilyazinkovich.dp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EggDroppingPuzzle {

    /**
     * f(i, 0) = 0
     * f(i, 1) = 1
     * f(1, j) = j
     * f(i, j) = 1 + min{max(f(j - 1, x - 1), f(i, j - x)), x in (1, j)}
     */
    public static int solve(int eggs, int floors) {
        int[][] trials = new int[eggs + 1][floors + 1];
        for (int i = 0; i <= eggs; i++) {
            trials[i][0] = 0;
            trials[i][1] = 1;
        }
        for (int j = 1; j <= floors; j++) {
            trials[1][j] = j;
        }
        for (int i = 2; i <= eggs; i++) {
            for (int j = 2; j <= floors; j++) {
                trials[i][j] = Integer.MAX_VALUE;
                for (int x = 1; x <= j; x++) {
                    int result = 1 + Math.max(trials[i - 1][x - 1], trials[i][j - x]);
                    trials[i][j] = Math.min(result, trials[i][j]);
                }
            }
        }
        return trials[eggs][floors];
    }

    @Test
    public void test() {
        assertEquals(8, solve(2, 36));
    }
}
