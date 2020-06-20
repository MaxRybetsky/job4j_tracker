package ru.job4j.tracker;

public class FindByNameAction implements UserAction {
    private final Output out;

    public FindByNameAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find items by name";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Find items by name ====");
        String name = input.askStr("Enter key name: ");
        Item[] items = tracker.findByName(name);
        if (items.length == 0) {
            out.println("No items with this key name, try again.");
        } else {
            for (int i = 0; i < items.length; i++) {
                out.println(items[i]);
            }
        }
        return true;
    }
}
