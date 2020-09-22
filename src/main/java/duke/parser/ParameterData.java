package duke.parser;

/**
 * Represents an object to hold all types of parameters required for different commands.
 */
public class ParameterData {
    private final int taskNumber;
    private final String description;
    private final String dateTime;

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

    public ParameterData(String description, String dateTime) {
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

    public String getDateTime() {
        return dateTime;
    }
}
