package ru.job4j.tracker;

public class StartUI {
    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
        System.out.println("Item was created!");
    }

    public static void printItems(Input input, Tracker tracker) {
        System.out.println("=== Print All Items ====");
        Item[] items = tracker.findAll();
        if (items.length == 0) {
            System.out.println("No items.");
        } else {
            for (int i = 0; i < items.length; i++) {
                System.out.println(items[i]);
            }
        }
    }

    public static void editItem(Input input, Tracker tracker) {
        System.out.println("=== Edit item ====");
        int id = input.askInt("Enter ID of item: ");
        System.out.println(tracker.findById(id));
        String newName = input.askStr("Enter new name: ");
        if (tracker.replace(id, new Item(newName))) {
            System.out.println("Item was changed!");
            System.out.println(tracker.findById(id));
        } else {
            System.out.println("Item wasn't changed, try again.");
        }
    }

    public static void deleteItem(Input input, Tracker tracker) {
        System.out.println("=== Delete Item ====");
        int id = input.askInt("Enter ID of item: ");
        if (tracker.delete(id)) {
            System.out.println("Item was deleted!");
        } else {
            System.out.println("Item wasn't deleted, try again.");
        }
    }

    public static void findItemById(Input input, Tracker tracker) {
        System.out.println("=== Find item by ID ====");
        int id = input.askInt("Enter ID of item: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("Item was found\n" + item.toString());
        } else {
            System.out.println("Item wasn't found, try again.");
        }
    }

    public static void findItemsByName(Input input, Tracker tracker) {
        System.out.println("=== Find items by name ====");
        String name = input.askStr("Enter key name: ");
        Item[] items = tracker.findByName(name);
        if (items.length == 0) {
            System.out.println("No items with this key name, try again.");
        } else {
            for (int i = 0; i < items.length; i++) {
                System.out.println(items[i]);
            }
        }
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu(input);
            int select = input.askInt("Select: ");
            if (select == 0) {
                StartUI.createItem(input, tracker);
            } else if (select == 1) {
                StartUI.printItems(input, tracker);
            } else if (select == 2) {
                StartUI.editItem(input, tracker);
            } else if (select == 3) {
                StartUI.deleteItem(input, tracker);
            } else if (select == 4) {
                StartUI.findItemById(input, tracker);
            } else if (select == 5) {
                StartUI.findItemsByName(input, tracker);
            } else if (select == 6) {
                run = false;
            }
        }
    }

    private void showMenu(Input input) {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        StartUI startUI = new StartUI();
        startUI.init(input, tracker);
    }
}