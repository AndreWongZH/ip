package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TaskAction {
    void addTask(TaskType taskType, String description, LocalDateTime dateTime);
    void printAllTasks(LocalDate matchDate);
    void setTaskDone(int taskNumber);
    void deleteTask(int taskNumber);
}
