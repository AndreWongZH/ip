package duke.command;

import duke.task.TaskManager;

/**
 * Represents the base of all command types.
 */
public abstract class Command {
    protected TaskManager taskManager;

    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Runs the taskManager methods based on the command type.
     */
    public abstract void execute();
}
