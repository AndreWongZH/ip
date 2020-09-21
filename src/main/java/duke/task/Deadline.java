package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.parser.DateTimeFormat;
import duke.parser.DateTimePrintable;
import duke.storage.FileWritable;

public class Deadline extends Task implements DateTimePrintable {
    public static final String DEADLINE_TAG = "D";
    private static final String DEADLINE_TAG_ENCLOSED = "[D]";
    private static final String BY_OPEN_TEXT = " (by: ";
    private static final String BY_CLOSE_TEXT = ")";

    protected final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(Boolean done, String description, LocalDateTime by) {
        super(done, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return DEADLINE_TAG_ENCLOSED + super.toString() + BY_OPEN_TEXT + formatDateTimeToString() + BY_CLOSE_TEXT;
    }

    @Override
    public String convertToData() {
        return DEADLINE_TAG + super.convertToData() + FileWritable.SEPARATOR + formatDateTimeToFile();
    }

    @Override
    public String formatDateTimeToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeFormat.FORMAT_16.getFormatStyle());
        return by.format(formatter);
    }

    @Override
    public String formatDateTimeToFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeFormat.FORMAT_2.getFormatStyle());
        return by.format(formatter);
    }
}
