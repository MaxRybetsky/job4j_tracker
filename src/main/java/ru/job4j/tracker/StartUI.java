package ru.job4j.tracker;

public class StartUI {
    public void init(Input input, Tracker tracker, UserAction[] userActions) {
        boolean run = true;
        while (run) {
            this.showMenu(userActions);
            int select = input.askInt("Select: ");
            if (select > 0 && select < userActions.length) {
                run = userActions[select].execute(input, tracker);
            }
        }
    }

    private void showMenu(UserAction[] actions) {
        System.out.println("Menu");
        for (int i = 0; i < actions.length; i++) {
            System.out.println(i + ". " + actions[i].name());
        }
    }

    public static void main(String[] args) {
        UserAction[] actions = {
                new CreateAction(),
                new ShowAction(),
                new EditAction(),
                new DeleteAction(),
                new FindByIdAction(),
                new FindByNameAction(),
                new ExitAction()
        };
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        StartUI startUI = new StartUI();
        startUI.init(input, tracker, actions);
    }
}