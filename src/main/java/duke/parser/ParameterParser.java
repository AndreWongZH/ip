package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.command.CommandType;
import duke.task.MissingTaskLiteralException;
import duke.task.TimeSearch;

/**
 * Represents the process of extracting out the parameters from user input.
 */
public class ParameterParser {
    private static final String BY_LITERAL = " /by ";
    private static final String AT_LITERAL = " /at ";
    private static final String ON_LITERAL = "/on ";
    private static final String AFTER_LITERAL = "/af ";
    private static final String BEFORE_LITERAL = "/bf ";
    private static final String TODAY = "today";
    private static final String TIME_OFFSET = " 0000";
    private static final int Literal_OFFSET = 4;
    private static final int PARAMETER_START_INDEX = 0;

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
            setUserInputAsParameter();
            break;
        case FIND:
            if (hasParameter()) {
                setFindParameter();
            }
            break;
        case LIST:
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
     * Check if user wants to find before, after or on the date specified.
     * Returned as a parameterData instance.
     *
     * @throws DateTimeFormatException If it is unable to parse user input as DateTime object.
     */
    private void setFindParameter() throws DateTimeFormatException {
        TimeSearch timeSearch;
        int index;
        if (userInput.contains(ON_LITERAL)) {
            index = userInput.indexOf(ON_LITERAL);
            timeSearch = TimeSearch.CURRENT;
        } else if (userInput.contains(AFTER_LITERAL)) {
            index = userInput.indexOf(AFTER_LITERAL);
            timeSearch = TimeSearch.FORWARD;
        } else if (userInput.contains(BEFORE_LITERAL)) {
            index = userInput.indexOf(BEFORE_LITERAL);
            timeSearch = TimeSearch.BACKWARD;
        } else {
            parameterData = new ParameterData(userInput, null, null);
            return;
        }
        String filterString = userInput.substring(PARAMETER_START_INDEX, index).trim();
        String dateTime = userInput.substring(index + Literal_OFFSET);

        if (dateTime.toLowerCase().contains(TODAY)) {
            parameterData = new ParameterData(filterString, LocalDate.now(), timeSearch);
            return;
        }

        // convert user's date input into a datetime input using a dummy time offset
        LocalDate matchDate = new DateTimeParser(dateTime + TIME_OFFSET).formatDate().toLocalDate();
        parameterData = new ParameterData(filterString, matchDate, timeSearch);
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

        description = userInput.substring(PARAMETER_START_INDEX, index);
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
