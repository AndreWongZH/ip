package duke.task;

import java.time.LocalDateTime;

import duke.storage.FileWritable;

public class Event extends Task {
    private static final String EVENT_TAG = "E";
    private static final String EVENT_TAG_ENCLOSED = "[E]";
    private static final String AT_OPEN_TEXT = " (at: ";
    private static final String AT_CLOSE_TEXT = ")";

    private final LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(Boolean done, String description, LocalDateTime at) {
        super(done, description);
        this.at = at;
    }

    @Override
    public String toString() {
        return EVENT_TAG_ENCLOSED + super.toString() + AT_OPEN_TEXT + at + AT_CLOSE_TEXT;
    }

    @Override
    public String convertToData() {
        return EVENT_TAG + super.convertToData() + FileWritable.SEPARATOR + at;
    }
}
