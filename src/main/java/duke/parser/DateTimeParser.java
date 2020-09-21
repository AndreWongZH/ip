package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private final String dateTimeInput;
    LocalDateTime dateTime;
    boolean isMatchFormat;

    public DateTimeParser(String dateTimeInput) {
        this.dateTimeInput = dateTimeInput;
        dateTime = null;
        isMatchFormat = false;
    }

    public LocalDateTime formatDate() throws DateTimeFormatException {
        fitInputToFormat();
        checkFormatIsMatched();

        return dateTime;
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
