package io.github.ilyazinkovich.dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class WordBreakProblem {

    public static boolean solveRecursively(String word, String[] dictionary) {
        if (word.isEmpty()) {
            return true;
        } else {
            for (String dictionaryWord : dictionary) {
                if (word.startsWith(dictionaryWord)) {
                    return solveRecursively(word.substring(dictionaryWord.length()), dictionary);
                }
            }
            return false;
        }
    }

    public static boolean solveDP(String word, String[] dictionary) {
        List<Integer> candidates = new ArrayList<>();
        candidates.add(0);
        boolean[] failures = new boolean[word.length()];
        while (!candidates.isEmpty()) {
            boolean failed = true;
            Integer candidate = candidates.remove(0);
            if (failures[candidate]) {
                continue;
            }
            for (String dictionaryWord : dictionary) {
                if (word.substring(candidate).startsWith(dictionaryWord)) {
                    if (candidate + dictionaryWord.length() == word.length()) {
                        return true;
                    } else {
                        failed = false;
                        candidates.add(candidate + dictionaryWord.length());
                    }
                }
            }
            failures[candidate] = failed;
        }
        return false;
    }

    @Test
    public void test() {
        String[] dictionary =
                {"i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "icecream", "man", "go", "mango"};
        assertTrue(solveRecursively("ilikesamsung", dictionary));
        assertTrue(solveDP("ilikesamsung", dictionary));
    }
}
