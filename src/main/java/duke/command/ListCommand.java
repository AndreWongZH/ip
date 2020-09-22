package duke.command;

import java.time.LocalDate;

import duke.task.TaskManager;

/**
 * Represents the command for printing out all the tasks and limit by date.
 */
public class ListCommand extends Command {
    private final LocalDate matchDate;

    public ListCommand(TaskManager taskManager) {
        super(taskManager);
        matchDate = null;
    }

    /* Creates a ListCommand instance when a date is given */
    public ListCommand(TaskManager taskManager, LocalDate matchDate) {
        super(taskManager);
        this.matchDate = matchDate;
    }

    /**
     * Calls the taskManager instance to print out all tasks.
     * Filters tasks based on the date given as well.
     */
    @Override
    public void execute() {
        if (matchDate == null) {
            taskManager.printAllTasks();
            return;
        }
        taskManager.filterByDate(matchDate);
    }
}
