package duke.parser;

import java.time.LocalDateTime;

import duke.command.CommandType;
import duke.task.MissingTaskLiteralException;
import duke.ui.CommandUi;

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

    public ParameterData processParameters() throws NumberFormatException, MissingTaskLiteralException, DateTimeFormatException {
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

    /**  splits user input into descriptions and date/time */
    private void splitUserInput() throws MissingTaskLiteralException, IllegalStateException, DateTimeFormatException {
        int index;
        String description;
        LocalDateTime dateTime;

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
        dateTime = new DateTimeParser(userInput.substring(index + 5)).formatDate();
        parameterData = new ParameterData(description, dateTime);
    }

    /** returns the index of the task in tasks */
    private void getTaskNumber() throws NumberFormatException {
        parameterData =  new ParameterData(Integer.parseInt(userInput) - 1);
    }

    private void setTodoParameter() {
        parameterData = new ParameterData(userInput);
    }
}
