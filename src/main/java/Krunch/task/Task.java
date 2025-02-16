package Krunch.task;

/**
 * Represents a generic task. The Task class serves as a base class for different types of tasks
 * like ToDo, Deadline, and Event. Each task has a description and a status indicating whether it is completed.
 */
public class Task {
    protected String task;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially set to not done.
     *
     * @param task the description of the task
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false; // initialise task as not done
    }

    /**
     * Returns the string representation of the completion status.
     * If the task is completed, returns "X", otherwise returns a space " ".
     *
     * @return the string representing the completion status ("X" if done, " " if not done)
     */
    public String stringDone() {
        return isDone ? "X" : " ";
    }

    /**
     * Toggles the task's completion status.
     * If the task is done, it will be marked as not done, and vice versa.
     */
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Returns a string representation of the task.
     * The format includes the task's completion status (e.g., "[ ] Task description")
     * and its description.
     *
     * @return a string representing the task's status and description
     */
    @Override
    public String toString() {
        return "[" + stringDone() + "] " + getTask();
    }
}
