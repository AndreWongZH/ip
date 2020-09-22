package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.parser.DateTimeFormat;
import duke.parser.DateTimePrintable;
import duke.storage.FileWritable;

/**
 * Represents a task of type deadline. It has the description of the event,
 * an isDone attribute to check if event is done
 * and an by attribute to indicate the time for the deadline to be done by.
 */
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

    /* Constructs an instance when reading from a file */
    public Deadline(Boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
    }

    /**
     * Returns the string representation of the task for printing to output.
     *
     * @return Formatted task string.
     */
    @Override
    public String toString() {
        return DEADLINE_TAG_ENCLOSED + super.toString() + BY_OPEN_TEXT + formatDateTimeToString() + BY_CLOSE_TEXT;
    }

    /**
     * Returns the string representation of the task for writing to file.
     *
     * @return Formatted task string.
     */
    @Override
    public String convertToData() {
        return DEADLINE_TAG + super.convertToData() + FileWritable.SEPARATOR + formatDateTimeToFile();
    }

    /**
     * Formats LocalDateTime object into string format for output.
     *
     * @return Formatted date time string.
     */
    @Override
    public String formatDateTimeToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeFormat.FORMAT_16.getFormatStyle());
        return by.format(formatter);
    }

    /**
     * Formats LocalDateTime object into string format for data file.
     *
     * @return Formatted date time string.
     */
    @Override
    public String formatDateTimeToFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeFormat.FORMAT_2.getFormatStyle());
        return by.format(formatter);
    }

    /**
     * Converts LocalDateTime attribute by to a LocalDate object.
     *
     * @return LocalDate object.
     */
    @Override
    public LocalDate convertToDate() {
        return by.toLocalDate();
    }
}
