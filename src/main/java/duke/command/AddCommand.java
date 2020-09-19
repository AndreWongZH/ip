package duke.command;

import duke.task.TaskManager;
import duke.task.TaskType;

public class AddCommand extends Command {
    private final TaskType taskType;
//    private final String description;
//    private String dateTime;
    private String parameters;


//    public AddCommand(TaskManager taskManager, TaskType taskType, String description) {
//        super(taskManager);
//        this.taskType = taskType;
//        this.description = description;
//    }
//
//    public AddCommand(TaskManager taskManager, TaskType taskType, String description, String dateTime) {
//        this(taskManager, taskType, description);
//        this.dateTime = dateTime;
//    }

    public AddCommand(TaskManager taskManager, TaskType taskType, String parameters) {
        super(taskManager);
        this.taskType = taskType;
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        taskManager.addTask(taskType, parameters);
    }
}
