package duke.parser;

import duke.command.CommandType;
import duke.task.MissingTaskLiteralException;

public class ParameterParser {
    private static final String BY_LITERAL = " /by ";
    private static final String AT_LITERAL = " /at ";
    private static final String ERROR_NOT_INTEGER = "Sorry but parameter entered is not a integer\n";
    private static final String ERROR_NOT_IN_RANGE = "Sorry but parameter entered is not within range of list\n";

    private final CommandType commandType;
    private final String userInput;
    private ParameterData parameterData;

    public ParameterParser(CommandType commandType, String userInput) {
        this.commandType = commandType;
        this.userInput = userInput;
        parameterData = null;
    }

    public ParameterData processParameters() {
        try {
            switch (commandType) {
            case DONE:
            case DELETE:
                getTaskNumber();
            case DEADLINE:
            case EVENT:
                splitUserInput();
            case TODO:
                setTodoParameter();
            default:
                // todo
            }
        } catch (MissingTaskLiteralException e) {
            printMissingLiteral(e.getMessage());
        } catch (NumberFormatException e) {
            printTaskDoneNotInteger();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            printTaskDoneNotInRange();
        }

        return parameterData;
    }

    /**  splits user input into descriptions and date/time */
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

    /** returns the index of the task in the tasklist */
    private void getTaskNumber() throws NumberFormatException {
        parameterData =  new ParameterData(Integer.parseInt(userInput) - 1);
    }

    private void setTodoParameter() {
        parameterData = new ParameterData(userInput);
    }

    private void printMissingLiteral(String literal) {
        System.out.println("Command is missing the" + literal + "literal\n");
    }

    private void printTaskDoneNotInteger() {
        System.out.println(ERROR_NOT_INTEGER);
    }

    private void printTaskDoneNotInRange() {
        System.out.println(ERROR_NOT_IN_RANGE);
    }
}
