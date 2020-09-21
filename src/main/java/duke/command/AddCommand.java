package duke.command;

import java.time.LocalDateTime;

import duke.task.TaskManager;
import duke.task.TaskType;

public class AddCommand extends Command {
    private final TaskType taskType;
    private final String description;
    private LocalDateTime dateTime;


    public AddCommand(TaskManager taskManager, TaskType taskType, String description) {
        super(taskManager);
        this.taskType = taskType;
        this.description = description;
    }

    public AddCommand(TaskManager taskManager, TaskType taskType, String description, LocalDateTime dateTime) {
        this(taskManager, taskType, description);
        this.dateTime = dateTime;
    }

    @Override
    public void execute() {
        taskManager.addTask(taskType, description, dateTime);
    }
}
