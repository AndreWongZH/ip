package duke.command;

import duke.task.TaskManager;

public class ByeCommand extends Command {
    private static final String MESSAGE_GOODBYE = "____________________________________________________________\n" +
            " __\n" +
            "( \")  Bye! I said with a disappointed look.\n" +
            "|| \\\n" +
            "|_||\n" +
            "____________________________________________________________";

    public ByeCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void execute() {
        printGoodbye();
        // TODO: 19/9/2020  Print task count here
    }

    private static void printGoodbye() {
        System.out.println(MESSAGE_GOODBYE);
    }
}
