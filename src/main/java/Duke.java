import java.util.Scanner;

public class Duke {
    private static final String GREET = "____________________________________________________________\n" +
            " __\n" +
            "('')  Hello! I'm MK.II Titan with feelings.\n" +
            "// \\  What can I do for you?\n" +
            "|_/| \n" +
            "____________________________________________________________\n";

    private static final String END = "____________________________________________________________\n" +
            " __\n" +
            "( \")  Bye! I said with a disappointed look.\n" +
            "|| \\\n" +
            "|_||\n" +
            "____________________________________________________________";

    private static final String LOGO = " _    _      _                          \n" +
            "| |  | |    | |                         \n" +
            "| |  | | ___| | ___ ___  _ __ ___   ___ \n" +
            "| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\\n" +
            "\\  /\\  /  __/ | (_| (_) | | | | | |  __/\n" +
            " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|";

    private static final int INDEX_AFTER_DONE = 5;
    private static final int INDEX_AFTER_TODO = 5;
    private static final int INDEX_AFTER_DEADLINE = 9;
    private static final int INDEX_AFTER_EVENT = 6;

    public static void main(String[] args) {
        String userInput;
        boolean isActive = true;
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        printGreeting();
        while (isActive) {
            userInput = readUserInput(in);
            isActive = checkIfUserQuits(userInput);
            handleUserInput(taskManager, userInput);
        }
        printGoodbye();
    }

    private static void printGreeting() {
        System.out.println(LOGO);
        System.out.println(GREET);
    }

    private static void printGoodbye() {
        System.out.println(END);
    }

    private static String readUserInput(Scanner in) {
        return in.nextLine();
    }

    private static boolean checkIfUserQuits(String userInput) {
        return !userInput.equals("bye");
    }

    private static void handleUserInput(TaskManager taskManager, String userInput) {
        if (userInput.equals("list")) {
            taskManager.printAllTasks();
        } else if (userInput.contains("done")) {
            taskManager.setTaskDone(userInput.substring(INDEX_AFTER_DONE));
        } else if (userInput.contains("todo")) {
            taskManager.addTask(TaskType.TODO, userInput.substring(INDEX_AFTER_TODO));
        } else if (userInput.contains("deadline")) {
            taskManager.addTask(TaskType.DEADLINE, userInput.substring(INDEX_AFTER_DEADLINE));
        } else if (userInput.contains("event")) {
            taskManager.addTask(TaskType.EVENT, userInput.substring(INDEX_AFTER_EVENT));
        }
    }
}
