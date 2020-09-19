package duke.parser;

import java.util.ArrayList;

import duke.storage.FileCorruptedException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class DataParser {
    private static final String REGEX_DELIMITER = " \\| ";

    public static ArrayList<Task> fileToTask(ArrayList<String> dataStreams) throws FileCorruptedException, ArrayIndexOutOfBoundsException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String fileData : dataStreams) {
            Task task;
            String[] params = fileData.split(REGEX_DELIMITER);
            String taskType = params[0];
            Boolean done = Integer.parseInt(params[1]) == 1;
            String description = params[2];

            switch(taskType) {
            case "T":
                task = new Todo(done, description);
                break;
            case "D":
                task = new Deadline(done, description, params[3]);
                break;
            case "E":
                task = new Event(done, description, params[3]);
                break;
            default:
                throw new FileCorruptedException();
            }

            tasks.add(task);
        }

        return tasks;
    }
}
