package duke.parser;

import duke.command.CommandType;
import duke.task.MissingTaskLiteralException;
import duke.ui.CommandUi;

/**
 * Represents the process of extracting out the parameters from user input.
 */
public class ParameterParser {
    private static final String BY_LITERAL = " /by ";
    private static final String AT_LITERAL = " /at ";

    private final CommandType commandType;
    private final String userInput;
    private ParameterData parameterData;
    private final CommandUi commandUi;

    public ParameterParser(CommandType commandType, String userInput) {
        this.commandType = commandType;
        this.userInput = userInput;
        parameterData = null;
        commandUi = new CommandUi();
    }

    /**
     * Returns a parameterData object after extracting the parameters.
     * ParameterData can be null.
     *
     * @return A parameterData object.
     * @throws NumberFormatException If parameter is not an integer.
     * @throws MissingTaskLiteralException If user input is missing task literal.
     */
    public ParameterData processParameters() throws NumberFormatException, MissingTaskLiteralException {
        try {
            switch (commandType) {
            case DONE:
            case DELETE:
                getTaskNumber();
                break;
            case DEADLINE:
            case EVENT:
                splitUserInput();
                break;
            case TODO:
                setTodoParameter();
                break;
            case BYE:
            case LIST:
                parameterData = null;
                break;
            default:
                throw new IllegalStateException();
            }
        } catch (NullPointerException e) {
            commandUi.printTaskDoneNotInRange();
        }

        return parameterData;
    }

    /**
     * Splits user input into descriptions and date/time.
     * Sets the parameterData based on the split parameters.
     *
     * @throws MissingTaskLiteralException If user input is missing task literal.
     * @throws IllegalStateException If there are commandType that are not deadline or event.
     */
    private void splitUserInput() throws MissingTaskLiteralException, IllegalStateException {
        int index;
        String description;
        String dateTime;

        switch (commandType) {
        case DEADLINE:
            if (!userInput.contains(BY_LITERAL)) {
                throw new MissingTaskLiteralException(BY_LITERAL);
            }
            index = userInput.indexOf(BY_LITERAL);
            break;
        case EVENT:
            if (!userInput.contains(AT_LITERAL)) {
                throw new MissingTaskLiteralException(AT_LITERAL);
            }
            index = userInput.indexOf(AT_LITERAL);
            break;
        default:
            throw new IllegalStateException();
        }

        description = userInput.substring(0, index);
        dateTime = userInput.substring(index + 5);
        parameterData = new ParameterData(description, dateTime);
    }

    /**
     * Returns the index of the task in tasks.
     *
     * @throws NumberFormatException If parameter is not an integer.
     */
    private void getTaskNumber() throws NumberFormatException {
        parameterData =  new ParameterData(Integer.parseInt(userInput) - 1);
    }

    private void setTodoParameter() {
        parameterData = new ParameterData(userInput);
    }
}
