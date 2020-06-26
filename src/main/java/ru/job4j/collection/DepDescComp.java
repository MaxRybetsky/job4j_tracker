package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String[] strs1 = o1.split("/");
        String[] strs2 = o2.split("/");
        int result = strs2[0].compareTo(strs1[0]);
        if (result != 0) {
            return result;
        }
        for (int i = 1; i < Math.min(strs1.length, strs2.length); i++) {
            result = strs1[i].compareTo(strs2[i]);
            if (result != 0) {
                return result;
            }
        }
        return  strs1.length - strs2.length;
    }
}
