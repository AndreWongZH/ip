package duke.command;

import duke.task.TaskManager;
import duke.ui.InputUi;

/**
 * Represents the command for printing out all the command formats.
 */
public class HelpCommand extends Command {
    private final InputUi inputUi;

    public HelpCommand(TaskManager taskManager) {
        super(taskManager);
        inputUi = new InputUi();
    }

    @Override
    public void execute() {
        inputUi.printHelp();
    }
}
