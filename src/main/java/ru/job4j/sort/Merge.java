package ru.job4j.sort;

import java.util.Arrays;

public class Merge {
    public int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int posL = 0;
        int posR = 0;
        for (int i = 0; i < rsl.length; i++) {
            if (posL == left.length) {
                rsl[i] = right[posR++];
            } else if (posR == right.length) {
                rsl[i] = left[posR++];
            } else {
                rsl[i] = left[posL] < right[posR] ? left[posL++] : right[posR++];
            }
        }
        return rsl;
    }
}
