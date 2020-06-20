package ru.job4j.tracker;

public class ShowAction implements UserAction {
    @Override
    public String name() {
        return "=== Print All Items ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item[] items = tracker.findAll();
        if (items.length == 0) {
            System.out.println("No items.");
        } else {
            for (int i = 0; i < items.length; i++) {
                System.out.println(items[i]);
            }
        }
        return true;
    }
}
