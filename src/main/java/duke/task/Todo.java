package duke.task;

import java.time.LocalDate;

/**
 * Represents a task of type todo. It has the description of the todo,
 * an isDone attribute to check if the todo is done.
 */
public class Todo extends Task {
    public static final String TODO_TAG = "T";
    private static final String TODO_TAG_ENCLOSED = "[T]";

    public Todo(String description) {
        super(description);
    }

    /* Constructs an instance when reading from a file */
    public Todo(Boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns the string representation of the task for printing to output.
     *
     * @return Formatted task string.
     */
    @Override
    public String toString() {
        return TODO_TAG_ENCLOSED + super.toString();
    }

    /**
     * Returns the string representation of the task for writing to file.
     *
     * @return Formatted task string.
     */
    @Override
    public String convertToData() {
        return TODO_TAG + super.convertToData();
    }

    /**
     * Returns today's date as a LocalDate object.
     *
     * @return LocalDate object.
     */
    @Override
    LocalDate convertToDate() {
        return LocalDate.now();
    }

    /**
     * Compares if two todos are the same.
     * Compares the class type first before comparing the description.
     *
     * @param obj Any object class.
     * @return A boolean indicating if they are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Todo todo = (Todo) obj;
        return todo.description.equals(this.description);
    }
}
