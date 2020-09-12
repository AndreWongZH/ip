package duke.task;

public class Todo extends Task {
    private static final String TODO_TAG = "[T]";

    public Todo(String description) {
        super(description);
    }

    public Todo(Boolean done, String description) {
        super(done, description);
    }

    @Override
    public String toString() {
        return TODO_TAG + super.toString();
    }
}
