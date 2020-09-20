package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.IllegalCommandException;
import duke.command.ListCommand;

import duke.task.IllegalParameterException;
import duke.task.TaskManager;
import duke.task.TaskType;

import duke.ui.CommandUi;

public class CommandParser {
    private static final int INDEX_AFTER_DONE = 5;
    private static final int INDEX_AFTER_TODO = 5;
    private static final int INDEX_AFTER_DEADLINE = 9;
    private static final int INDEX_AFTER_EVENT = 6;
    private static final int INDEX_AFTER_DELETE = 7;

    private final String userInput;
    private final TaskManager taskManager;
    private final CommandUi commandUi;

    private String parameters;
    private CommandType commandType;
    private ParameterData parameterData;

    public CommandParser(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
        this.commandType = null;
        this.parameters = null;
        this.parameterData = null;
        this.commandUi = new CommandUi();
    }

    public CommandType parseCommand() {
        try {
            extractCommand();
            extractParameters();
            parameterData = new ParameterParser(commandType, parameters).processParameters();
            executeCommand();
        } catch (IllegalCommandException e) {
            commandUi.printInvalidCommand();
        } catch (IndexOutOfBoundsException | IllegalParameterException e) {
            commandUi.printInvalidParameters();
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
            command = new DoneCommand(taskManager, parameterData.getTaskNumber());
            break;
        case TODO:
            command = new AddCommand(taskManager, TaskType.TODO, parameterData.getDescription());
            break;
        case DEADLINE:
            command = new AddCommand(taskManager, TaskType.DEADLINE, parameterData.getDescription(), parameterData.getDateTime());
            break;
        case EVENT:
            command = new AddCommand(taskManager, TaskType.EVENT, parameterData.getDescription(), parameterData.getDateTime());
            break;
        case DELETE:
            command = new DeleteCommand(taskManager, parameterData.getTaskNumber());
            break;
        case BYE:
            command = new ByeCommand(taskManager);
            break;
        default:
            commandUi.printNoCommandRan();
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
}
