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
import duke.ui.FileUi;

/**
 * Represents a manager that can read and write to txt file.
 * Only recognises duke.txt as the data path.
 * Detects the presence of data directory.
 */
public class FileManager implements FileAction {
    private static final String FILE_NAME = "duke.txt";
    private static final String DATA_DIRECTORY = "data";
    private static final String FILE_PATH = "./data/duke.txt";

    private final Path filePath;
    private final Path directoryPath;
    private final FileUi fileUi;

    public FileManager() {
        this.fileUi = new FileUi();
        filePath = Paths.get(DATA_DIRECTORY, FILE_NAME);
        directoryPath = Paths.get(DATA_DIRECTORY);
        validatePaths();
    }

    /**
     * Reads data from ./data/duke.txt.
     * Parses the string formats into task objects
     * PreviousData can be an instance of an empty ArrayList of type task but not null.
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
     * Writes data to ./data/duke.txt.
     * Parses the task objects into string format.
     * If any error occur during the writing to file,
     * it will inform the user of the error.
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
            fileUi.printUnableToWriteFile();
        }
    }

    /**
     * Parse the ArrayList of user data into ArrayList of tasks.
     * Inform user of corrupt file if parsing of data file fails.
     *
     * @param fileData An ArrayList of user data of type string.
     * @return An ArrayList of tasks of type task.
     */
    private ArrayList<Task> parseFileData(ArrayList<String> fileData) {
        try {
            return DataParser.fileToTask(fileData);
        } catch (FileCorruptedException | ArrayIndexOutOfBoundsException e) {
            handleCorruptFile();
        }
        return new ArrayList<>();
    }

    /**
     * Reads in the user data from ./data/duke.txt.
     * Inform user of corrupt file if reading of data file fails.
     *
     * @return An ArrayList of user data in type string
     */
    private ArrayList<String> getFileData() {
        ArrayList<String> fileData = new ArrayList<>();
        Scanner s;

        try {
            s = new Scanner(filePath);
            while (s.hasNext()) {
                fileData.add(s.nextLine());
            }
        } catch (IOException e) {
            fileUi.printFileNotFound();
        } catch (ArrayIndexOutOfBoundsException e) {
            handleCorruptFile();
        }

        return fileData;
    }

    /**
     * Checks if ./data/duke.txt exist, if not creates it.
     */
    private void validatePaths() {
        if(!Files.exists(directoryPath)) {
            createDataDirectory();
        }
        if (!Files.exists(filePath)) {
            createDataFile();
        }
    }

    /**
     * Informs user that file is corrupted.
     * Proceeds to delete and recreate the data file.
     */
    private void handleCorruptFile() {
        fileUi.printCorruptFile();
        deleteDataFile();
        createDataFile();
    }

    /* Creates the directory ./data/ */
    private void createDataDirectory() {
        try {
            Files.createDirectory(directoryPath);
            fileUi.printDirCreated();
        } catch (IOException e) {
            fileUi.printUnableToCreateDir();
        }
    }

    /* Creates the data file ./data/duke.txt */
    private void createDataFile() {
        try {
            Files.createFile(filePath);
            fileUi.printFileCreated();
        } catch (IOException e) {
            fileUi.printUnableToCreateFile();
        }
    }

    /* Deletes the data file ./data/duke.txt */
    private void deleteDataFile() {
        try {
            Files.deleteIfExists(filePath);
            fileUi.printFileDeleted();
        } catch (IOException e) {
            fileUi.printUnableToDeleteFile();
        }
    }
}
