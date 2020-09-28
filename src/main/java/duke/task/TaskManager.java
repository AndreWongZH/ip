package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

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
     * Filters out tasks based on the filterString.
     * Empty filterString would mean it does not filter any tasks by description.
     * Filters out task based on the date given and the timeSearch property.
     * Prints to user the tasks that has been filtered.
     * If there are no tasks left then inform user that their query yields no result.
     *
     * @param filterString String to compare with in the description.
     * @param matchDate Date to compare with in the task date.
     * @param timeSearch How the comparison is done, after, before or on the specified date.
     */
    @Override
    public void findTask(String filterString, LocalDate matchDate, TimeSearch timeSearch) {
        ArrayList<Task> filteredTasks;

        filteredTasks = filterByString(filterString);
        if (matchDate != null) {
            filteredTasks = filterByDate(filteredTasks, matchDate, timeSearch);
        }

        printSearchTasks(filteredTasks);
    }

    /**
     * Toggles the particular task to be done.
     * Prints to user that task's done has been toggled.
     * Write data to file after task's done has been toggled.
     *
     * @param taskNumber Corresponding value of the task to be set done.
     */
    @Override
    public void toggleTaskDone(int taskNumber) {
        tasks.get(taskNumber).toggleDone();
        taskUi.printToggleTaskDone(tasks.get(taskNumber), taskNumber);
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

    /**
     * Returns the total number to todos in tasks.
     *
     * @return Total number of todo counts.
     */
    @Override
    public long getTodoCount() {
        return tasks.stream().filter((t) -> t instanceof Todo).count();
    }

    /**
     * Returns the total number to deadlines in tasks.
     *
     * @return Total number of deadline counts.
     */
    @Override
    public long getDeadlineCount() {
        return tasks.stream().filter((t) -> t instanceof Deadline).count();
    }

    /**
     * Returns the total number to events in tasks.
     *
     * @return Total number of event counts.
     */
    @Override
    public long getEventCount() {
        return tasks.stream().filter((t) -> t instanceof Event).count();
    }

    /**
     * Returns the total number of tasks.
     *
     * @return Total number of tasks.
     */
    public long getTotalCount() {
        return getTodoCount() + getDeadlineCount() + getEventCount();
    }

    /**
     * Prints out all the task from the filtered array list.
     * If the array list is empty, then inform user that search yields no result.
     *
     * @param tasks ArrayList of tasks to be printed out.
     */
    private void printSearchTasks(ArrayList<Task> tasks) {
        if (tasks.size() == LIST_EMPTY) {
            taskUi.printTaskListSearchEmpty();
            return;
        }

        taskUi.printSearchTasksList(tasks);
    }

    /**
     * Filters tasks to find task's dates that matches with the given matchDate
     * based on the timeSearch parameter.
     *
     * @param filteredTasks An ArrayList of tasks that has already been filtered.
     * @param matchDate LocalDate object that all task must compare to.
     * @param timeSearch A TimeSearch enum to filter tasks before, after or on the date specified.
     * @return An ArrayList of tasks filtered by date.
     */
    private ArrayList<Task> filterByDate(ArrayList<Task> filteredTasks, LocalDate matchDate, TimeSearch timeSearch) {
        return (ArrayList<Task>) filteredTasks.stream()
                .filter((t) -> {
                    switch (timeSearch) {
                    case CURRENT:
                        return t.convertToDate().equals(matchDate);
                    case FORWARD:
                        return t.convertToDate().isAfter(matchDate);
                    case BACKWARD:
                        return t.convertToDate().isBefore(matchDate);
                    default:
                        return false;
                    }
                })
                .collect(toList());
    }

    /**
     * Filters tasks to find task's descriptions that matches with the given string.
     *
     * @param filterString The string to match task's descriptions with.
     * @return An ArrayList of tasks filtered by filterString.
     */
    private ArrayList<Task> filterByString(String filterString) {
        return (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().toLowerCase().contains(filterString.toLowerCase()))
                .collect(toList());
    }

    /** Adds a task instance to tasks */
    private void addTaskToList(Task task) {
        tasks.add(task);
    }
}
