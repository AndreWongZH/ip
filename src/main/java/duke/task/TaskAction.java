package duke.task;

public interface TaskAction {
    void addTask(TaskType taskType, String inputText);
    void printAllTasks();
    void setTaskDone(String inputText);
    void deleteTask(String inputText);
}
