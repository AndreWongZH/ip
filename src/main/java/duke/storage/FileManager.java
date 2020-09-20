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
            fileUi.printUnableToWriteFile();
        }
    }

    private ArrayList<Task> parseFileData(ArrayList<String> fileData) {
        try {
            return DataParser.fileToTask(fileData);
        } catch (FileCorruptedException | ArrayIndexOutOfBoundsException e) {
            handleCorruptFile();
        }
        return new ArrayList<>();
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
            fileUi.printFileNotFound();
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
        fileUi.printCorruptFile();
        deleteDataFile();
        createDataFile();
    }

    private void createDataDirectory() {
        try {
            Files.createDirectory(directoryPath);
            fileUi.printDirCreated();
        } catch (IOException e) {
            fileUi.printUnableToCreateDir();
        }
    }

    private void createDataFile() {
        try {
            Files.createFile(filePath);
            fileUi.printFileCreated();
        } catch (IOException e) {
            fileUi.printUnableToCreateFile();
        }
    }

    private void deleteDataFile() {
        try {
            Files.deleteIfExists(filePath);
            fileUi.printFileDeleted();
        } catch (IOException e) {
            fileUi.printUnableToDeleteFile();
        }
    }
}
