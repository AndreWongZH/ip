package duke.storage;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents all the available actions that can be called from a FileManager instance.
 */
public interface FileAction {
    ArrayList<Task> loadData();
    void writeToFile(ArrayList<Task> tasks);
}
