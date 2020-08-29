public class Deadline extends Task {
    private static final String DEADLINE_TAG = "[D]";
    private static final String BY_OPEN_TEXT = "(by: ";
    private static final String BY_CLOSE_TEXT = ")";

    protected final String by;

    public Deadline(String[] details) {
        super(details[0]);
        by = details[1];
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return DEADLINE_TAG + super.toString() + BY_OPEN_TEXT + by + BY_CLOSE_TEXT;
    }
}
