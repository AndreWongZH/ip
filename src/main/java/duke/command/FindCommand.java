package duke.command;

import duke.task.TaskManager;

public class FindCommand extends Command {
    private final String filterString;

    public FindCommand(TaskManager taskManager, String filterString) {
        super(taskManager);
        this.filterString = filterString;
    }

    @Override
    public void execute() {
        taskManager.filterByString(filterString);
    }
}
