public class Todo extends Task {
    private static final String TODO_TAG = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TODO_TAG + super.toString();
    }
}
