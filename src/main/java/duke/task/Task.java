package duke.task;

import duke.storage.FileWritable;

/**
 * Represents a base of a task object, which has description of the task and checks
 * if the task is done or not.
 */
public abstract class Task implements FileWritable {
    private static final String ICON_TICK = "\u2713";
    private static final String ICON_CROSS = "\u2718";
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

    /**
     * Sets the task done boolean to the parameter value.
     *
     * @param isDone Boolean status of the task.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
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
}
