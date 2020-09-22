package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ParameterData {
    private final int taskNumber;
    private final String description;
    private final LocalDateTime dateTime;
    private final LocalDate matchDate;

    public ParameterData(int taskNumber) {
        this.taskNumber = taskNumber;
        description = null;
        dateTime = null;
        matchDate = null;
    }

    public ParameterData(String description) {
        this.description = description;
        taskNumber = 0;
        dateTime = null;
        matchDate = null;
    }

    public ParameterData(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
        taskNumber = 0;
        matchDate = null;
    }

    public ParameterData(LocalDate matchDate) {
        this.matchDate = matchDate;
        description = null;
        dateTime = null;
        taskNumber = 0;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }
}
