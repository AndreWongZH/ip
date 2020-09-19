package duke.command;

public class CommandManager {
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

    private static final String ERROR_NO_COMMAND_RAN = "No command executed\n";
    private static final String ERROR_COMMAND_NOT_VALID = "Command not valid, please try again :(\n";
    private static final String ERROR_INVALID_PARAMETERS = "Please enter parameters after the command\n";

    private static final int INDEX_AFTER_DONE = 5;
    private static final int INDEX_AFTER_TODO = 5;
    private static final int INDEX_AFTER_DEADLINE = 9;
    private static final int INDEX_AFTER_EVENT = 6;
    private static final int INDEX_AFTER_DELETE = 7;

    public static CommandEnum extractCommand(String userInput) throws IllegalCommandException {
        CommandEnum commandEnum;

        if (userInput.contentEquals("bye")) {
            commandEnum = CommandEnum.BYE;
        } else if (userInput.contentEquals("list")) {
            commandEnum = CommandEnum.LIST;
        } else if (userInput.startsWith("done")) {
            commandEnum = CommandEnum.DONE;
        } else if (userInput.startsWith("todo")) {
            commandEnum = CommandEnum.TODO;
        } else if (userInput.startsWith("deadline")) {
            commandEnum = CommandEnum.DEADLINE;
        } else if (userInput.startsWith("event")) {
            commandEnum = CommandEnum.EVENT;
        } else if (userInput.startsWith("delete")) {
            commandEnum = CommandEnum.DELETE;
        } else {
            throw new IllegalCommandException();
        }

        return commandEnum;
    }

    public static String extractParameters(CommandEnum commandEnum, String userInput) throws IndexOutOfBoundsException {
        String parameters;

        switch (commandEnum) {
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
        case DELETE:
            parameters = userInput.substring(INDEX_AFTER_DELETE);
            break;
        case LIST:
        default:
            parameters = null;
        }

        return parameters;
    }

    public static void printGreeting() {
        System.out.println(LOGO);
        System.out.println(GREET);
    }

    public static void printNoCommandRan() {
        System.out.println(ERROR_NO_COMMAND_RAN);
    }

    public static void printInvalidCommand() {
        System.out.println(ERROR_COMMAND_NOT_VALID);
    }

    public static void printInvalidParameters() {
        System.out.println(ERROR_INVALID_PARAMETERS);
    }
}
