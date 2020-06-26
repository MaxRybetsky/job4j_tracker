package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class SearchAtt {

    public static List<Attachment> filterSize(List<Attachment> list, int value) {
        Predicate<Attachment> predicate = new Predicate<>() {
            @Override
            public boolean test(Attachment attachment) {
                return attachment.getSize() > value;
            }
        };
        return loop(list, predicate);
    }

    public static List<Attachment> filterName(List<Attachment> list, String value) {
        Predicate<Attachment> predicate = new Predicate<>() {
            @Override
            public boolean test(Attachment attachment) {
                return attachment.getName().contains(value);
            }
        };
        return loop(list, predicate);
    }

    private static List<Attachment> loop(List<Attachment> list, Predicate<Attachment> predicate) {
        List<Attachment> result = new ArrayList<>();
        for (Attachment attachment : list) {
            if (predicate.test(attachment)) {
                result.add(attachment);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Attachment> lst = Arrays.asList(
                new Attachment("bug smth", 12),
                new Attachment("A smth", 120),
                new Attachment("smth bug", 1200)
        );
        List<Attachment> a = filterSize(lst, 100);
        for (Attachment at : a) {
            System.out.println(at);
        }
    }
}