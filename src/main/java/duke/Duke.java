package duke;

import java.util.ArrayList;

import duke.command.CommandType;
import duke.parser.CommandParser;
import duke.storage.FileManager;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.InputUi;

public class Duke {
    private static TaskManager taskManager;
    private static InputUi inputUi;
    private static boolean isActive;

    public Duke() {
        FileManager fileManager = new FileManager();
        ArrayList<Task> previousData = fileManager.loadData();
        taskManager = new TaskManager(fileManager, previousData);
        inputUi = new InputUi();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        isActive = true;

        inputUi.greetUser();
        while (isActive) {
            String userInput = inputUi.readUserInput();
            if (hasUserInput(userInput)) {
                CommandType commandType = new CommandParser(taskManager, userInput).parseCommand();
                checkTerminate(commandType);
            }
        }
    }

    private void checkTerminate(CommandType commandType) {
        if (commandType == CommandType.BYE) {
            exitProgram();
        }
    }

    private void exitProgram() {
        isActive = false;
    }

    private boolean hasUserInput(String userInput) {
        return userInput != null;
    }
}
