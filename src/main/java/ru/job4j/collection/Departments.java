package ru.job4j.collection;

import java.util.*;

public class Departments {

    public static List<String> fillGaps(List<String> deps) {
        Set<String> tmp = new HashSet<>();
        for (String value : deps) {
            StringBuffer start = new StringBuffer("");
            for (String str : value.split("/")) {
                start.append("/").append(str);
                tmp.add(start.substring(1));
            }
        }
        List<String> result = new ArrayList<>(tmp);
        sortAsc(result);
        return result;
    }

    public static void sortAsc(List<String> orgs) {
        orgs.sort(Comparator.naturalOrder());
    }

    public static void sortDesc(List<String> orgs) {
        orgs.sort(new DepDescComp());
    }
}