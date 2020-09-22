package duke.task;

import java.util.ArrayList;

import duke.storage.FileManager;
import duke.ui.TaskUi;

/**
 * Manages the list of tasks. This includes adding of task,
 * printing of tasks, setting tasks done and deleting tasks.
 * It also writes the data to a text file after each operations.
 */
public class TaskManager implements TaskAction {
    private static final int LIST_EMPTY = 0;

    private final ArrayList<Task> tasks;
    private final FileManager fileManager;
    private final TaskUi taskUi;

    /**
     * Creates a new task manager instance from previous data of tasks.
     * PreviousData can be a new empty ArrayList object but cannot be null.
     *
     * @param fileManager A fileManager instance to write data to file.
     * @param previousData An ArrayList of tasks.
     */
    public TaskManager(FileManager fileManager, ArrayList<Task> previousData) {
        this.fileManager = fileManager;
        tasks = previousData;
        this.taskUi = new TaskUi();
    }

    /**
     * Adds a task to the user's list of tasks.
     * Write tasks to file after adding.
     * Prints to user that task has been added.
     * DateTime parameter can be null.
     * If task type is not found, then inform user of the error.
     *
     * @param taskType Represents the type of task to store.
     * @param description Text of the user input to store.
     * @param dateTime Date time parameter of the user input.
     */
    @Override
    public void addTask(TaskType taskType, String description, String dateTime) {
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
     * If tasks is empty, then print to output to
     * inform user tasks is empty and to add more task.
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
     * Sets a particular task to be done.
     * Prints to user that task has been set to done.
     * Write data to file after task has been set to done.
     *
     * @param taskNumber Corresponding value of the task to be set done.
     */
    @Override
    public void setTaskDone(int taskNumber) {
        tasks.get(taskNumber).setDone(true);
        taskUi.printSetTaskDone(tasks.get(taskNumber), taskNumber);
        fileManager.writeToFile(tasks);
    }

    /**
     * Deletes a particular task from tasks.
     * Prints to user that task has been deleted.
     * Write data to file after task has been deleted
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

    /** add a task instance to tasks */
    private void addTaskToList(Task task) {
        tasks.add(task);
    }
}
