package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StringComparing {
    public static void main(String[] args) {
        Comparator<String> comparator = (left, right) -> {
            System.out.println("compare - " + left + " : " + right);
            return right.compareTo(left);
        };
        List<String> strs = Arrays.asList("A", "B", "C", "D", "F");
        Collections.sort(strs, comparator);
        for (String str : strs) {
            System.out.println(str);
        }
    }
}
