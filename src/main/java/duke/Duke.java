package duke;

import java.util.ArrayList;

import duke.command.CommandType;
import duke.parser.CommandParser;
import duke.storage.FileManager;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.InputUi;

/**
 * The Duke program is an application that acts as a schedule manager.
 *
 * @author Andre Wong Zhi Hua
 * @version 1.0
 * @since 22/9/2020
 */
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

    /**
     * Loops while asking for user's input and executing commands.
     * This will stop if user input a bye command.
     */
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

    /**
     * Checks if a commandType bye has been received.
     * If received, then terminates the program.
     *
     * @param commandType Command enum of commandTypes.
     */
    private void checkTerminate(CommandType commandType) {
        if (commandType == CommandType.BYE) {
            exitProgram();
        }
    }

    private void exitProgram() {
        isActive = false;
    }

    /* Checks if user has not input any empty string */
    private boolean hasUserInput(String userInput) {
        return userInput != null;
    }
}
