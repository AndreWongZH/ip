package duke.file;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

public class FileManager {
    private static final String ERROR_UNABLE_TO_WRITE_FILE = "Error unable to open file to write";
    private static final String ERROR_FILE_NOT_FOUND = "Error file not found";
    private static final String ERROR_UNABLE_TO_CREATE_DATA_FILE = "Unable to create data file";
    private static final String SUCCESS_DATA_FILE_CREATED = "Data file created under data/duke.txt";
    private static final String FILE_NAME = "duke.txt";
    private static final String DATA_DIRECTORY = "data";

    public static final String FILE_PATH = "./data/duke.txt";

    /**
     * Check if ./data/duke.txt exist, if not creates it.
     * Reads data from ./data/duke.txt.
     * Parse the string formats into task objects
     *
     * @return previousData in the form of an ArrayList
     */
    public static ArrayList<Task> getData() {
        Path filePath = Paths.get(".", DATA_DIRECTORY, FILE_NAME);
        if (!Files.exists(filePath)) {
            createDataFile(filePath);
        }

        ArrayList<String> dataStreams = new ArrayList<>();
        Scanner s;

        try {
            s = new Scanner(filePath);
            while (s.hasNext()) {
                dataStreams.add(s.nextLine());
            }
        } catch (IOException e) {
            printFileNotFound();
        }

        return DataParser.fileToTask(dataStreams);
    }

    /**
     * Check if ./data/duke.txt exist, if not creates it.
     * Reads data from ./data/duke.txt.
     * Parse the string formats into task objects
     *
     * @param filePath location of path to write to
     * @param tasks an ArrayList of task objects
     */
    public static void writeToFile(String filePath, ArrayList<Task> tasks) {
        FileWriter fw;
        try {
            fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.convertToData());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            printUnableToWriteFile();
        }
    }

    private static void createDataFile(Path filePath) {
        try {
            Files.createFile(filePath);
            printFileCreated();
        } catch (IOException e) {
            printUnableToCreateFile();
        }
    }

    private static void printFileCreated() {
        System.out.println(SUCCESS_DATA_FILE_CREATED);
    }

    private static void printUnableToCreateFile() {
        System.out.println(ERROR_UNABLE_TO_CREATE_DATA_FILE);
    }

    private static void printUnableToWriteFile() {
        System.out.println(ERROR_UNABLE_TO_WRITE_FILE);
    }

    private static void printFileNotFound() {
        System.out.println(ERROR_FILE_NOT_FOUND);
    }
}
