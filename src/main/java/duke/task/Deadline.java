package duke.task;

import duke.storage.FileWritable;

public class Deadline extends Task {
    private static final String DEADLINE_TAG = "D";
    private static final String DEADLINE_TAG_ENCLOSED = "[D]";
    private static final String BY_OPEN_TEXT = " (by: ";
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

    public Deadline(Boolean done, String description, String by) {
        super(done, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return DEADLINE_TAG_ENCLOSED + super.toString() + BY_OPEN_TEXT + by + BY_CLOSE_TEXT;
    }

    @Override
    public String convertToData() {
        return DEADLINE_TAG + super.convertToData() + FileWritable.SEPARATOR + by;
    }
}
