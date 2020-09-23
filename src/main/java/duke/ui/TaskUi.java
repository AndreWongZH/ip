package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the printing of task related errors and actions to user output.
 */
public class TaskUi extends Ui {
    private static final String ERROR_LIST_EMPTY = "No task in your list. Add some!";
    private static final String ERROR_TASK_TYPE_NOT_FOUND = "Task type is not found";
    private static final String ERROR_SEARCH_NOT_FOUND = "No task found based on your specified parameters";

    private static final String PRINT_TASK_LIST_HEADING = "Here are the tasks in your list";
    private static final String PRINT_NUMBER_OF_TASK = "You now have a total of %d tasks in the list";
    private static final String PRINT_TASK_ADD_HEADING = "Task successfully added, I said with a posed look.";
    private static final String PRINT_TASK_REMOVE_HEADING = "Understood, removed task %d:";
    private static final String PRINT_TASK_DONE_HEADING = "Understood, setting task %d as done:";
    private static final String PRINT_TASK_NOT_DONE_HEADING = "Understood, reverting task %d back as not done:";
    private static final String PRINT_TASK_SEARCH_HEADING = "Here are your search results";

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
     * Prints output to user after a task's done is toggled.
     *
     * @param task Task object that have just been sent to done.
     * @param taskNumber Number that corresponds to that task in the task list.
     */
    public void printToggleTaskDone(Task task, int taskNumber) {
        String taskHeading = task.getIsDone() ? PRINT_TASK_DONE_HEADING : PRINT_TASK_NOT_DONE_HEADING;

        generateMultiLineTextBorder(
                String.format(taskHeading, taskNumber + 1),
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
        ArrayList<String> outputText = new ArrayList<>();

        outputText.add(PRINT_TASK_LIST_HEADING);
        addOutputText(outputText, tasks);
        generateMultiLineTextBorder(outputText);
    }

    public void printSearchTasksList(ArrayList<Task> tasks) {
        ArrayList<String> outputText = new ArrayList<>();

        outputText.add(PRINT_TASK_SEARCH_HEADING);
        addOutputText(outputText, tasks);
        generateMultiLineTextBorder(outputText);
    }

    public void printTaskNotFound() {
        generateTextBorder(ERROR_TASK_TYPE_NOT_FOUND);
    }

    public void printTaskListEmpty() {
        generateTextBorder(ERROR_LIST_EMPTY);
    }

    public void printTaskListSearchEmpty() {
        generateTextBorder(ERROR_SEARCH_NOT_FOUND);
    }

    /**
     * Returns an ArrayList of type string with associated list number.
     * This method will convert the tasks ArrayList of type task to an ArrayList of type string.
     * It also pads the task with its corresponding list number.
     *
     * @param tasks An array list of tasks of type task.
     */
    private void addOutputText(ArrayList<String> outputText, ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            outputText.add((i + 1) + ". " + tasks.get(i));
        }

    }
}
