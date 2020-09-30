package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.IllegalCommandException;
import duke.command.ListCommand;
import duke.command.FindCommand;

import duke.task.MissingTaskLiteralException;
import duke.task.TaskManager;
import duke.task.TaskType;

import duke.ui.CommandUi;

/**
 * Represents the process of extracting out the commands, parameters and executing them.
 */
public class CommandParser {
    private static final int INDEX_AFTER_DONE = 5;
    private static final int INDEX_AFTER_TODO = 5;
    private static final int INDEX_AFTER_DEADLINE = 9;
    private static final int INDEX_AFTER_EVENT = 6;
    private static final int INDEX_AFTER_DELETE = 7;
    private static final int INDEX_AFTER_FIND = 5;
    private static final int INDEX_AFTER_LIST = 5;
    private static final int LIST_LENGTH = 4;

    private static final String COMMAND_WORD_BYE = "bye";
    private static final String COMMAND_WORD_LIST = "list";
    private static final String COMMAND_WORD_DONE = "done";
    private static final String COMMAND_WORD_TODO = "todo";
    private static final String COMMAND_WORD_DEADLINE = "deadline";
    private static final String COMMAND_WORD_EVENT = "event";
    private static final String COMMAND_WORD_DELETE = "delete";
    private static final String COMMAND_WORD_FIND = "find";

    private final String userInput;
    private final TaskManager taskManager;
    private final CommandUi commandUi;

    private String parameters;
    private CommandType commandType;
    private ParameterData parameterData;

    public CommandParser(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
        commandType = null;
        parameters = null;
        parameterData = null;
        commandUi = new CommandUi();
    }

    /**
     * Takes in user input and converts them into understandable command and
     * parameters for the command class to execute.
     * If user input has no associated commands, inform user of invalid command.
     * If user input has invalid parameters, inform user of incorrect parameters.
     * If user input does not have the required literals, inform user of the missing literals.
     * If user input has inconvertible string to integer for done and delete, inform user of the error.
     *
     * @return One of the commandType enums.
     */
    public CommandType parseCommand() {
        try {
            extractCommand();
            extractParameters();
            parameterData = new ParameterParser(commandType, parameters).processParameters();
            executeCommand();
        } catch (IllegalCommandException e) {
            commandUi.printInvalidCommand();
        } catch (IndexOutOfBoundsException e) {
            commandUi.printInvalidParameters();
        } catch (MissingTaskLiteralException e) {
            commandUi.printMissingLiteral(e.getMessage());
        } catch (NumberFormatException e) {
            commandUi.printTaskDoneNotInteger();
        } catch (DateTimeFormatException e) {
            commandUi.printDateTimeFormatIncorrect();
        }

        return commandType;
    }

    /**
     * Initialises an instance of the command class, based on the different commandType enums.
     * with corresponding parameters and execute it.
     * If task number is out of range for delete and done command, inform user of the error.
     */
    private void executeCommand() {
        Command command = null;

        switch (commandType) {
        case LIST:
            command = new ListCommand(taskManager, parameterData.getDescription());
            break;
        case DONE:
            command = new DoneCommand(taskManager, parameterData.getTaskNumber());
            break;
        case TODO:
            command = new AddCommand(taskManager, TaskType.TODO, parameterData.getDescription());
            break;
        case DEADLINE:
            command = new AddCommand(taskManager,
                    TaskType.DEADLINE, parameterData.getDescription(),
                    parameterData.getDateTime());
            break;
        case EVENT:
            command = new AddCommand(taskManager,
                    TaskType.EVENT, parameterData.getDescription(),
                    parameterData.getDateTime());
            break;
        case DELETE:
            command = new DeleteCommand(taskManager, parameterData.getTaskNumber());
            break;
        case BYE:
            command = new ByeCommand(taskManager);
            break;
        case FIND:
            command = new FindCommand(taskManager,
                    parameterData.getFilterString(),
                    parameterData.getMatchDate(),
                    parameterData.getTimeSearch());
            break;
        default:
            commandUi.printNoCommandRan();
        }

        try {
            command.execute();
        } catch (IndexOutOfBoundsException e) {
            commandUi.printTaskDoneNotInRange();
        } catch (IllegalStateException e) {
            commandUi.printInvalidParameters();
        }
    }

    /**
     * Sets the commandType enum for the command based of the string input provided by user.
     *
     * @throws IllegalCommandException If no commandType is associated with user input.
     */
    private void extractCommand() throws IllegalCommandException {
        if (userInput.contentEquals(COMMAND_WORD_BYE)) {
            commandType = CommandType.BYE;
        } else if (userInput.startsWith(COMMAND_WORD_LIST)) {
            commandType = CommandType.LIST;
        } else if (userInput.startsWith(COMMAND_WORD_DONE)) {
            commandType = CommandType.DONE;
        } else if (userInput.startsWith(COMMAND_WORD_TODO)) {
            commandType = CommandType.TODO;
        } else if (userInput.startsWith(COMMAND_WORD_DEADLINE)) {
            commandType = CommandType.DEADLINE;
        } else if (userInput.startsWith(COMMAND_WORD_EVENT)) {
            commandType = CommandType.EVENT;
        } else if (userInput.startsWith(COMMAND_WORD_DELETE)) {
            commandType = CommandType.DELETE;
        } else if (userInput.startsWith(COMMAND_WORD_FIND)) {
            commandType = CommandType.FIND;
        } else {
            throw new IllegalCommandException();
        }
    }

    /**
     * Sets the parameters by discarding the command text in the user input.
     *
     * @throws IndexOutOfBoundsException If there are no more string after the command text.
     */
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
        case FIND:
            parameters = userInput.substring(INDEX_AFTER_FIND);
            break;
        case LIST:
            if (userInput.length() == LIST_LENGTH) {
                parameters = null;
            } else {
                parameters = userInput.substring(INDEX_AFTER_LIST);
            }
            break;
        case BYE:
            // Bye does not take any parameters
        default:
            parameters = null;
        }
    }
}
