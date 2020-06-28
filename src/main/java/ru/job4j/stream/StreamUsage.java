package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(-1, 3, 0, -6, -4, 90, 125);
        list = list.stream().filter((x) -> x > 0).collect(Collectors.toList());
        for (int value : list) {
            System.out.println(value);
        }
    }
}
