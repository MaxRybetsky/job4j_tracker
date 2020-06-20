package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete Item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Enter ID of item: ");
        if (tracker.delete(id)) {
            System.out.println("Item was deleted!");
        } else {
            System.out.println("Item wasn't deleted, try again.");
        }
        return true;
    }
}
