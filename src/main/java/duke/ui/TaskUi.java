package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

public class TaskUi extends Ui {
    private static final String ERROR_LIST_EMPTY = "No task in your list. Add some!";
    private static final String ERROR_TASK_TYPE_NOT_FOUND = "Task type is not found";

    private static final String PRINT_TASK_LIST_HEADING = "Here are the tasks in your list";
    private static final String PRINT_NUMBER_OF_TASK = "You now have a total of %d tasks in the list";
    private static final String PRINT_TASK_ADD_HEADING = "Task successfully added, I said with a posed look.";
    private static final String PRINT_TASK_REMOVE_HEADING = "Understood, removed task %d:";
    private static final String PRINT_TASK_DONE_HEADING = "Understood, setting task %d as done:";

    public void printTaskNotFound() {
        generateTextBorder(ERROR_TASK_TYPE_NOT_FOUND);
    }

    /** print message after a task is added */
    public void printAddTaskSuccessful(Task task, int tasksSize) {
        generateMultiLineTextBorder(
                PRINT_TASK_ADD_HEADING,
                task.toString(),
                String.format(PRINT_NUMBER_OF_TASK, tasksSize));
    }

    /** print message after setting task to done */
    public void printSetTaskDone(Task task, int taskNumber) {
        generateMultiLineTextBorder(
                String.format(PRINT_TASK_DONE_HEADING, taskNumber + 1),
                task.toString());
    }

    /** print message after deleting a task */
    public void printTaskRemoved(Task task, int taskNumber, int taskSize) {
        generateMultiLineTextBorder(
                String.format(PRINT_TASK_REMOVE_HEADING, taskNumber + 1),
                task.toString(),
                String.format(PRINT_NUMBER_OF_TASK, taskSize));
    }

    public void printTaskListEmpty() {
        generateTextBorder(ERROR_LIST_EMPTY);
    }

    public void printTasksList(ArrayList<Task> tasks) {
        ArrayList<String> outputText;

        outputText = addOutputText(tasks);
        generateMultiLineTextBorder(outputText);
    }

    private ArrayList<String> addOutputText(ArrayList<Task> tasks) {
        ArrayList<String> outputText = new ArrayList<>();

        outputText.add(PRINT_TASK_LIST_HEADING);
        for (int i = 0; i < tasks.size(); i++) {
            outputText.add((i + 1) + ". " + tasks.get(i));
        }

        return outputText;
    }
}
