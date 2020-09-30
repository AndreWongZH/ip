package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.task.TimeSearch;

/**
 * Represents an object to hold all types of parameters required for different commands.
 */
public class ParameterData {
    private final int taskNumber;
    private final String description;
    private final LocalDateTime dateTime;
    private final LocalDate matchDate;
    private final String filterString;
    private final TimeSearch timeSearch;

    /* Initialises ParameterData for done and delete command */
    public ParameterData(int taskNumber) {
        this.taskNumber = taskNumber;
        description = null;
        dateTime = null;
        matchDate = null;
        filterString = null;
        timeSearch = null;
    }

    /* Initialises ParameterData for add command for todo task and list command parameter */
    public ParameterData(String description) {
        this.description = description;
        taskNumber = 0;
        dateTime = null;
        matchDate = null;
        filterString = null;
        timeSearch = null;
    }

    /* Initialises ParameterData for add command for event and deadline task */
    public ParameterData(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
        taskNumber = 0;
        matchDate = null;
        filterString = null;
        timeSearch = null;
    }

    /* Initialises ParameterData for find command */
    public ParameterData(String filterString, LocalDate matchDate, TimeSearch timeSearch) {
        this.filterString = filterString;
        this.matchDate = matchDate;
        this.timeSearch = timeSearch;
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

    public String getFilterString() {
        return filterString;
    }

    public TimeSearch getTimeSearch() {
        return timeSearch;
    }
}
