package duke.command;

import duke.task.TaskManager;
import duke.ui.InputUi;

public class ByeCommand extends Command {

    private final InputUi inputUi;

    public ByeCommand(TaskManager taskManager) {
        super(taskManager);
        inputUi = new InputUi();
    }

    @Override
    public void execute() {
        inputUi.printGoodbye();
        // TODO: 19/9/2020  Print task count here
    }
}
