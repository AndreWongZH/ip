package duke.task;

/**
 * Represents a task of type todo. It has the description of the todo,
 * an isDone attribute to check if the todo is done.
 */
public class Todo extends Task {
    private static final String TODO_TAG = "T";
    private static final String TODO_TAG_ENCLOSED = "[T]";

    public Todo(String description) {
        super(description);
    }

    /* Constructs an instance when reading from a file */
    public Todo(Boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns the string representation of the task for printing to output.
     *
     * @return Formatted task string.
     */
    @Override
    public String toString() {
        return TODO_TAG_ENCLOSED + super.toString();
    }

    /**
     * Returns the string representation of the task for writing to file.
     *
     * @return Formatted task string.
     */
    @Override
    public String convertToData() {
        return TODO_TAG + super.convertToData();
    }
}
