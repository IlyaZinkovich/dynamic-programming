package io.github.ilyazinkovich.dp;

import org.junit.Test;

import java.util.Arrays;

import static java.lang.Math.max;
import static org.junit.Assert.assertEquals;

public class MaximalProductCuttingProblem {

    /**
     * f(0) = 0
     * f(1) = 0
     * f(i) = max{max(j, f(j)) * max(i - j, f(i - j)), j in (2, i / 2)}
     */
    public static int solve(int length) {
        int[] products = new int[length + 1];
        products[0] = 0;
        products[1] = 0;
        for (int i = 2; i <= length; i++) {
            int maxProduct = 0;
            for (int j = 1; j <= Math.floorDiv(i, 2); j++) {
                maxProduct = max(maxProduct, max(j, products[j]) * max(i - j, products[i - j]));
            }
            products[i] = maxProduct;
        }
        System.out.println(Arrays.toString(products));
        return products[length];
    }

    @Test
    public void test() {
        assertEquals(36, solve(10));
    }
}
