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
        Command command;
        String parameters;
        boolean isActive = true;
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        printGreeting();
        while (isActive) {
            try {
                userInput = readUserInput(in);
                command = extractCommand(userInput);
                parameters = extractParameters(command, userInput);
                isActive = checkIfUserQuits(userInput);
                handleUserInput(taskManager, command, parameters);
            } catch (IllegalCommandException e) {
                printInvalidCommand();
            } catch (IndexOutOfBoundsException e) {
                printInvalidParameters();
            }
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

    private static void printNoCommandRan() {
        System.out.println("No Command executed");
    }

    private static void printInvalidCommand() {
        System.out.println("Command not valid, please try again :(\n");
    }

    private static void printInvalidParameters() {
        System.out.println("Please enter parameters after the command\n");
    }

    private static String readUserInput(Scanner in) {
        return in.nextLine();
    }

    private static boolean checkIfUserQuits(String userInput) {
        return !userInput.equals("bye");
    }

    private static void handleUserInput(TaskManager taskManager, Command command, String parameters) {
        switch (command) {
        case LIST:
            taskManager.printAllTasks();
            break;
        case DONE:
            taskManager.setTaskDone(parameters);
            break;
        case TODO:
            taskManager.addTask(TaskType.TODO, parameters);
            break;
        case DEADLINE:
            taskManager.addTask(TaskType.DEADLINE, parameters);
            break;
        case EVENT:
            taskManager.addTask(TaskType.EVENT, parameters);
            break;
        default:
            printNoCommandRan();
        }
    }

    private static Command extractCommand(String userInput) throws IllegalCommandException {
        Command command;

        if (userInput.equals("list")) {
            command = Command.LIST;
        } else if (userInput.contains("done")) {
            command = Command.DONE;
        } else if (userInput.contains("todo")) {
            command = Command.TODO;
        } else if (userInput.contains("deadline")) {
            command = Command.DEADLINE;
        } else if (userInput.contains("event")) {
            command = Command.EVENT;
        } else {
            throw new IllegalCommandException();
        }

        return command;
    }

    private static String extractParameters(Command command, String userInput) throws IndexOutOfBoundsException {
        String parameters;

        switch (command) {
        case DONE:
            parameters = userInput.substring(INDEX_AFTER_DONE);
            break;
        case TODO:
            parameters = userInput.substring(INDEX_AFTER_TODO);
            break;
        case DEADLINE:
            parameters = userInput.substring(INDEX_AFTER_DEADLINE);
            break;
        case EVENT:
            parameters = userInput.substring(INDEX_AFTER_EVENT);
            break;
        case LIST:
        default:
            parameters = null;
        }

        return parameters;
    }
}
