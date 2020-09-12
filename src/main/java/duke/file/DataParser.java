package duke.file;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class DataParser {

    public static ArrayList<Task> fileToTask(ArrayList<String> dataStreams) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String fileData : dataStreams) {
            Task task;
            String[] params = fileData.split(" \\| ");
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
                task = null;
            }

            tasks.add(task);
        }

        return tasks;
    }
}
