package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class StartUITest {
    @Test
    public void whenCreateItem() {
        UserAction[] actions = {
                new CreateAction(),
                new ExitAction()
        };
        Input input = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker, actions);
        assertThat(
                tracker.findAll()[0].getName(),
                is("Item name")
        );
    }

    @Test
    public void whenEditItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        UserAction[] actions = {
                new EditAction(),
                new ExitAction()
        };
        Input input = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        new StartUI().init(input, tracker, actions);
        assertThat(
                tracker.findById(item.getId()).getName(),
                is(replacedName)
        );
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleting item"));
        UserAction[] actions = {
                new DeleteAction(),
                new ExitAction()
        };
        Input input = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        new StartUI().init(input, tracker, actions);
        assertNull(
                tracker.findById(item.getId())
        );
    }
}