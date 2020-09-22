package duke.parser;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.storage.FileCorruptedException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class DataParser {
    private static final String REGEX_DELIMITER = " \\| ";

    public static ArrayList<Task> fileToTask(ArrayList<String> dataStreams) throws FileCorruptedException, ArrayIndexOutOfBoundsException, DateTimeFormatException {
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
