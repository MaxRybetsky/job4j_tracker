package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    private final Output out;

    public FindByIdAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find item by Id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Find item by ID ====");
        int id = input.askInt("Enter ID of item: ");
        Item item = tracker.findById(id);
        if (item != null) {
            out.println("Item was found" + System.lineSeparator() + item.toString());
        } else {
            out.println("Item wasn't found, try again.");
        }
        return true;
    }
}
