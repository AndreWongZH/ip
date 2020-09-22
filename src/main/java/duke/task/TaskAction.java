package duke.task;

/**
 * Represents all the available actions that can be called from a task instance.
 */
public interface TaskAction {
    void addTask(TaskType taskType, String description, String dateTime);
    void printAllTasks();
    void setTaskDone(int taskNumber);
    void deleteTask(int taskNumber);
}
