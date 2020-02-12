package io.github.ilyazinkovich.dp;

import static java.util.Arrays.sort;
import static java.util.Comparator.comparingInt;

public class BoxStackingProblem {

    public static int solve(int n, int[] heights, int[] widths, int[] depths) {
        int count = n * 3;
        Option[] options = generateOptions(n, heights, widths, depths, count);
        sort(options, comparingInt(option -> -option.depth * option.width));
        int[] maxHeights = new int[count];
        int[] bottoms = new int[count];
        findMaxHeights(count, options, maxHeights, bottoms);
        int maxHeight = maxHeights[0];
        int top = 0;
        for (int i = 1; i < count; i++) {
            if (maxHeight < maxHeights[i]) {
                maxHeight = maxHeights[i];
                top = i;
            }
        }
        printStack(options, bottoms, top);
        return maxHeight;
    }

    /**
     * maxHeight(i) = max{height(i) + maxHeight(j), j < i, width(i) < width(bottom(i)), depth(i) < depth(bottom(i))}
     */
    private static void findMaxHeights(int count, Option[] options, int[] maxHeights, int[] bottoms) {
        for (int i = 0; i < count; i++) {
            maxHeights[i] = options[i].height;
            bottoms[i] = i;
        }
        for (int i = 1; i < count; i++) {
            int maxHeight = maxHeights[i];
            int bottom = bottoms[i];
            for (int j = 0; j < i; j++) {
                if (options[bottoms[i]].depth < options[j].depth && options[bottoms[i]].width < options[j].width) {
                    int height = options[bottoms[i]].height + maxHeights[j];
                    if (maxHeight < height) {
                        maxHeight = height;
                        bottom = j;
                    }
                }
            }
            maxHeights[i] = maxHeight;
            bottoms[i] = bottom;
        }
    }

    private static Option[] generateOptions(int n, int[] heights, int[] widths, int[] depths, int count) {
        Option[] options = new Option[count];
        for (int i = 0; i < n; i++) {
            options[i * 3] = new Option(heights[i], widths[i], depths[i]);
            options[i * 3 + 1] = new Option(widths[i], heights[i], depths[i]);
            options[i * 3 + 2] = new Option(depths[i], heights[i], widths[i]);
        }
        return options;
    }

    private static void printStack(Option[] options, int[] bottoms, int top) {
        int currentBox = top;
        while (true) {
            System.out.println(options[currentBox]);
            if (currentBox == bottoms[currentBox]) {
                break;
            } else {
                currentBox = bottoms[currentBox];
            }
        }
    }

    public static void main(String[] args) {
        assert solve(4, new int[]{4, 1, 4, 10}, new int[]{6, 2, 5, 12}, new int[]{7, 3, 6, 32}) == 60;
    }

    private static class Option {
        public final int height;
        public final int width;
        public final int depth;

        private Option(int height, int width, int depth) {
            this.height = height;
            this.width = width;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return height + "x" + width + "x" + depth;
        }
    }
}
