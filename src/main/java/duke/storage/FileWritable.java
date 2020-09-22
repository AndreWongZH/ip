package duke.storage;

/**
 * Represents all the available methods for converting into file data.
 */
public interface FileWritable {
    String SEPARATOR = " | ";

    String convertToData();
}
