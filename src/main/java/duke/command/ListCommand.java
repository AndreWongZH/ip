package duke.command;

import duke.task.TaskManager;

/**
 * Represents the command for printing out all the tasks.
 */
public class ListCommand extends Command {
    private final String argument;

    public ListCommand(TaskManager taskManager, String argument) {
        super(taskManager);
        this.argument = argument;
    }

    /**
     * Calls the taskManager instance to print out all tasks.
     * Can take an argument desc or asc to print the tasks in order.
     */
    @Override
    public void execute() {
        taskManager.printAllTasks(argument);
    }
}
