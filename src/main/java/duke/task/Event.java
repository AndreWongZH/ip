package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.parser.DateTimeFormat;
import duke.parser.DateTimePrintable;
import duke.storage.FileWritable;

public class Event extends Task implements DateTimePrintable {
    public static final String EVENT_TAG = "E";
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
        return EVENT_TAG_ENCLOSED + super.toString() + AT_OPEN_TEXT + formatDateTimeToString() + AT_CLOSE_TEXT;
    }

    @Override
    public String convertToData() {
        return EVENT_TAG + super.convertToData() + FileWritable.SEPARATOR + formatDateTimeToFile();
    }

    @Override
    public String formatDateTimeToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeFormat.FORMAT_16.getFormatStyle());
        return at.format(formatter);
    }

    @Override
    public String formatDateTimeToFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeFormat.FORMAT_2.getFormatStyle());
        return at.format(formatter);
    }
}
