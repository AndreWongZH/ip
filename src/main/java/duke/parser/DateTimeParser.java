package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    private void sanitizeAmPm() {
        dateTimeInput = dateTimeInput.replace(OLD_AM, NEW_AM);
        dateTimeInput = dateTimeInput.replace(OLD_PM, NEW_PM);
    }

    private void sanitizeMonth() {
        for (Month month : Month.values()) {
            dateTimeInput = dateTimeInput.replace(month.getOldInput(), month.getNewInput());
        }
    }

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

    private void checkFormatIsMatched() throws DateTimeFormatException {
        if (!isMatchFormat) {
            throw new DateTimeFormatException();
        }
    }
}
