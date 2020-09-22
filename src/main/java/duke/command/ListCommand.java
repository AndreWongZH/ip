package duke.command;

import java.time.LocalDate;

import duke.task.TaskManager;

public class ListCommand extends Command {
    private final LocalDate matchDate;

    public ListCommand(TaskManager taskManager) {
        super(taskManager);
        matchDate = null;
    }

    public ListCommand(TaskManager taskManager, LocalDate matchDate) {
        super(taskManager);
        this.matchDate = matchDate;
    }

    @Override
    public void execute() {
        if (matchDate == null) {
            taskManager.printAllTasks();
            return;
        }
        taskManager.filterByDate(matchDate);
    }
}
