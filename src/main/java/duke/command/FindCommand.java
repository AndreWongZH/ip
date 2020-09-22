package duke.command;

import duke.task.TaskManager;

/**
 * Represents the command for searching via task description.
 */
public class FindCommand extends Command {
    private final String filterString;

    public FindCommand(TaskManager taskManager, String filterString) {
        super(taskManager);
        this.filterString = filterString;
    }

    /**
     * Calls the taskManager instance to search for tasks based on keyword.
     */
    @Override
    public void execute() {
        taskManager.filterByString(filterString);
    }
}
