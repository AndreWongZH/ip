package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the process of parsing datetime strings into datetime objects.
 */
public class DateTimeParser {
    private static final String OLD_AM = "am";
    private static final String OLD_PM = "pm";
    private static final String NEW_AM = "AM";
    private static final String NEW_PM = "PM";

    private String dateTimeInput;
    LocalDateTime dateTime;
    boolean isMatchFormat;

    public DateTimeParser(String dateTimeInput) {
        this.dateTimeInput = dateTimeInput.toLowerCase();
        dateTime = null;
        isMatchFormat = false;
    }

    /**
     * Converts datetime string into datetime objects.
     *
     * @return LocalDateTime object.
     * @throws DateTimeFormatException If it is unable to parse datetime string into datetime objects.
     */
    public LocalDateTime formatDate() throws DateTimeFormatException {
        sanitizeInput();
        fitInputToFormat();
        checkFormatIsMatched();

        return dateTime;
    }

    private void sanitizeInput() {
        sanitizeMonth();
        sanitizeAmPm();
    }

    /* Replaces user AM and PM to upper case for parsing */
    private void sanitizeAmPm() {
        dateTimeInput = dateTimeInput.replace(OLD_AM, NEW_AM);
        dateTimeInput = dateTimeInput.replace(OLD_PM, NEW_PM);
    }

    /* Replaces user entered month into correct capitalized format for parsing */
    private void sanitizeMonth() {
        for (Month month : Month.values()) {
            dateTimeInput = dateTimeInput.replace(month.getOldInput(), month.getNewInput());
        }
    }

    /* Checks which format the userinput's string matches */
    private void fitInputToFormat() {
        for (DateTimeFormat format : DateTimeFormat.values()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format.getFormatStyle());
                dateTime = LocalDateTime.parse(dateTimeInput, formatter);
                isMatchFormat = true;
                break;
            } catch (DateTimeParseException ignored) {
                /*
                   This is purposely left empty because this function has to loop through all the possible
                   DateTime formats.
                   The checkFormatIsMatch function is responsible of throwing DateTimeFormatException
                   if there is no format matched.
                */
            }
        }
    }

    /* Throws exception here because user input does not match any of the formats */
    private void checkFormatIsMatched() throws DateTimeFormatException {
        if (!isMatchFormat) {
            throw new DateTimeFormatException();
        }
    }
}
