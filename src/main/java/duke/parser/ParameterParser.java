package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.command.CommandType;
import duke.task.MissingTaskLiteralException;

/**
 * Represents the process of extracting out the parameters from user input.
 */
public class ParameterParser {
    private static final String BY_LITERAL = " /by ";
    private static final String AT_LITERAL = " /at ";
    private static final String ON_LITERAL = " /on ";
    private static final int Literal_OFFSET = 5;
    private static final String TIME_OFFSET = " 0000";
    private static final String TODAY = "today";

    private final CommandType commandType;
    private final String userInput;
    private ParameterData parameterData;

    public ParameterParser(CommandType commandType, String userInput) {
        this.commandType = commandType;
        this.userInput = userInput;
        parameterData = null;
    }

    /**
     * Returns a parameterData object after extracting the parameters.
     * ParameterData can be null.
     *
     * @return A parameterData object.
     * @throws NumberFormatException If parameter is not an integer.
     * @throws MissingTaskLiteralException If user input is missing task literal.
     */
    public ParameterData processParameters() throws NumberFormatException, MissingTaskLiteralException, DateTimeFormatException {
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
        case FIND:
            setUserInputAsParameter();
            break;
        case LIST:
            if (hasParameter()) {
                setListParameter();
            }
            break;
        case BYE:
            parameterData = null;
            break;
        default:
            throw new IllegalStateException();
        }

        return parameterData;
    }

    /**
     * Extracts the date string from user input if /on is provided.
     * Parses the date string given into a LocalDateTime object.
     * Returned as a parameterData instance.
     *
     * @throws DateTimeFormatException If it is unable to parse user input as DateTime object.
     * @throws MissingTaskLiteralException If /on literal is not provided.
     */
    private void setListParameter() throws DateTimeFormatException, MissingTaskLiteralException {
        if (!userInput.contains(ON_LITERAL)) {
            throw new MissingTaskLiteralException(ON_LITERAL);
        }

        if (userInput.toLowerCase().contains(TODAY)) {
            parameterData = new ParameterData(LocalDate.now());
            return;
        }

        int index = userInput.indexOf(ON_LITERAL);
        // convert user's date input into a datetime input using a dummy time offset
        String dateTime = userInput.substring(index + Literal_OFFSET) + TIME_OFFSET;
        LocalDate matchDate = new DateTimeParser(dateTime).formatDate().toLocalDate();
        parameterData = new ParameterData(matchDate);
    }

    /**
     * Splits user input into descriptions and date/time.
     * Sets the parameterData based on the split parameters.
     *
     * @throws MissingTaskLiteralException If user input is missing task literal.
     * @throws IllegalStateException If there are commandType that are not deadline or event.
     */
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
        dateTime = new DateTimeParser(userInput.substring(index + Literal_OFFSET)).formatDate();
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

    private void setUserInputAsParameter() {
        parameterData = new ParameterData(userInput);
    }

    private boolean hasParameter() {
        return userInput != null;
    }
}
