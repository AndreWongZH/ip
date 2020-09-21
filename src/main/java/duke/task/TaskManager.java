package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.storage.FileManager;
import duke.ui.TaskUi;

/**
 * Stores and add to the list of tasks.
 */
public class TaskManager implements TaskAction {
    private static final int LIST_EMPTY = 0;

    private final ArrayList<Task> tasks;
    private final FileManager fileManager;
    private final TaskUi taskUi;

    public TaskManager(FileManager fileManager, ArrayList<Task> previousData) {
        this.fileManager = fileManager;
        tasks = previousData;
        this.taskUi = new TaskUi();
    }

    /**
     * Adds a task to the user's list of tasks.
     * Prints to user that task has been added.
     *
     * @param taskType represents the type of task to store.
     * @param description text of the user input to store.
     */
    @Override
    public void addTask(TaskType taskType, String description, LocalDateTime dateTime) {
        Task task;

        try {
            switch (taskType) {
            case TODO:
                task = new Todo(description);
                break;
            case DEADLINE:
                task = new Deadline(description, dateTime);
                break;
            case EVENT:
                task = new Event(description, dateTime);
                break;
            default:
                throw new IllegalStateException();
            }
            addTaskToList(task);
            taskUi.printAddTaskSuccessful(task, tasks.size());
            fileManager.writeToFile(tasks);
        } catch (IllegalStateException e) {
            taskUi.printTaskNotFound();
        }
    }

    /**
     * Prints the entire list of user's tasks.
     */
    @Override
    public void printAllTasks() {
        if (tasks.size() == LIST_EMPTY) {
            taskUi.printTaskListEmpty();
            return;
        }
        taskUi.printTasksList(tasks);
    }

    /**
     * Sets the particular task to be done.
     * Prints to user that task has been set to done.
     *
     * @param taskNumber Corresponding value of the task to set done.
     */
    @Override
    public void setTaskDone(int taskNumber) {
        tasks.get(taskNumber).setDone(true);
        taskUi.printSetTaskDone(tasks.get(taskNumber), taskNumber);
        fileManager.writeToFile(tasks);
    }

    /**
     * Deletes a particular task.
     * Prints to user that task has been deleted.
     *
     * @param taskNumber Corresponding value of the task to delete.
     */
    @Override
    public void deleteTask(int taskNumber) {
        Task task;

        task = tasks.remove(taskNumber);
        taskUi.printTaskRemoved(task, taskNumber, tasks.size());
        fileManager.writeToFile(tasks);
    }

    /** add a task instance to taskList */
    private void addTaskToList(Task task) {
        tasks.add(task);
    }
}
