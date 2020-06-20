package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "=== Find item by ID ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Enter ID of item: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("Item was found\n" + item.toString());
        } else {
            System.out.println("Item wasn't found, try again.");
        }
        return true;
    }
}
