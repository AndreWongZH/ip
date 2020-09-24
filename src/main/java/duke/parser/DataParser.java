package duke.parser;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.storage.FileCorruptedException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the process of converting user data into tasks.
 */
public class DataParser {
    private static final String REGEX_DELIMITER = " \\| ";

    private final ArrayList<String> dataStreams;

    public DataParser(ArrayList<String> dataStreams) {
        this.dataStreams = dataStreams;
    }

    /**
     * Converts ArrayList of user data into an ArrayList of tasks.
     *
     * @return ArrayList of tasks of type task.
     * @throws FileCorruptedException If error occurs when task type is not found.
     * @throws ArrayIndexOutOfBoundsException If error occurs during splitting of user data.
     * @throws NumberFormatException If boolean parsed is not 1 or 0;
     */
    public ArrayList<Task> convertFileToTask() throws FileCorruptedException, ArrayIndexOutOfBoundsException, DateTimeFormatException, NumberFormatException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String fileData : dataStreams) {
            Task task;
            String[] params = fileData.split(REGEX_DELIMITER);
            String taskType = params[0];
            Boolean done = Integer.parseInt(params[1]) == 1;
            String description = params[2];
            LocalDateTime dateTime;

            switch(taskType) {
            case Todo.TODO_TAG:
                task = new Todo(done, description);
                break;
            case Deadline.DEADLINE_TAG:
                dateTime = new DateTimeParser(params[3]).formatDate();
                task = new Deadline(done, description, dateTime);
                break;
            case Event.EVENT_TAG:
                dateTime = new DateTimeParser(params[3]).formatDate();
                task = new Event(done, description, dateTime);
                break;
            default:
                throw new FileCorruptedException();
            }

            tasks.add(task);
        }

        return tasks;
    }
}
