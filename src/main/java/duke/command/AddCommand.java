package duke.command;

import java.time.LocalDateTime;

import duke.task.TaskManager;
import duke.task.TaskType;

/**
 * Represents the command for adding a task.
 */
public class AddCommand extends Command {
    private final TaskType taskType;
    private final String description;
    private LocalDateTime dateTime;

    /* Creates a AddCommand instance when task type is todo */
    public AddCommand(TaskManager taskManager, TaskType taskType, String description) {
        super(taskManager);
        this.taskType = taskType;
        this.description = description;
        this.dateTime = null;
    }

    /* Creates a AddCommand instance when task type is either deadline or event */
    public AddCommand(TaskManager taskManager, TaskType taskType, String description, LocalDateTime dateTime) {
        this(taskManager, taskType, description);
        this.dateTime = dateTime;
    }

    /**
     * Calls the taskManager instance to add a task object to tasks.
     */
    @Override
    public void execute() {
        taskManager.addTask(taskType, description, dateTime);
    }
}
