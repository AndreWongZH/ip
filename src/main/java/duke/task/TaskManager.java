package duke.task;

/**
 * Stores and add to the list of tasks.
 */
public class TaskManager {
    private static final int MAX_TASK_SIZE = 100;
    private static final String BY_LITERAL = " /by ";
    private static final String AT_LITERAL = " /at ";
    private static final int MAX_INPUT_PARAMS = 2;

    private static final String ERROR_NOT_INTEGER = "Sorry but parameter entered is not a integer\n";
    private static final String ERROR_NOT_IN_RANGE = "Sorry but parameter entered is not within range of list\n";
    private static final String ERROR_TASK_TYPE_NOT_FOUND = "Task type is not found\n";
    private static final String ERROR_LIST_EMPTY = "No task in your list. Add some!\n";
    private static final String ERROR_TASK_LIST_FULL = "I cannot add any more. Task list is full :(\n";

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

        try {
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
                throw new IllegalStateException();
            }
            addTaskToList(task);
            printAddTaskSuccessful(task);
        } catch (MissingTaskLiteralException e) {
            printMissingLiteral(e.getMessage());
        } catch (IllegalStateException e) {
            printTaskNotFound();
        } catch (IndexOutOfBoundsException e) {
            printTaskListFull();
        }
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
            System.out.println();
        } else {
            System.out.println(ERROR_LIST_EMPTY);
        }
    }

    /**
     * Sets the particular task to be done.
     * Prints to user that task has been set to done.
     *
     * @param inputText Corresponding value of the task to set done.
     */
    public void setTaskDone(String inputText) {
        int taskNumber = 0;

        try {
            taskNumber = getTaskNumber(inputText);
            tasks[taskNumber].setDone(true);
            printSetTaskDone(taskNumber, inputText);
        } catch (NumberFormatException e) {
            printTaskDoneNotInteger();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            printTaskDoneNotInRange();
        }
    }

    private void printTaskDoneNotInteger() {
        System.out.println(ERROR_NOT_INTEGER);
    }

    private void printTaskDoneNotInRange() {
        System.out.println(ERROR_NOT_IN_RANGE);
    }

    private void printTaskNotFound() {
        System.out.println(ERROR_TASK_TYPE_NOT_FOUND);
    }

    private void printTaskListFull() {
        System.out.println(ERROR_TASK_LIST_FULL);
    }

    /**  splits user input into descriptions and date/time */
    private String[] splitItem(TaskType taskType, String inputText) throws MissingTaskLiteralException, IllegalStateException {
        int index = 0;
        String[] taskParameters = new String[MAX_INPUT_PARAMS];

        switch (taskType) {
        case DEADLINE:
            if (!inputText.contains(BY_LITERAL)) {
                throw new MissingTaskLiteralException(BY_LITERAL);
            }
            index = inputText.indexOf(BY_LITERAL);
            break;
        case EVENT:
            if (!inputText.contains(AT_LITERAL)) {
                throw new MissingTaskLiteralException(AT_LITERAL);
            }
            index = inputText.indexOf(AT_LITERAL);
            break;
        default:
            throw new IllegalStateException();
        }

        taskParameters[0] = inputText.substring(0, index);
        taskParameters[1] = inputText.substring(index + 5);

        return taskParameters;
    }

    private void printMissingLiteral(String literal) {
        System.out.println("Command is missing the" + literal + "literal\n");
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

    private int getTaskNumber(String userInput) throws NumberFormatException {
        return Integer.parseInt(userInput) - 1;
    }

    /** print message after setting task to done */
    private void printSetTaskDone(int taskNumber, String userInput) {
        System.out.println("Understood, setting task " + userInput + " as done:");
        System.out.println(tasks[taskNumber]);
        System.out.println();
    }
}
