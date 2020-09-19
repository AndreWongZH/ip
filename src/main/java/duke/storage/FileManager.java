package duke.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.parser.DataParser;
import duke.task.Task;

public class FileManager implements FileAction {
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
    private static final String FILE_PATH = "./data/duke.txt";

    private final Path filePath;
    private final Path directoryPath;

    public FileManager() {
        filePath = Paths.get(DATA_DIRECTORY, FILE_NAME);
        directoryPath = Paths.get(DATA_DIRECTORY);
        validatePaths();
    }

    /**
     * Check if ./data/duke.txt exist, if not creates it.
     * Reads data from ./data/duke.txt.
     * Parse the string formats into task objects
     *
     * @return previousData in the form of an ArrayList
     */
    @Override
    public ArrayList<Task> loadData() {
        ArrayList<String> fileData;
        ArrayList<Task> previousData;

        fileData = getFileData();
        previousData = parseFileData(fileData);

        return previousData;
    }

    /**
     * Check if ./data/duke.txt exist, if not creates it.
     * Reads data from ./data/duke.txt.
     * Parse the string formats into task objects
     *
     * @param tasks an ArrayList of task objects
     */
    @Override
    public void writeToFile(ArrayList<Task> tasks) {
        FileWriter fw;
        try {
            fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.convertToData());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            printUnableToWriteFile();
        }
    }

    private ArrayList<Task> parseFileData(ArrayList<String> fileData) {
        try {
            return DataParser.fileToTask(fileData);
        } catch (FileCorruptedException | ArrayIndexOutOfBoundsException e) {
            handleCorruptFile();
        }
        return null;
    }

    private ArrayList<String> getFileData() {
        ArrayList<String> fileData = new ArrayList<>();
        Scanner s;

        try {
            s = new Scanner(filePath);
            while (s.hasNext()) {
                fileData.add(s.nextLine());
            }
        } catch (IOException e) {
            printFileNotFound();
        } catch (ArrayIndexOutOfBoundsException e) {
            handleCorruptFile();
        }

        return fileData;
    }

    private void validatePaths() {
        if(!Files.exists(directoryPath)) {
            createDataDirectory();
        }
        if (!Files.exists(filePath)) {
            createDataFile();
        }
    }

    private void handleCorruptFile() {
        printCorruptFile();
        deleteDataFile();
        createDataFile();
    }

    private void createDataDirectory() {
        try {
            Files.createDirectory(directoryPath);
            printDirCreated();
        } catch (IOException e) {
            printUnableToCreateDir();
        }
    }

    private void createDataFile() {
        try {
            Files.createFile(filePath);
            printFileCreated();
        } catch (IOException e) {
            printUnableToCreateFile();
        }
    }

    private void deleteDataFile() {
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
