package duke.task;

import java.time.LocalDate;

import duke.storage.FileWritable;

/**
 * Represents a base of a task object, which has description of the task and checks
 * if the task is done or not.
 */
public abstract class Task implements FileWritable {
    private static final String ICON_TICK = "Y";
    private static final String ICON_CROSS = "X";
    private static final String STATUS_OPEN_BRACKET = "[";
    private static final String STATUS_CLOSE_BRACKET = "] ";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /* Constructs an instance when reading from a file */
    public Task(Boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    abstract LocalDate convertToDate();

    /**
     * Returns the string representation of the task for printing to output.
     *
     * @return Formatted task string.
     */
    @Override
    public String toString() {
        return STATUS_OPEN_BRACKET + getStatusIcon() + STATUS_CLOSE_BRACKET + description;
    }

    /**
     * Returns the string representation of the task for writing to file.
     *
     * @return Formatted task string.
     */
    @Override
    public String convertToData() {
        return FileWritable.SEPARATOR + convertDoneToString() + FileWritable.SEPARATOR + description;
    }

    protected String getDescription() {
        return description;
    }

    /**
     * Toggles the task done boolean to the parameter value.
     */
    protected void toggleDone() {
        this.isDone = !isDone;
    }

    /**
     * Returns the correct icon based on task's isDone boolean.
     *
     * @return Tick/cross icon.
     */
    protected String getStatusIcon() {
        return (isDone ? ICON_TICK : ICON_CROSS);
    }

    /**
     * Converts boolean value of isDone to string "1" or "0".
     *
     * @return "1" or "0".
     */
    protected String convertDoneToString() {
        return (isDone ? "1" : "0");
    }

    public boolean getIsDone() {
        return isDone;
    }
}
