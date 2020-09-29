package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.parser.DateTimeFormat;
import duke.parser.DateTimePrintable;
import duke.storage.FileWritable;

/**
 * Represents a task of type event. It has the description of the event,
 * an isDone attribute to check if event is done
 * and an at attribute to indicate the time for the event.
 */
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

    /* Constructs an instance when reading from a file */
    public Event(Boolean isDone, String description, LocalDateTime at) {
        super(isDone, description);
        this.at = at;
    }

    /**
     * Returns the string representation of the task for printing to output.
     *
     * @return Formatted task string.
     */
    @Override
    public String toString() {
        return EVENT_TAG_ENCLOSED + super.toString() + AT_OPEN_TEXT + formatDateTimeToString() + AT_CLOSE_TEXT;
    }

    /**
     * Returns the string representation of the task for writing to file.
     *
     * @return Formatted task string.
     */
    @Override
    public String convertToData() {
        return EVENT_TAG + super.convertToData() + FileWritable.SEPARATOR + formatDateTimeToFile();
    }

    /**
     * Formats LocalDateTime object into string format for output.
     *
     * @return Formatted date time string.
     */
    @Override
    public String formatDateTimeToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeFormat.FORMAT_16.getFormatStyle());
        return at.format(formatter);
    }

    /**
     * Formats LocalDateTime object into string format for data file.
     *
     * @return Formatted date time string.
     */
    @Override
    public String formatDateTimeToFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeFormat.FORMAT_2.getFormatStyle());
        return at.format(formatter);
    }

    /**
     * Converts LocalDateTime attribute at to a LocalDate object.
     *
     * @return LocalDate object.
     */
    @Override
    public LocalDate convertToDate() {
        return at.toLocalDate();
    }

    /**
     * Compares if two events are the same.
     * Compares the class type first before comparing the description and datetime.
     *
     * @param obj Any object class.
     * @return A boolean indicating if they are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Event event = (Event) obj;
        boolean isSameDesc = event.description.equals(this.description);
        boolean isSameDateTime = event.at.equals(this.at);

        return isSameDesc && isSameDateTime;
    }
}
