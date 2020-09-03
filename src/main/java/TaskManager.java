/**
 * Stores and add to the list of tasks.
 */
public class TaskManager {
    private static final int MAX_TASK_SIZE = 100;
    private static final String BY_LITERAL = "/by";
    private static final String AT_LITERAL = "/at";
    private static final int MAX_INPUT_PARAMS = 2;

    private final Task[] tasks;
    private int taskCount;

    public TaskManager() {
        tasks = new Task[MAX_TASK_SIZE];
        taskCount = 0;
    }

    /**
     * Adds a task to the user's list of tasks.
     * Prints to user that task has been added.
     *
     * @param taskType represents the type of task to store.
     * @param inputText text of the user input to store.
     */
    public void addTask(TaskType taskType, String inputText) {
        Task task;
        String[] taskParameters;

        switch (taskType) {
        case TODO:
            task = new Todo(inputText);
            break;
        case DEADLINE:
            taskParameters = splitItem(TaskType.DEADLINE, inputText);
            task = new Deadline(taskParameters);
            break;
        case EVENT:
            taskParameters = splitItem(TaskType.EVENT, inputText);
            task = new Event(taskParameters);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + taskType);
        }

        addTaskToList(task);
        printAddTaskSuccessful(task);
    }

    /**
     * Prints the entire list of user's tasks.
     */
    public void printAllTasks() {
        if (taskCount != 0) {
            System.out.println("Here are the tasks in your list");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        } else {
            System.out.println("No task in your list. Add some!");
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
        int taskNumber = getTaskNumber(userInput);
        tasks[taskNumber].setDone(true);
        printSetTaskDone(taskNumber, userInput);
    }

    /**  splits user input into descriptions and date/time */
    private String[] splitItem(TaskType taskType, String inputText) {
        int index = 0;
        String[] taskParameters = new String[MAX_INPUT_PARAMS];

        switch (taskType) {
        case DEADLINE:
            index = inputText.indexOf(BY_LITERAL);
            break;
        case EVENT:
            index = inputText.indexOf(AT_LITERAL);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + taskType);
        }

        taskParameters[0] = inputText.substring(0, index);
        taskParameters[1] = inputText.substring(index + 4);

        return taskParameters;
    }

    /** print message after a task is added */
    private void printAddTaskSuccessful(Task task) {
        System.out.println("Task successfully added, I said with a posed look.");
        System.out.println(task);
        System.out.println("Now you have a total of " + taskCount + " tasks in the list\n");
    }

    /** add a task instance to taskList */
    private void addTaskToList(Task task) {
        tasks[taskCount] = task;
        taskCount++;
    }

    private int getTaskNumber(String userInput) {
        return Integer.parseInt(userInput) - 1;
    }

    /** print message after setting task to done */
    private void printSetTaskDone(int taskNumber, String userInput) {
        System.out.println("Understood, setting task " + userInput + " as done:");
        System.out.println(tasks[taskNumber]);
        System.out.println();
    }
}
