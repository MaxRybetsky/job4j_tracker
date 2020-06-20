package ru.job4j.tracker;

public class EditAction implements UserAction {
    @Override
    public String name() {
        return "=== Edit item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Enter ID of item: ");
        String newName = input.askStr("Enter new name: ");
        if (tracker.replace(id, new Item(newName))) {
            System.out.println("Item was changed!");
        } else {
            System.out.println("Item wasn't changed, try again.");
        }
        return true;
    }
}
