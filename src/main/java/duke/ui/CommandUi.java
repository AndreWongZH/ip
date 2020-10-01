package duke.ui;

/**
 * Represents the printing of command related errors to user output.
 *
 * Methods here are pretty self-explanatory.
 */
public class CommandUi extends Ui {
    private static final String ERROR_NO_COMMAND_RAN = "No command executed";
    private static final String ERROR_COMMAND_NOT_VALID = "Command not valid, please try again. :(";
    private static final String ERROR_SUGGEST_HELP_COMMAND = "Type help to look at the correct command format.";
    private static final String ERROR_INVALID_PARAMETERS = "Please enter valid parameters after the command";
    private static final String ERROR_NOT_INTEGER = "Sorry but parameter entered is not a integer";
    private static final String ERROR_NOT_IN_RANGE = "Sorry but parameter entered is not within range of list";
    private static final String ERROR_MISSING_LITERAL = "Command is missing the%sliteral";
    public static final String ERROR_WRONG_DATETIME_FORMAT = "Sorry but date time entered is in the wrong format";

    public void printInvalidCommand() {
        generateMultiLineTextBorder(ERROR_COMMAND_NOT_VALID, ERROR_SUGGEST_HELP_COMMAND);
    }

    public void printInvalidParameters() {
        generateTextBorder(ERROR_INVALID_PARAMETERS);
    }

    public void printNoCommandRan() {
        generateTextBorder(ERROR_NO_COMMAND_RAN);
    }

    public void printTaskDoneNotInteger() {
        generateTextBorder(ERROR_NOT_INTEGER);
    }

    public void printTaskDoneNotInRange() {
        generateTextBorder(ERROR_NOT_IN_RANGE);
    }

    public void printMissingLiteral(String literal) {
        generateTextBorder(String.format(ERROR_MISSING_LITERAL, literal));
    }

    public void printDateTimeFormatIncorrect() {
        generateTextBorder(ERROR_WRONG_DATETIME_FORMAT);
    }
}
