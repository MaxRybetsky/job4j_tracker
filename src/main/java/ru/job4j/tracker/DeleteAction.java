package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete Item ====");
        int id = input.askInt("Enter ID of item: ");
        if (tracker.delete(id)) {
            out.println("Item was deleted!");
        } else {
            out.println("Item wasn't deleted, try again.");
        }
        return true;
    }
}
