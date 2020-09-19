package duke.command;

import duke.task.TaskManager;

public class DoneCommand extends Command {
    private final String taskIndex;

    public DoneCommand(TaskManager taskManager, String taskIndex) {
        super(taskManager);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        taskManager.setTaskDone(taskIndex);
    }
}
