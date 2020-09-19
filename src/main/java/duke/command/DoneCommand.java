package duke.command;

import duke.task.TaskManager;

public class DoneCommand extends Command {
    private final int taskIndex;

    public DoneCommand(TaskManager taskManager, int taskIndex) {
        super(taskManager);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        taskManager.setTaskDone(taskIndex);
    }
}
