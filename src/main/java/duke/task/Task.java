package duke.task;

public class Task {
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

    /**
     * Sets the task done boolean to the parameter value.
     *
     * @param done boolean status of the task.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return STATUS_OPEN_BRACKET + getStatusIcon() + STATUS_CLOSE_BRACKET + description;
    }

    protected String getStatusIcon() {
        return (isDone ? ICON_TICK : ICON_CROSS);
    }
}
