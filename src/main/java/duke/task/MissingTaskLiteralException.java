package duke.task;

public class MissingTaskLiteralException extends Exception {
    public MissingTaskLiteralException(String literal) {
        super(literal);
    }
}
