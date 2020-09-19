package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.command.CommandType;

import duke.command.GreetCommand;
import duke.file.FileManager;
import duke.parser.CommandParser;
import duke.task.Task;
import duke.task.TaskManager;

public class Duke {
    private static final Scanner in = new Scanner(System.in);
    private static TaskManager taskManager;

    private static boolean isActive;

    public static void main(String[] args) {
        String userInput;
        CommandType commandType;
        isActive = true;

        GreetCommand.printGreeting();
        loadPreviousData();
        while (isActive) {
            userInput = readUserInput();
            commandType = new CommandParser(taskManager, userInput).parseCommand();
            if (commandType == CommandType.BYE) {
                exitProgram();
            }
        }
    }

    /** load data to TaskManager */
    private static void loadPreviousData() {
        ArrayList<Task> previousData = FileManager.getData();
        taskManager = new TaskManager(previousData);
    }

    private static String readUserInput() {
        return in.nextLine();
    }

    private static void exitProgram() {
        isActive = false;
    }
}
