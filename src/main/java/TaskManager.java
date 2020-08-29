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

    private String[] splitItem(TaskType taskType, String item) {
        int index = 0;
        String[] separatedItems = new String[2];

        switch (taskType) {
        case DEADLINE:
            index = item.indexOf("/by");
            break;
        case EVENT:
            index = item.indexOf("/at");
            break;
        }

        separatedItems[0] = item.substring(0, index);
        separatedItems[1] = item.substring(index + 4);

        return separatedItems;
    }

    /**
     * Adds a task to the user's list of tasks.
     * Prints to user that task has been added.
     *
     * @param taskType represents the type of task to store.
     * @param item text of the user input to store.
     */
    public void addTask(TaskType taskType, String item) {
        Task todo;
        String[] separatedItems;

        switch (taskType) {
        case TODO:
            todo = new Todo(item);
            break;
        case DEADLINE:
            separatedItems = splitItem(TaskType.DEADLINE, item);
            todo = new Deadline(separatedItems[0], separatedItems[1]);
            break;
        case EVENT:
            separatedItems = splitItem(TaskType.EVENT, item);
            todo = new Event(separatedItems[0], separatedItems[1]);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + taskType);
        }

        tasks[taskCount] = todo;
        taskCount++;
        System.out.println("Task successfully added, I said with a posed look.");
        System.out.println(todo);
        System.out.println("Now you have a total of " + taskCount + " tasks in the list\n");
    }

    /**
     * Prints the entire list of user's tasks.
     */
    public void printAllTasks() {
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
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
        System.out.println(tasks[taskNumber]);
        System.out.println();
    }
}
