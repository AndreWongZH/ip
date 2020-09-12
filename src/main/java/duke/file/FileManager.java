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
    private static final String ERROR_UNABLE_TO_WRITE_FILE = "Error unable to open file to write\n";
    private static final String ERROR_FILE_NOT_FOUND = "Error file not found\n";
    private static final String ERROR_CORRUPT_FILE = "Error, data file might be corrupted\n";
    private static final String ERROR_UNABLE_TO_CREATE_DATA_FILE = "Unable to create data file\n";
    private static final String ERROR_UNABLE_TO_DELETE_FILE = "Unable to delete file\n";
    private static final String ERROR_UNABLE_TO_CREATE_DIRECTORY = "Unable to create directory\n";

    private static final String SUCCESS_DATA_FILE_CREATED = "Data file created under data/duke.txt\n";
    private static final String SUCCESS_FILE_DELETED = "Corrupt file deleted\n";
    private static final String SUCCESS_DATA_DIRECTORY_CREATED = "Data directory created\n";

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
        Path filePath = Paths.get(DATA_DIRECTORY, FILE_NAME);
        Path directoryPath = Paths.get(DATA_DIRECTORY);
        if(!Files.exists(directoryPath)) {
            createDataDirectory(directoryPath);
        }
        if (!Files.exists(filePath)) {
            createDataFile(filePath);
        }

        ArrayList<String> dataStreams = new ArrayList<>();
        ArrayList<Task> previousData = new ArrayList<>();
        Scanner s;

        try {
            s = new Scanner(filePath);
            while (s.hasNext()) {
                dataStreams.add(s.nextLine());
            }
            previousData = DataParser.fileToTask(dataStreams);
        } catch (IOException e) {
            printFileNotFound();
        } catch (FileCorruptedException | ArrayIndexOutOfBoundsException e) {
            printCorruptFile();
            deleteDataFile(filePath);
            createDataFile(filePath);
        }

        return previousData;
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

    private static void createDataDirectory(Path directoryPath) {
        try {
            Files.createDirectory(directoryPath);
            printDirCreated();
        } catch (IOException e) {
            printUnableToCreateDir();
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

    private static void deleteDataFile(Path filePath) {
        try {
            Files.deleteIfExists(filePath);
            printFileDeleted();
        } catch (IOException e) {
            printUnableToDeleteFile();
        }
    }

    private static void printDirCreated() {
        System.out.println(SUCCESS_DATA_DIRECTORY_CREATED);
    }

    private static void printUnableToCreateDir() {
        System.out.println(ERROR_UNABLE_TO_CREATE_DIRECTORY);
    }

    private static void printFileDeleted() {
        System.out.println(SUCCESS_FILE_DELETED);
    }

    private static void printUnableToDeleteFile() {
        System.out.println(ERROR_UNABLE_TO_DELETE_FILE);
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

    private static void printCorruptFile() {
        System.out.println(ERROR_CORRUPT_FILE);
    }
}
