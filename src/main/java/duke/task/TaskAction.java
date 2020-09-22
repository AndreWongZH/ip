package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents all the available actions that can be called from a task instance.
 */
public interface TaskAction {
    void addTask(TaskType taskType, String description, LocalDateTime dateTime);
    void printAllTasks();
    void filterByDate(LocalDate matchDate);
    void filterByString(String filterString);
    void setTaskDone(int taskNumber);
    void deleteTask(int taskNumber);
}
