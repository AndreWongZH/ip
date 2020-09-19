package duke.storage;

import java.util.ArrayList;

import duke.task.Task;

public interface FileAction {
    ArrayList<Task> loadData();
    void writeToFile(ArrayList<Task> tasks);
}
