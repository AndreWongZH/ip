package duke.task;

/**
 * Represents the error when literals such as /by, /at and /on are missing in user's input.
 */
public class MissingTaskLiteralException extends Exception {
    public MissingTaskLiteralException(String literal) {
        super(literal);
    }
}
