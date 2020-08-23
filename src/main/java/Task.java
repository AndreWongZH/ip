public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        // return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getTaskStatus() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
