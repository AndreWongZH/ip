import java.util.Scanner;

public class Duke {
    private static final String GREET = "____________________________________________________________\n" +
            " __\n" +
            "('')  Hello! I'm MK.II Titan with feelings.\n" +
            "// \\  What can I do for you?\n" +
            "|_/| \n" +
            "____________________________________________________________\n";

    private static final String END = "____________________________________________________________\n" +
            " __\n" +
            "( \")  Bye! I said with a disappointed look.\n" +
            "|| \\\n" +
            "|_|| \n" +
            "____________________________________________________________\n";

    private static final String LOGO = " _    _      _                          \n" +
            "| |  | |    | |                         \n" +
            "| |  | | ___| | ___ ___  _ __ ___   ___ \n" +
            "| |/\\| |/ _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\\n" +
            "\\  /\\  /  __/ | (_| (_) | | | | | |  __/\n" +
            " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|";

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);
        boolean isActive = true;

        System.out.println(LOGO);
        System.out.println(GREET);
        while (isActive) {
            userInput = in.nextLine();

            if (userInput.equals("bye")) {
                isActive = false;
            } else {
                printEcho(userInput);
            }
        }
        System.out.println(END);
    }

    private static void printEcho(String userInput) {
        System.out.println("-> " + userInput + ", I said with a posed look.\n");
    }
}
