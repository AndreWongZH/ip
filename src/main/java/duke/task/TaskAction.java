package duke.task;

import java.time.LocalDateTime;

public interface TaskAction {
    void addTask(TaskType taskType, String description, LocalDateTime dateTime);
    void printAllTasks();
    void setTaskDone(int taskNumber);
    void deleteTask(int taskNumber);
}
