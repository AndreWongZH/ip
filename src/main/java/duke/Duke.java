package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.command.CommandType;

import duke.command.GreetCommand;
import duke.storage.FileManager;
import duke.parser.CommandParser;
import duke.task.Task;
import duke.task.TaskManager;

public class Duke {
    private static final Scanner in = new Scanner(System.in);
    private static TaskManager taskManager;
    private static boolean isActive;

    public Duke() {
        FileManager fileManager = new FileManager();
        ArrayList<Task> previousData = fileManager.loadData();
        taskManager = new TaskManager(fileManager, previousData);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        isActive = true;

        GreetCommand.printGreeting();
        while (isActive) {
            String userInput = readUserInput();
            CommandType commandType = new CommandParser(taskManager, userInput).parseCommand();
            if (commandType == CommandType.BYE) {
                exitProgram();
            }
        }
    }

    private String readUserInput() {
        return in.nextLine();
    }

    private void exitProgram() {
        isActive = false;
    }
}
