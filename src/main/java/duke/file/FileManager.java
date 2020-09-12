package duke.file;

import duke.task.Task;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    public static ArrayList<Task> getData(Path filePath) throws IOException {
        ArrayList<String> dataStreams = new ArrayList<>();
        Scanner s = new Scanner(filePath);

        while (s.hasNext()) {
            dataStreams.add(s.nextLine());
        }

        return DataParser.fileToTask(dataStreams);
    }
}
