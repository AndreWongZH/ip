/**
 * Stores and add to the list of tasks.
 */
public class TaskManager {
    private final Task[] tasks;
    private int taskCount;

    public TaskManager() {
        tasks = new Task[100];
        taskCount = 0;
    }

    /**
     * Adds a task to the user's list of tasks.
     * Prints to user that task has been added.
     *
     * @param item text of the user input to store.
     */
    public void addTask(String item) {
        tasks[taskCount] = new Task(item);
        taskCount++;
        System.out.println("added: " + item + ", I said with a posed look.\n");
    }

    /**
     * Prints the entire list of user's tasks.
     */
    public void printAllTasks() {
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].getTaskStatus());
        }
        System.out.println();
    }

    /**
     * Sets the particular task to be done.
     * Prints to user that task has been set to done.
     *
     * @param userInput Corresponding value of the task to set done.
     */
    public void setTaskDone(String userInput) {
        int taskNumber = Integer.parseInt(userInput) - 1;
        tasks[taskNumber].setDone(true);

        System.out.println("Understood, setting task " + userInput + " as done:");
        System.out.println(tasks[taskNumber].getTaskStatus());
        System.out.println();
    }
}
