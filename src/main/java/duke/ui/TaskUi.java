package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the printing of task related errors and actions to user output.
 */
public class TaskUi extends Ui {
    private static final String ERROR_LIST_EMPTY = "No task in your list. Add some!";
    private static final String ERROR_TASK_TYPE_NOT_FOUND = "Task type is not found";

    private static final String PRINT_TASK_LIST_HEADING = "Here are the tasks in your list";
    private static final String PRINT_NUMBER_OF_TASK = "You now have a total of %d tasks in the list";
    private static final String PRINT_TASK_ADD_HEADING = "Task successfully added, I said with a posed look.";
    private static final String PRINT_TASK_REMOVE_HEADING = "Understood, removed task %d:";
    private static final String PRINT_TASK_DONE_HEADING = "Understood, setting task %d as done:";

    /**
     * Prints output to user after a task is successfully added.
     *
     * @param task Task object that have just been added.
     * @param tasksSize Total number of tasks currently in the task list.
     */
    public void printAddTaskSuccessful(Task task, int tasksSize) {
        generateMultiLineTextBorder(
                PRINT_TASK_ADD_HEADING,
                task.toString(),
                String.format(PRINT_NUMBER_OF_TASK, tasksSize));
    }

    /**
     * Prints output to user after a task is set to done.
     *
     * @param task Task object that have just been sent to done.
     * @param taskNumber Number that corresponds to that task in the task list.
     */
    public void printSetTaskDone(Task task, int taskNumber) {
        generateMultiLineTextBorder(
                String.format(PRINT_TASK_DONE_HEADING, taskNumber + 1),
                task.toString());
    }

    /**
     * Prints output to user after a task is deleted.
     *
     * @param task Task object that have just been deleted.
     * @param taskNumber Number that corresponds to that task in the task list.
     * @param taskSize Total number of tasks currently in the task list.
     */
    public void printTaskRemoved(Task task, int taskNumber, int taskSize) {
        generateMultiLineTextBorder(
                String.format(PRINT_TASK_REMOVE_HEADING, taskNumber + 1),
                task.toString(),
                String.format(PRINT_NUMBER_OF_TASK, taskSize));
    }

    /**
     * Prints all the tasks given in the parameter to the user.
     *
     * @param tasks An array list of tasks to be printed out
     */
    public void printTasksList(ArrayList<Task> tasks) {
        ArrayList<String> outputText;

        outputText = addOutputText(tasks);
        generateMultiLineTextBorder(outputText);
    }

    public void printTaskNotFound() {
        generateTextBorder(ERROR_TASK_TYPE_NOT_FOUND);
    }

    public void printTaskListEmpty() {
        generateTextBorder(ERROR_LIST_EMPTY);
    }

    /**
     * Returns an ArrayList of type string with associated list number.
     * This method will convert the tasks ArrayList of type task to an ArrayList of type string.
     * It also pads the task with its corresponding list number.
     *
     * @param tasks An array list of tasks of type task.
     * @return ArrayList of tasks in type string.
     */
    private ArrayList<String> addOutputText(ArrayList<Task> tasks) {
        ArrayList<String> outputText = new ArrayList<>();

        outputText.add(PRINT_TASK_LIST_HEADING);
        for (int i = 0; i < tasks.size(); i++) {
            outputText.add((i + 1) + ". " + tasks.get(i));
        }

        return outputText;
    }
}
