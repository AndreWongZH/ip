public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        final String TICK = "\u2713";
        final String CROSS = "\u2718";
        return (isDone ? TICK : CROSS);
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
        return "[" + getStatusIcon() + "] " + description;
    }
}
