package duke.ui;

import java.util.Scanner;

/**
 * Represents the printing of greetings to user.
 * Represents the receiving of inputs from user.
 *
 * Methods here are pretty self-explanatory.
 */
public class InputUi extends Ui {
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String GREET =
            " __\n" +
            "('')  Hello! I'm MK.II Titan with feelings.\n" +
            "// \\  What can I do for you?\n" +
            "|_/|";
    private static final String LOGO = " _    _      _\n" +
            "| |  | |    | |\n" +
            "| |  | | ___| | ___ ___  _ __ ___   ___\n" +
            "| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\\n" +
            "\\  /\\  /  __/ | (_| (_) | | | | | |  __/\n" +
            " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|";
    private static final String MESSAGE_GOODBYE =
            " __\n" +
            "( \")  Bye! I said with a disappointed look.\n" +
            "|| \\\n" +
            "|_||";

    private final Scanner in;

    public InputUi() {
        in = new Scanner(System.in);
    }

    /**
     * Returns the string the user has typed.
     * User input is trimmed of any whitespaces at the ends of the strings.
     * If user input is empty, then it returns null.
     *
     * @return User's input;
     */
    public String readUserInput() {
        String userInput =  in.nextLine().trim();
        if (hasEmptyString(userInput)) {
            return null;
        }

        return userInput;
    }

    public void greetUser() {
        out.println(LOGO);
        out.println(SEPARATOR);
        out.println(GREET);
        out.println(SEPARATOR);
    }

    public void printGoodbye() {
        out.println(SEPARATOR);
        out.println(MESSAGE_GOODBYE);
        out.println(SEPARATOR);
    }

    private boolean hasEmptyString(String userInput) {
        return userInput.isEmpty();
    }
}
