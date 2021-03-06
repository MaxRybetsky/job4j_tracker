package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class StartUITest {
    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        UserAction[] actions = {
                new CreateAction(out),
                new ExitAction()
        };
        Input input = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        Store memTracker = new MemTracker();
        new StartUI(out).init(input, memTracker, Arrays.asList(actions));
        assertThat(
                memTracker.findAll().get(0).getName(),
                is("Item name")
        );
    }

    @Test
    public void whenEditItem() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        UserAction[] actions = {
                new EditAction(out),
                new ExitAction()
        };
        Input input = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        new StartUI(out).init(input, memTracker, Arrays.asList(actions));
        assertThat(
                memTracker.findById(item.getId()).getName(),
                is(replacedName)
        );
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Deleting item"));
        UserAction[] actions = {
                new DeleteAction(out),
                new ExitAction()
        };
        Input input = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        new StartUI(out).init(input, memTracker, Arrays.asList(actions));
        assertNull(
                memTracker.findById(item.getId())
        );
    }

    @Test
    public void whenFindAll() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item1 = memTracker.add(new Item("Item1"));
        Item item2 = memTracker.add(new Item("Item2"));
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        UserAction[] actions = {
                new ShowAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, memTracker, Arrays.asList(actions));
        String ln = System.lineSeparator();
        String expected = "Menu" + ln
                + "0. Show all items" + ln
                + "1. Exit Program" + ln
                + "=== Print All Items ====" + ln
                + item1.toString() + ln
                + item2.toString() + ln
                + "Menu" + ln
                + "0. Show all items" + ln
                + "1. Exit Program" + ln;
        assertThat(
                out.toString(),
                is(expected)
        );
    }

    @Test
    public void whenFindById() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new FindByIdAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, memTracker, Arrays.asList(actions));
        String ln = System.lineSeparator();
        String expected = "Menu" + ln
                + "0. Find item by Id" + ln
                + "1. Exit Program" + ln
                + "=== Find item by ID ====" + ln
                + "Item was found" + ln
                + item.toString() + ln
                + "Menu" + ln
                + "0. Find item by Id" + ln
                + "1. Exit Program" + ln;
        assertThat(
                out.toString(),
                is(expected)
        );
    }

    @Test
    public void whenFindByName() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Item"));
        Input in = new StubInput(
                new String[]{"0", item.getName(), "1"}
        );
        UserAction[] actions = {
                new FindByNameAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, memTracker, Arrays.asList(actions));
        String ln = System.lineSeparator();
        String expected = "Menu" + ln
                + "0. Find items by name" + ln
                + "1. Exit Program" + ln
                + "=== Find items by name ====" + ln
                + item.toString() + ln
                + "Menu" + ln
                + "0. Find items by name" + ln
                + "1. Exit Program" + ln;
        assertThat(
                out.toString(),
                is(expected)
        );
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0"}
        );
        MemTracker memTracker = new MemTracker();
        UserAction[] actions = {
                new ExitAction()
        };
        new StartUI(out).init(in, memTracker, Arrays.asList(actions));
        assertThat(
                out.toString(),
                is(
                        "Menu" + System.lineSeparator()
                                + "0. Exit Program" + System.lineSeparator()
                )
        );
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"5", "0"}
        );
        MemTracker memTracker = new MemTracker();
        UserAction[] actions = {
                new ExitAction()
        };
        new StartUI(out).init(in, memTracker, Arrays.asList(actions));
        assertThat(
                out.toString(),
                is(
                        String.format(
                                "Menu%n"
                                        + "0. Exit Program%n"
                                        + "Wrong input, you can select: 0 .. 0%n"
                                        + "Menu%n"
                                        + "0. Exit Program%n"
                        )
                ));
    }

    @Test
    public void whenCorrectInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"5"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu: ");
        assertThat(selected, is(5));
    }

    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu: ");
        assertThat(selected, is(1));
    }

    @Test
    public void whenInvalidInputThenTestOutput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"four", "4"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu: ");
        System.out.println(out);
        assertThat(out.toString(), is(String.format("Please enter validate data again.%n")));
    }

    @Test
    public void whenDBAddTest() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"four", "4"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu: ");
        System.out.println(out);
        assertThat(out.toString(), is(String.format("Please enter validate data again.%n")));
    }
}