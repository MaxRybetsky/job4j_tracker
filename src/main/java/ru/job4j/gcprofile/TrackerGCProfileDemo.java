package ru.job4j.gcprofile;

import ru.job4j.tracker.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class TrackerGCProfileDemo {
    public static void main(String[] args) throws InterruptedException {
        int n = new Scanner(System.in).nextInt();
        String[] answers = fillAnswers(n);
        Output out = new ConsoleOutput();
        Input input = new StubInput(answers);
        UserAction[] actions = {
                new CreateAction(out),
                new DeleteAction(out),
                new ShowAction(out),
                new ExitAction()
        };
        Store tracker = new MemTracker();
        CustomStartUI startUI = new CustomStartUI(out);
        startUI.init(input, tracker, Arrays.asList(actions));
    }

    private static String[] fillAnswers(int n) {
        String[] answers = new String[n];
        fillCreateAnswers(0, answers.length / 4, answers);
        fillShowAnswers(answers.length / 4, answers.length / 4 + 2, answers);
        fillDeleteAnswers(answers.length / 4 + 2, answers.length / 2, answers);
        fillCreateAnswers(answers.length / 2, answers.length - 2, answers);
        fillShowAnswers(answers.length - 2, answers.length - 1, answers);
        fillExitAnswers(answers.length - 1, answers.length, answers);
        return answers;
    }

    private static void fillCreateAnswers(int start, int end, String[] answers) {
        Objects.checkFromToIndex(start, end, answers.length);
        for (int i = start; i < end; i++) {
            if (i % 2 == 0) {
                answers[i] = "0";
            } else {
                answers[i] = "Name " + i;
            }
        }
    }

    private static void fillDeleteAnswers(int start, int end, String[] answers) {
        Objects.checkFromToIndex(start, end, answers.length);
        for (int i = start; i < end; i++) {
            if (i % 2 == 0) {
                answers[i] = "1";
            } else {
                answers[i] = i - start + "";
            }
        }
    }

    private static void fillShowAnswers(int start, int end, String[] answers) {
        Objects.checkFromToIndex(start, end, answers.length);
        for (int i = start; i < end; i++) {
            answers[i] = "2";
        }
    }

    private static void fillExitAnswers(int start, int end, String[] answers) {
        Objects.checkFromToIndex(start, end, answers.length);
        Objects.checkFromToIndex(start, end, answers.length);
        for (int i = start; i < end; i++) {
            answers[i] = "3";
        }
    }

}
