package ru.job4j.tracker;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find items by name ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter key name: ");
        Item[] items = tracker.findByName(name);
        if (items.length == 0) {
            System.out.println("No items with this key name, try again.");
        } else {
            for (int i = 0; i < items.length; i++) {
                System.out.println(items[i]);
            }
        }
        return true;
    }
}
