/**
 * Handles the state and description of the task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        final String TICK = "\u2713";
        final String CROSS = "\u2718";
        return (isDone ? TICK : CROSS);
    }

    /**
     * Returns the status icon and task's description.
     *
     * @return Task information.
     */
    public String getTaskStatus() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Sets the task done boolean to the parameter value.
     *
     * @param done boolean status of the task.
     */
    public void setDone(boolean done) {
        isDone = done;
    }
}
