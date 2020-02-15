package io.github.ilyazinkovich.dp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoinChangeProblem {

    public static int solve(int n, int[] coins) {
        int[] change = new int[n + 1];
        change[0] = 1;
        for (int coin : coins) {
            for (int j = coin; j <= n; j++) {
                change[j] += change[j - coin];
            }
        }
        return change[n];
    }

    @Test
    public void test() {
        assertEquals(4, solve(4, new int[]{1, 2, 3}));
    }
}
