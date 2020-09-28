package duke.command;

import duke.task.TaskManager;
import duke.ui.InputUi;

/**
 * Represents the command for saying bye to user before the program terminates.
 */
public class ByeCommand extends Command {
    private final InputUi inputUi;

    public ByeCommand(TaskManager taskManager) {
        super(taskManager);
        inputUi = new InputUi();
    }

    /**
     * Calls the inputUi instance to print greeting.
     */
    @Override
    public void execute() {
        inputUi.printGoodbye(taskManager.getTotalCount());
    }
}
