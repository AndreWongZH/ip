package duke.ui;

import java.util.Scanner;

public class InputUi extends Ui {
    private static final String GREET = "____________________________________________________________\n" +
            " __\n" +
            "('')  Hello! I'm MK.II Titan with feelings.\n" +
            "// \\  What can I do for you?\n" +
            "|_/|\n" +
            "____________________________________________________________\n";


    private static final String LOGO = " _    _      _\n" +
            "| |  | |    | |\n" +
            "| |  | | ___| | ___ ___  _ __ ___   ___\n" +
            "| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\\n" +
            "\\  /\\  /  __/ | (_| (_) | | | | | |  __/\n" +
            " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|";

    private static final String MESSAGE_GOODBYE = "____________________________________________________________\n" +
            " __\n" +
            "( \")  Bye! I said with a disappointed look.\n" +
            "|| \\\n" +
            "|_||\n" +
            "____________________________________________________________";

    private final Scanner in;

    public InputUi() {
        in = new Scanner(System.in);
    }

    public String readUserInput() {
        String userInput =  in.nextLine().trim();
        if (hasEmptyString(userInput)) {
            return null;
        }

        return userInput;
    }

    public void greetUser() {
        out.println(LOGO);
        out.println(GREET);
    }

    public void printGoodbye() {
        out.println(MESSAGE_GOODBYE);
    }

    private boolean hasEmptyString(String userInput) {
        return userInput.isEmpty();
    }
}
