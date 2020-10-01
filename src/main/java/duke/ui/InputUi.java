package duke.ui;

import java.util.Scanner;

/**
 * Represents the printing of greetings to user.
 * Represents the receiving of inputs from user.
 *
 * Methods here are pretty self-explanatory.
 */
public class InputUi extends Ui {
    private static final String SEPARATOR_HEADING = "____________________________________________________________";
    private static final String SEPARATOR_INPUT = "------------------------------------------------------------";

    private static final String GREET_MESSAGE =
            " __\n" +
            "('')  Hello! I'm MK.II Titan with feelings.\n" +
            "// \\  What can I do for you?\n" +
            "|_/|";
    private static final String LOGO_MESSAGE = " _    _      _\n" +
            "| |  | |    | |\n" +
            "| |  | | ___| | ___ ___  _ __ ___   ___\n" +
            "| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\\n" +
            "\\  /\\  /  __/ | (_| (_) | | | | | |  __/\n" +
            " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|";
    private static final String GOODBYE_MESSAGE =
            " __\n" +
            "( \")  Bye! I said with a disappointed look.\n" +
            "|| \\\n" +
            "|_||  %d entries of tasks has been saved under ./data/duke.txt";
    private static final String INPUT_INFO_MESSAGE =
            "       ____________      ____________      ____________\n" +
            "______/ [T]: %-5s \\____/ [D]: %-5s \\____/ [E]: %-5s \\____\n" +
            "|| >>> ";
    private static final String HELP_INFO_MESSAGE = "Here are the available command formats:\n" +
            "   Add task:\n" +
            "       todo <TASK>\n" +
            "       deadline <TASK> /by <DATE>\n" +
            "       event <TASK> /at <DATE>\n" +
            "   Toggle task Done:\n" +
            "       done <INDEX>\n" +
            "   Delete a task:\n" +
            "       delete <INDEX>\n" +
            "   List out tasks:\n" +
            "       list (order)\n" +
            "       order can be \"asc\" \"desc\"\n" +
            "   Find tasks\n" +
            "       find (keyword) (/on <DATE>)\n" +
            "       find (keyword) (/bf <DATE>)\n" +
            "       find (keyword) (/af <DATE>)\n" +
            "   Exit program\n" +
            "       bye";

    private static final String MAX_TASK_VALUE_MESSAGE = ">9999";
    private static final int MAX_TASK_VALUE = 99999;


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
        out.println(LOGO_MESSAGE);
        out.println(SEPARATOR_HEADING);
        out.println(GREET_MESSAGE);
        out.println(SEPARATOR_HEADING);
    }

    public void printGoodbye(long totalCount) {
        out.println(SEPARATOR_HEADING);
        out.printf((GOODBYE_MESSAGE) + "%n", totalCount);
        out.println(SEPARATOR_HEADING);
    }

    public void printHelp() {
        out.println(SEPARATOR_HEADING);
        out.println(HELP_INFO_MESSAGE);
        out.println(SEPARATOR_HEADING);
    }

    /**
     * Prints out an interface for user to enter their commands in.
     * There is a top bar that also shows the number of todos, deadlines and events they have.
     *
     * @param todoCount Total number of todos in tasks.
     * @param deadlineCount Total number of deadlines in tasks.
     * @param eventCount Total number of events in tasks.
     */
    public void printInputCommand(long todoCount, long deadlineCount, long eventCount) {
        String todoStringCount = todoCount > MAX_TASK_VALUE
                ? MAX_TASK_VALUE_MESSAGE
                : String.valueOf(todoCount);
        String deadlineStringCount = deadlineCount > MAX_TASK_VALUE
                ? MAX_TASK_VALUE_MESSAGE
                : String.valueOf(deadlineCount);
        String eventStringCount = eventCount > MAX_TASK_VALUE
                ? MAX_TASK_VALUE_MESSAGE
                : String.valueOf(eventCount);

        out.printf(INPUT_INFO_MESSAGE, todoStringCount, deadlineStringCount, eventStringCount);
    }

    /**
     * Prints separator after user has entered a command.
     */
    public void printSeparator() {
        out.println(SEPARATOR_INPUT);
    }

    private boolean hasEmptyString(String userInput) {
        return userInput.isEmpty();
    }
}
