package duke.ui;

public class FileUi extends Ui {
    private static final String ERROR_UNABLE_TO_WRITE_FILE = "Error unable to open file to write";
    private static final String ERROR_FILE_NOT_FOUND = "Error file not found";
    private static final String ERROR_CORRUPT_FILE = "Error, data file might be corrupted";
    private static final String ERROR_UNABLE_TO_CREATE_DATA_FILE = "Unable to create data file";
    private static final String ERROR_UNABLE_TO_DELETE_FILE = "Unable to delete file";
    private static final String ERROR_UNABLE_TO_CREATE_DIRECTORY = "Unable to create directory";

    private static final String SUCCESS_DATA_FILE_CREATED = "Data file created under data/duke.txt";
    private static final String SUCCESS_FILE_DELETED = "Corrupt file deleted";
    private static final String SUCCESS_DATA_DIRECTORY_CREATED = "Data directory created";

    public void printDirCreated() {
        generateTextBorder(SUCCESS_DATA_DIRECTORY_CREATED);
    }

    public void printUnableToCreateDir() {
        generateTextBorder(ERROR_UNABLE_TO_CREATE_DIRECTORY);
    }

    public void printFileDeleted() {
        generateTextBorder(SUCCESS_FILE_DELETED);
    }

    public void printUnableToDeleteFile() {
        generateTextBorder(ERROR_UNABLE_TO_DELETE_FILE);
    }

    public void printFileCreated() {
        generateTextBorder(SUCCESS_DATA_FILE_CREATED);
    }

    public void printUnableToCreateFile() {
        generateTextBorder(ERROR_UNABLE_TO_CREATE_DATA_FILE);
    }

    public void printUnableToWriteFile() {
        generateTextBorder(ERROR_UNABLE_TO_WRITE_FILE);
    }

    public void printFileNotFound() {
        generateTextBorder(ERROR_FILE_NOT_FOUND);
    }

    public void printCorruptFile() {
        generateTextBorder(ERROR_CORRUPT_FILE);
    }
}
