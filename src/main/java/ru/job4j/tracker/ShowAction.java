package ru.job4j.tracker;

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
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Print All Items ====");
        Item[] items = tracker.findAll();
        if (items.length == 0) {
            out.println("No items.");
        } else {
            for (int i = 0; i < items.length; i++) {
                out.println(items[i]);
            }
        }
        return true;
    }
}
