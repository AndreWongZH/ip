package duke.command;

import duke.task.TaskManager;

public class DeleteCommand extends Command {
    private final String taskIndex;

    public DeleteCommand(TaskManager taskManager, String taskIndex) {
        super(taskManager);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        taskManager.deleteTask(taskIndex);
    }
}
