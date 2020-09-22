package duke.command;

import duke.task.TaskManager;

/**
 * Represents the command for printing out all the tasks.
 */
public class ListCommand extends Command {

    public ListCommand(TaskManager taskManager) {
        super(taskManager);
    }

    /**
     * Calls the taskManager instance to print out all tasks.
     */
    @Override
    public void execute() {
        taskManager.printAllTasks();
    }
}
