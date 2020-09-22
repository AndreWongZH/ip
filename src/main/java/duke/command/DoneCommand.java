package duke.command;

import duke.task.TaskManager;

/**
 * Represents the command for setting task to done.
 */
public class DoneCommand extends Command {
    private final int taskIndex;

    public DoneCommand(TaskManager taskManager, int taskIndex) {
        super(taskManager);
        this.taskIndex = taskIndex;
    }

    /**
     * Calls the taskManager instance to set task to done.
     */
    @Override
    public void execute() {
        taskManager.setTaskDone(taskIndex);
    }
}
