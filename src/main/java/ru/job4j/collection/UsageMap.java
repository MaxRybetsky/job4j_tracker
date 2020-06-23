package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("maximrybetsky@gmail.com", "Rybetsky Maxim Alexandrovich");
        map.put("ivan@ivan.ru", "Ivanov Ivan Ivanovich");
        for (String key : map.keySet()) {
            System.out.println(map.get(key));
        }
    }
}
