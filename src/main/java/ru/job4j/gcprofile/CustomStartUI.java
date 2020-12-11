package ru.job4j.gcprofile;

import ru.job4j.tracker.*;

import java.util.List;

public class CustomStartUI {
    private final Output out;

    public CustomStartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Store tracker, List<UserAction> userActions) throws InterruptedException {
        boolean run = true;
        while (run) {
            showMenu(userActions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= userActions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (userActions.size() - 1));
                continue;
            }
            run = userActions.get(select).execute(input, tracker);
            Thread.sleep(500);
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
}
