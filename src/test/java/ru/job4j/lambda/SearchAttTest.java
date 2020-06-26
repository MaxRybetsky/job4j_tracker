package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchAttTest {
    @Test
    public void whenFilterSize() {
        List<Attachment> lst = Arrays.asList(
                new Attachment("bug smth", 12),
                new Attachment("A smth", 120),
                new Attachment("smth bug", 1200)
        );
        assertThat(
                SearchAtt.filterSize(lst, 100),
                is(Arrays.asList(
                        new Attachment("A smth", 120),
                        new Attachment("smth bug", 1200))
                )
        );
    }

    @Test
    public void whenFilterName() {
        List<Attachment> lst = Arrays.asList(
                new Attachment("bug smth", 12),
                new Attachment("A smth", 120),
                new Attachment("smth bug", 1200)
        );
        assertThat(
                SearchAtt.filterName(lst, "bug"),
                is(Arrays.asList(
                        new Attachment("bug smth", 12),
                        new Attachment("smth bug", 1200))
                )
        );
    }
}