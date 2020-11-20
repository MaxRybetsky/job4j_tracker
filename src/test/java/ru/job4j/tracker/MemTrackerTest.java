package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class MemTrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Store memTracker = new MemTracker();
        Item item = new Item("test1");
        memTracker.add(item);
        Item result = memTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenAddNewItemsThenFindAll() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        Item item4 = new Item("test4");
        Item item5 = new Item("test5");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        memTracker.add(item4);
        memTracker.add(item5);
        assertThat(
                memTracker.findAll(),
                is(
                        Arrays.asList(item1, item2, item3, item4, item5)
                )
        );
    }

    @Test
    public void whenAddNewItemsThenFindByName() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        Item item4 = new Item("test1");
        Item item5 = new Item("test5");
        memTracker.add(item1);
        memTracker.add(item2);
        memTracker.add(item3);
        memTracker.add(item4);
        memTracker.add(item5);
        assertThat(
                memTracker.findByName("test1"),
                is(
                        Arrays.asList(item1, item4)
                )
        );
    }

    @Test
    public void whenAddNewItemsThenFindByNameAndNoSuchItems() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        memTracker.add(item1);
        memTracker.add(item2);
        List<Item> expected = new ArrayList<>();
        assertThat(
                memTracker.findByName("test"),
                is(expected)
        );
    }

    @Test
    public void whenAddNewItemsThenFindByIdAndNull() {
        Store memTracker = new MemTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        memTracker.add(item1);
        memTracker.add(item2);
        Item expected = null;
        assertThat(
                memTracker.findById(123),
                is(expected)
        );
    }

    @Test
    public void whenReplace() {
        Store memTracker = new MemTracker();
        Item bug = new Item();
        bug.setName("Bug");
        memTracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        memTracker.replace(id, bugWithDesc);
        assertThat(memTracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        Store memTracker = new MemTracker();
        Item bug = new Item();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        bug.setName("Bug");
        memTracker.add(bug);
        memTracker.add(item1);
        memTracker.add(item2);
        int id = item1.getId();
        memTracker.delete(id);
        assertThat(memTracker.findById(id), is(nullValue()));
    }
}