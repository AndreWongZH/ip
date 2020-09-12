package duke;

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
    private static TaskManager taskManager;

    private static boolean isActive;
    private static String parameters;
    private static Command command;

    public static void main(String[] args) {
        String userInput;
        isActive = true;

        CommandManager.printGreeting();
        loadPreviousData();
        while (isActive) {
            try {
                userInput = readUserInput();
                command = CommandManager.extractCommand(userInput);
                parameters = CommandManager.extractParameters(command, userInput);
                handleUserInput();
            } catch (IllegalCommandException e) {
                CommandManager.printInvalidCommand();
            } catch (IndexOutOfBoundsException e) {
                CommandManager.printInvalidParameters();
            }
        }
        CommandManager.printGoodbye();
    }

    /** load data to TaskManager */
    private static void loadPreviousData() {
        ArrayList<Task> previousData = FileManager.getData();
        taskManager = new TaskManager(previousData);
    }

    private static String readUserInput() {
        return in.nextLine();
    }

    private static void handleUserInput() {
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
        case DELETE:
            taskManager.deleteTask(parameters);
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
