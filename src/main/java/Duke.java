import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String GREET = "____________________________________________________________\n" +
                " __\n" +
                "('')  Hello! I'm MK.II Titan with feelings.\n" +
                "// \\  What can I do for you?\n" +
                "|_/| \n" +
                "____________________________________________________________\n";

        final String END = "____________________________________________________________\n" +
                " __\n" +
                "( \")  Bye! I said with a disappointed look.\n" +
                "|| \\\n" +
                "|_|| \n" +
                "____________________________________________________________\n";

        final String LOGO = " _    _      _                          \n" +
                "| |  | |    | |                         \n" +
                "| |  | | ___| | ___ ___  _ __ ___   ___ \n" +
                "| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\\n" +
                "\\  /\\  /  __/ | (_| (_) | | | | | |  __/\n" +
                " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|";

        String userInput;
        Scanner in = new Scanner(System.in);
        boolean isActive = true;
        TaskManager taskManager = new TaskManager();

        System.out.println(LOGO);
        System.out.println(GREET);
        while (isActive) {
            userInput = in.nextLine();

            if (userInput.equals("bye")) {
                isActive = false;
            } else if (userInput.equals("list")) {
                taskManager.printAllTasks();
            } else if (userInput.contains("done")) {
                taskManager.setTaskDone(userInput.substring(5));
            } else if (userInput.contains("todo")) {
                taskManager.addTask(TaskType.TODO, userInput.substring(5));
            } else if (userInput.contains("deadline")) {
                taskManager.addTask(TaskType.DEADLINE, userInput.substring(9));
            } else if (userInput.contains("event")) {
                taskManager.addTask(TaskType.EVENT, userInput.substring(6));
            }
        }
        System.out.println(END);
    }
}
