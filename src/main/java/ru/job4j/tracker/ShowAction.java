package ru.job4j.tracker;

import java.util.List;

public class ShowAction implements UserAction {
    private final Output out;

    public ShowAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show all items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Print All Items ====");
        List<Item> items = tracker.findAll();
        if (items.size() == 0) {
            out.println("No items.");
        } else {
            for (Item item : items) {
                out.println(item);
            }
        }
        return true;
    }
}
