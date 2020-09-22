package duke.command;

import java.time.LocalDate;

import duke.task.TaskManager;
import duke.task.TimeSearch;

/**
 * Represents the command for searching via task description and date.
 */
public class FindCommand extends Command {
    private final String filterString;
    private final LocalDate matchDate;
    private final TimeSearch timeSearch;

    /* The parameter matchDate and timeSearch can be null */
    public FindCommand(TaskManager taskManager, String filterString, LocalDate matchDate, TimeSearch timeSearch) {
        super(taskManager);
        this.filterString = filterString;
        this.matchDate = matchDate;
        this.timeSearch = timeSearch;
    }

    /**
     * Calls the taskManager instance to search for tasks based on keyword and date.
     */
    @Override
    public void execute() {
        taskManager.findTask(filterString, matchDate, timeSearch);
    }
}
