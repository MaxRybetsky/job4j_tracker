package ru.job4j.tracker;

public class EditAction implements UserAction {
    private final Output out;

    public EditAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Edit item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Edit item ====");
        int id = input.askInt("Enter ID of item: ");
        String newName = input.askStr("Enter new name: ");
        if (tracker.replace(id, new Item(newName))) {
            out.println("Item was changed!");
        } else {
            out.println("Item wasn't changed, try again.");
        }
        return true;
    }
}
