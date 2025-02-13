package Krunch.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline with a description and a due date.
 * The Deadline class extends the Task class and includes additional functionality
 * to store and format a due date for the task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline with the given description and due date.
     * The due date must be in the format "yyyy-mm-dd".
     * If the date format is incorrect, the program will terminate with an error message.
     *
     * @param task the description of the task
     * @param by the due date of the task in "yyyy-mm-dd" format
     */
    public Deadline(String task, String by) {
        super(task);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            System.out.println("Deadline this!\n" + "I warned you!!");
            System.exit(1);
        }
    }

    /**
     * Returns a string representation of the deadline.
     * The format includes the deadline's completion status, description, and its due date.
     *
     * @return a string representing the deadline's status, description, and due date
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns the due date of the deadline.
     *
     * @return the due date of the deadline
     */
    public LocalDate getBy() {
        return this.by;
    }
}
