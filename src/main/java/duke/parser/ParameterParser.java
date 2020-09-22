package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.command.CommandType;
import duke.task.MissingTaskLiteralException;

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

    private boolean hasParameter() {
        return userInput != null;
    }

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
        dateTime = new DateTimeParser(userInput.substring(index + Literal_OFFSET)).formatDate();
        parameterData = new ParameterData(description, dateTime);
    }

    /** returns the index of the task in tasks */
    private void getTaskNumber() throws NumberFormatException {
        parameterData =  new ParameterData(Integer.parseInt(userInput) - 1);
    }

    private void setUserInputAsParameter() {
        parameterData = new ParameterData(userInput);
    }
}
