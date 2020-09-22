package duke.task;

import java.time.LocalDate;

import duke.storage.FileWritable;

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

    public Task(Boolean done, String description) {
        this.description = description;
        isDone = done;
    }

    abstract LocalDate convertToDate();

    @Override
    public String toString() {
        return STATUS_OPEN_BRACKET + getStatusIcon() + STATUS_CLOSE_BRACKET + description;
    }

    @Override
    public String convertToData() {
        return FileWritable.SEPARATOR + convertDoneToString() + FileWritable.SEPARATOR + description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets the task done boolean to the parameter value.
     *
     * @param isDone boolean status of the task.
     */
    protected void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return (isDone ? ICON_TICK : ICON_CROSS);
    }

    protected String convertDoneToString() {
        if (isDone) {
            return "1";
        }
        return "0";
    }
}
