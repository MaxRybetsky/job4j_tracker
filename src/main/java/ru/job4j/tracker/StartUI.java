package ru.job4j.tracker;

import java.util.Arrays;
import java.util.List;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Store tracker, List<UserAction> userActions) {
        boolean run = true;
        while (run) {
            this.showMenu(userActions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= userActions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (userActions.size() - 1));
                continue;
            }
            run = userActions.get(select).execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu");
        int i = 0;
        for (UserAction action : actions) {
            out.println(i + ". " + action.name());
            i++;
        }
    }

    public static void main(String[] args) {
        Output out = new ConsoleOutput();
        Input input = new ValidateInput(out,
                new ConsoleInput()
        );
        try (Store tracker = new SqlTracker()) {
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
            startUI.init(input, tracker, Arrays.asList(actions));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}