package duke.task;

public class Event extends Task {
    private static final String EVENT_TAG = "[E]";
    private static final String AT_OPEN_TEXT = " (at: ";
    private static final String AT_CLOSE_TEXT = ")";

    private final String at;

    public Event(String[] details) {
        super(details[0]);
        at = details[1];
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return EVENT_TAG + super.toString() + AT_OPEN_TEXT + at + AT_CLOSE_TEXT;
    }
}
