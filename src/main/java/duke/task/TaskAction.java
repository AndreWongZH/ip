package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents all the available actions that can be called from a task instance.
 */
public interface TaskAction {
    void addTask(TaskType taskType, String description, LocalDateTime dateTime);
    void printAllTasks();
    void findTask(String filterString, LocalDate matchDate, TimeSearch timeSearch);
    void toggleTaskDone(int taskNumber);
    void deleteTask(int taskNumber);
    long getTodoCount();
    long getDeadlineCount();
    long getEventCount();
    long getTotalCount();
}
