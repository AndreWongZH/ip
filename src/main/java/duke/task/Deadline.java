package duke.task;

import duke.storage.FileWritable;

/**
 * Represents a task of type deadline. It has the description of the event,
 * an isDone attribute to check if event is done
 * and an by attribute to indicate the time for the deadline to be done by.
 */
public class Deadline extends Task {
    private static final String DEADLINE_TAG = "D";
    private static final String DEADLINE_TAG_ENCLOSED = "[D]";
    private static final String BY_OPEN_TEXT = " (by: ";
    private static final String BY_CLOSE_TEXT = ")";

    protected final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /* Constructs an instance when reading from a file */
    public Deadline(Boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    /**
     * Returns the string representation of the task for printing to output.
     *
     * @return Formatted task string.
     */
    @Override
    public String toString() {
        return DEADLINE_TAG_ENCLOSED + super.toString() + BY_OPEN_TEXT + by + BY_CLOSE_TEXT;
    }

    /**
     * Returns the string representation of the task for writing to file.
     *
     * @return Formatted task string.
     */
    @Override
    public String convertToData() {
        return DEADLINE_TAG + super.convertToData() + FileWritable.SEPARATOR + by;
    }
}
