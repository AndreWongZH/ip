package duke.command;

import duke.task.TaskManager;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(TaskManager taskManager, int taskIndex) {
        super(taskManager);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        taskManager.deleteTask(taskIndex);
    }
}
