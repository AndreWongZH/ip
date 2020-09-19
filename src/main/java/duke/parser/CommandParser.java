package duke.parser;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.IllegalCommandException;
import duke.command.AddCommand;
import duke.command.ListCommand;
import duke.command.ByeCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;

import duke.task.TaskManager;
import duke.task.TaskType;

public class CommandParser {
    private static final String ERROR_NO_COMMAND_RAN = "No command executed\n";
    private static final String ERROR_COMMAND_NOT_VALID = "Command not valid, please try again :(\n";
    private static final String ERROR_INVALID_PARAMETERS = "Please enter parameters after the command\n";

    private static final int INDEX_AFTER_DONE = 5;
    private static final int INDEX_AFTER_TODO = 5;
    private static final int INDEX_AFTER_DEADLINE = 9;
    private static final int INDEX_AFTER_EVENT = 6;
    private static final int INDEX_AFTER_DELETE = 7;

    private final String userInput;
    private final TaskManager taskManager;
    private String parameters;
    private CommandType commandType;

    public CommandParser(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
        this.commandType = null;
        this.parameters = null;
    }

    public CommandType parseCommand() {
        try {
            extractCommand();
            extractParameters();
            executeCommand();
        } catch (IllegalCommandException e) {
            printInvalidCommand();
        } catch (IndexOutOfBoundsException e) {
            printInvalidParameters();
        }

        return commandType;
    }

    private void executeCommand() {
        Command command = null;

        switch (commandType) {
        case LIST:
            command = new ListCommand(taskManager);
            break;
        case DONE:
            command = new DoneCommand(taskManager, parameters);
            break;
        case TODO:
            command = new AddCommand(taskManager, TaskType.TODO, parameters);
            break;
        case DEADLINE:
            command = new AddCommand(taskManager, TaskType.DEADLINE, parameters);
            break;
        case EVENT:
            command = new AddCommand(taskManager, TaskType.EVENT, parameters);
            break;
        case DELETE:
            command = new DeleteCommand(taskManager, parameters);
            break;
        case BYE:
            command = new ByeCommand(taskManager);
            break;
        default:
            printNoCommandRan();
        }

        command.execute();
    }

    private void extractCommand() throws IllegalCommandException {
        if (userInput.contentEquals("bye")) {
            commandType = CommandType.BYE;
        } else if (userInput.contentEquals("list")) {
            commandType = CommandType.LIST;
        } else if (userInput.startsWith("done")) {
            commandType = CommandType.DONE;
        } else if (userInput.startsWith("todo")) {
            commandType = CommandType.TODO;
        } else if (userInput.startsWith("deadline")) {
            commandType = CommandType.DEADLINE;
        } else if (userInput.startsWith("event")) {
            commandType = CommandType.EVENT;
        } else if (userInput.startsWith("delete")) {
            commandType = CommandType.DELETE;
        } else {
            throw new IllegalCommandException();
        }
    }

    private void extractParameters() throws IndexOutOfBoundsException {
        switch (commandType) {
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
    }

    public void printInvalidCommand() {
        System.out.println(ERROR_COMMAND_NOT_VALID);
    }

    public void printInvalidParameters() {
        System.out.println(ERROR_INVALID_PARAMETERS);
    }

    public static void printNoCommandRan() {
        System.out.println(ERROR_NO_COMMAND_RAN);
    }
}
