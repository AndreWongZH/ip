package duke.parser;

import java.time.LocalDateTime;

public class ParameterData {
    private final int taskNumber;
    private final String description;
    private final LocalDateTime dateTime;

    public ParameterData(int taskNumber) {
        this.taskNumber = taskNumber;
        description = null;
        dateTime = null;
    }

    public ParameterData(String description) {
        this.description = description;
        taskNumber = 0;
        dateTime = null;
    }

    public ParameterData(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
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
}
