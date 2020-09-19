package duke.command;

import duke.task.TaskManager;

public class ListCommand extends Command {

    public ListCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void execute() {
        taskManager.printAllTasks();
    }
}
