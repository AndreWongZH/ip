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
    private static final String LITERAL_BY = " /by ";
    private static final String LITERAL_AT = " /at ";
    private static final String LITERAL_ON = "/on ";
    private static final String LITERAL_AFTER = "/af ";
    private static final String LITERAL_BEFORE = "/bf ";
    private static final int LITERAL_OFFSET_VALUE = 4;

    private static final String TODAY = "today";
    private static final String TIME_OFFSET = " 0000";
    private static final int PARAMETER_START_INDEX = 0;
    private static final LocalDateTime DATE_TIME_TODAY = LocalDate.now().atTime(23, 59);

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
     * @throws DateTimeFormatException If user's input date is incorrect.
     */
    public ParameterData processParameters() throws NumberFormatException,
            MissingTaskLiteralException, DateTimeFormatException {
        switch (commandType) {
        case DONE:
        case DELETE:
            getTaskNumber();
            break;
        case DEADLINE:
        case EVENT:
            splitUserInput();
            break;
        case LIST:
        case TODO:
            setUserInputAsParameter();
            break;
        case FIND:
            if (hasParameter()) {
                setFindParameter();
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
     * Check if user wants to find before, after or on the date specified.
     * Returned as a parameterData instance.
     *
     * @throws DateTimeFormatException If it is unable to parse user input as DateTime object.
     */
    private void setFindParameter() throws DateTimeFormatException {
        TimeSearch timeSearch;
        int index;
        if (userInput.contains(LITERAL_ON)) {
            index = userInput.indexOf(LITERAL_ON);
            timeSearch = TimeSearch.CURRENT;
        } else if (userInput.contains(LITERAL_AFTER)) {
            index = userInput.indexOf(LITERAL_AFTER);
            timeSearch = TimeSearch.FORWARD;
        } else if (userInput.contains(LITERAL_BEFORE)) {
            index = userInput.indexOf(LITERAL_BEFORE);
            timeSearch = TimeSearch.BACKWARD;
        } else {
            parameterData = new ParameterData(userInput, null, null);
            return;
        }
        String filterString = userInput.substring(PARAMETER_START_INDEX, index).trim();
        String dateTime = userInput.substring(index + LITERAL_OFFSET_VALUE);

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
            if (!userInput.contains(LITERAL_BY)) {
                throw new MissingTaskLiteralException(LITERAL_BY);
            }
            index = userInput.indexOf(LITERAL_BY);
            break;
        case EVENT:
            if (!userInput.contains(LITERAL_AT)) {
                throw new MissingTaskLiteralException(LITERAL_AT);
            }
            index = userInput.indexOf(LITERAL_AT);
            break;
        default:
            throw new IllegalStateException();
        }

        description = userInput.substring(PARAMETER_START_INDEX, index);
        String dateTimeString = userInput.substring(index + LITERAL_OFFSET_VALUE);

        if (dateTimeString.toLowerCase().contains(TODAY)) {
            dateTime = DATE_TIME_TODAY;
        } else {
            dateTime = new DateTimeParser(dateTimeString).formatDate();
        }

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
