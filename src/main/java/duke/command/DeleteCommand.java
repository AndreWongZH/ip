package duke.command;

import duke.task.TaskManager;

/**
 * Represents the command for deleting a selected task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(TaskManager taskManager, int taskIndex) {
        super(taskManager);
        this.taskIndex = taskIndex;
    }

    /**
     * Calls the taskManager instance to delete selected task.
     */
    @Override
    public void execute() {
        taskManager.deleteTask(taskIndex);
    }
}
