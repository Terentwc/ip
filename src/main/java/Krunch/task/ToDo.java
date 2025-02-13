package Krunch.task;

/**
 * Represents a ToDo task, which is a type of task that the user needs to complete.
 * This class extends the abstract Task class and adds specific behavior for ToDo tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the given description.
     *
     * @param task the description of the ToDo task
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Returns a string representation of the ToDo task.
     * This format includes the task type identifier "[T]" followed by the string representation of the task.
     *
     * @return a string representing the ToDo task
     */
    @Override
    public String toString() {
        // appends [T] to the string representation of the task
        return "[T]" + super.toString();
    }
}
