package duke.file;

import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String ERROR_UNABLE_TO_WRITE_FILE = "Error unable to open file to write";

    public static ArrayList<Task> getData(Path filePath) throws IOException {
        ArrayList<String> dataStreams = new ArrayList<>();
        Scanner s = new Scanner(filePath);

        while (s.hasNext()) {
            dataStreams.add(s.nextLine());
        }

        return DataParser.fileToTask(dataStreams);
    }

    public static void writeToFile(String filePath, Task[] tasks, int taskCount) {
        FileWriter fw;
        try {
            fw = new FileWriter(filePath);
            for (int i = 0; i < taskCount; i++) {
                fw.write(tasks[i].convertToData());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            printFileNotFound();
        }
    }

    private static void printFileNotFound() {
        System.out.println(ERROR_UNABLE_TO_WRITE_FILE);
    }
}
