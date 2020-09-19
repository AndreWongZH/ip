package duke.task;

import java.util.ArrayList;

import duke.storage.FileManager;

/**
 * Stores and add to the list of tasks.
 */
public class TaskManager implements TaskAction {
    private static final int LIST_EMPTY = 0;

    private static final String ERROR_TASK_TYPE_NOT_FOUND = "Task type is not found\n";
    private static final String ERROR_LIST_EMPTY = "No task in your list. Add some!\n";

    private final ArrayList<Task> tasks;
    private final FileManager fileManager;

    public TaskManager(FileManager fileManager, ArrayList<Task> previousData) {
        this.fileManager = fileManager;
        tasks = previousData;
    }

    /**
     * Adds a task to the user's list of tasks.
     * Prints to user that task has been added.
     *
     * @param taskType represents the type of task to store.
     * @param description text of the user input to store.
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
            printAddTaskSuccessful(task);
            fileManager.writeToFile(tasks);
        } catch (IllegalStateException e) {
            printTaskNotFound();
        }
    }

    /**
     * Prints the entire list of user's tasks.
     */
    @Override
    public void printAllTasks() {
        if (tasks.size() == LIST_EMPTY) {
            System.out.println(ERROR_LIST_EMPTY);
            return;
        }

        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println();
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
        printSetTaskDone(taskNumber);
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
        printTaskRemoved(task, taskNumber);
        fileManager.writeToFile(tasks);
    }

    private void printTaskNotFound() {
        System.out.println(ERROR_TASK_TYPE_NOT_FOUND);
    }

    /** add a task instance to taskList */
    private void addTaskToList(Task task) {
        tasks.add(task);
    }

    /** print message after a task is added */
    private void printAddTaskSuccessful(Task task) {
        System.out.println("Task successfully added, I said with a posed look.");
        System.out.println(task);
        System.out.println("Now you have a total of " + tasks.size() + " tasks in the list\n");
    }

    /** print message after setting task to done */
    private void printSetTaskDone(int taskNumber) {
        System.out.println("Understood, setting task " + (taskNumber + 1) + " as done:");
        System.out.println(tasks.get(taskNumber));
        System.out.println();
    }

    /** print message after deleting a task */
    private void printTaskRemoved(Task task, int taskNumber) {
        System.out.println("Understood, removed task " + (taskNumber + 1) + ":");
        System.out.println(task);
        System.out.println("You now have a total of " + tasks.size() + " tasks in the list");
        System.out.println();
    }
}
