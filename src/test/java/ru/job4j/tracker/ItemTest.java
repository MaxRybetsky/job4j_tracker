package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemTest {
    @Test
    public void whenAscendingSort() {
        List<Item> items = Arrays.asList(new Item(4, "Fourth"),
                new Item(1, "First"),
                new Item(5, "Fifth"),
                new Item(2, "Second"));
        List<Item> expected = Arrays.asList(new Item(1, "First"),
                new Item(2, "Second"),
                new Item(4, "Fourth"),
                new Item(5, "Fifth"));
        Collections.sort(items);
        assertThat(
                items,
                is(expected)
        );
    }

    @Test
    public void whenReverseSort() {
        List<Item> items = Arrays.asList(new Item(4, "Fourth"),
                new Item(5, "Fifth"),
                new Item(1, "First"),
                new Item(2, "Second"));
        List<Item> expected = Arrays.asList(new Item(5, "Fifth"),
                new Item(4, "Fourth"),
                new Item(2, "Second"),
                new Item(1, "First"));
        Collections.sort(items, Collections.reverseOrder());
        assertThat(
                items,
                is(expected)
        );
    }
}