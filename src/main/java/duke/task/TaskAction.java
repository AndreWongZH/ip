package duke.task;

public interface TaskAction {
    void addTask(TaskType taskType, String description, String dateTime);
    void printAllTasks();
    void setTaskDone(int taskNumber);
    void deleteTask(int taskNumber);
}
