package duke.command;

import duke.task.TaskManager;

public abstract class Command {
    protected TaskManager taskManager;

    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public abstract void execute();
}
