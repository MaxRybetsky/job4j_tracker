package ru.job4j.tracker;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Tracker tracker, UserAction[] userActions) {
        boolean run = true;
        while (run) {
            this.showMenu(userActions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= userActions.length) {
                out.println("Wrong input, you can select: 0 .. " + (userActions.length - 1));
                continue;
            }
            run = userActions[select].execute(input, tracker);
        }
    }

    private void showMenu(UserAction[] actions) {
        out.println("Menu");
        for (int i = 0; i < actions.length; i++) {
            out.println(i + ". " + actions[i].name());
        }
    }

    public static void main(String[] args) {
        Input input = new ValidateInput();
        Output out = new ConsoleOutput();
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(out),
                new ShowAction(out),
                new EditAction(out),
                new DeleteAction(out),
                new FindByIdAction(out),
                new FindByNameAction(out),
                new ExitAction()
        };
        StartUI startUI = new StartUI(out);
        startUI.init(input, tracker, actions);
    }
}