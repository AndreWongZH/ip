package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.command.CommandManager;
import duke.command.IllegalCommandException;

import duke.file.FileManager;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.TaskType;

public class Duke {
    private static final Scanner in = new Scanner(System.in);

    private static boolean isActive;

    public static void main(String[] args) {
        String userInput;
        Command command;
        String parameters;
        isActive = true;
        TaskManager taskManager = new TaskManager();

        // load data to TaskManager
        try {
            Path path = Paths.get(".","data", "duke.txt");
            ArrayList<Task> previousData = FileManager.getData(path);
            for (Task task : previousData) {
                taskManager.addTask(task);
            }
        } catch (IOException e) {
            System.out.println("Error file not found");
        }

        CommandManager.printGreeting();
        while (isActive) {
            try {
                userInput = readUserInput();
                command = CommandManager.extractCommand(userInput);
                parameters = CommandManager.extractParameters(command, userInput);
                handleUserInput(taskManager, command, parameters);
            } catch (IllegalCommandException e) {
                CommandManager.printInvalidCommand();
            } catch (IndexOutOfBoundsException e) {
                CommandManager.printInvalidParameters();
            }
        }
        CommandManager.printGoodbye();
    }

    private static String readUserInput() {
        return in.nextLine();
    }

    private static void handleUserInput(TaskManager taskManager, Command command, String parameters) {
        switch (command) {
        case LIST:
            taskManager.printAllTasks();
            break;
        case DONE:
            taskManager.setTaskDone(parameters);
            break;
        case TODO:
            taskManager.addTask(TaskType.TODO, parameters);
            break;
        case DEADLINE:
            taskManager.addTask(TaskType.DEADLINE, parameters);
            break;
        case EVENT:
            taskManager.addTask(TaskType.EVENT, parameters);
            break;
        case BYE:
            exitProgram();
            break;
        default:
            CommandManager.printNoCommandRan();
        }
    }

    private static void exitProgram() {
        isActive = false;
    }
}
