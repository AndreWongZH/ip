package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.command.CommandEnum;
import duke.command.CommandManager;
import duke.command.IllegalCommandException;
import duke.command.AddCommand;
import duke.command.ListCommand;
import duke.command.ByeCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;

import duke.file.FileManager;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.TaskType;

public class Duke {
    private static final Scanner in = new Scanner(System.in);
    private static TaskManager taskManager;

    private static boolean isActive;
    private static String parameters;
    private static CommandEnum commandEnum;

    public static void main(String[] args) {
        String userInput;
        isActive = true;

        CommandManager.printGreeting();
        loadPreviousData();
        while (isActive) {
            try {
                userInput = readUserInput();
                commandEnum = CommandManager.extractCommand(userInput);
                parameters = CommandManager.extractParameters(commandEnum, userInput);
                handleUserInput();
            } catch (IllegalCommandException e) {
                CommandManager.printInvalidCommand();
            } catch (IndexOutOfBoundsException e) {
                CommandManager.printInvalidParameters();
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

    private static void handleUserInput() {
        Command command = null;

        switch (commandEnum) {
        case LIST:
            command = new ListCommand(taskManager);
            break;
        case DONE:
            command = new DoneCommand(taskManager, parameters);
            break;
        case TODO:
            command = new AddCommand(taskManager, TaskType.TODO, parameters);
            break;
        case DEADLINE:
            command = new AddCommand(taskManager, TaskType.DEADLINE, parameters);
            break;
        case EVENT:
            command = new AddCommand(taskManager, TaskType.EVENT, parameters);
            break;
        case DELETE:
            command = new DeleteCommand(taskManager, parameters);
            break;
        case BYE:
            command = new ByeCommand(taskManager);
            exitProgram();
            break;
        default:
            CommandManager.printNoCommandRan();
        }


        command.execute();
    }

    private static void exitProgram() {
        isActive = false;
    }
}
