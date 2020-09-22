package duke.task;

import duke.storage.FileWritable;

/**
 * Represents a task of type event. It has the description of the event,
 * an isDone attribute to check if event is done
 * and an at attribute to indicate the time for the event.
 */
public class Event extends Task {
    private static final String EVENT_TAG = "E";
    private static final String EVENT_TAG_ENCLOSED = "[E]";
    private static final String AT_OPEN_TEXT = " (at: ";
    private static final String AT_CLOSE_TEXT = ")";

    private final String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /* Constructs an instance when reading from a file */
    public Event(Boolean isDone, String description, String at) {
        super(isDone, description);
        this.at = at;
    }

    /**
     * Returns the string representation of the task for printing to output.
     *
     * @return Formatted task string.
     */
    @Override
    public String toString() {
        return EVENT_TAG_ENCLOSED + super.toString() + AT_OPEN_TEXT + at + AT_CLOSE_TEXT;
    }

    /**
     * Returns the string representation of the task for writing to file.
     *
     * @return Formatted task string.
     */
    @Override
    public String convertToData() {
        return EVENT_TAG + super.convertToData() + FileWritable.SEPARATOR + at;
    }
}
