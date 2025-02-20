package Krunch.task;

import Krunch.exceptions.IllegalException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event with a description and a time range (from and to dates).
 * The Event class extends the Task class and includes additional functionality
 * to store and format event dates.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event with the given description, start date, and end date.
     * The start and end dates must be in the format "yyyy-mm-dd".
     * If the date format is incorrect, an IllegalException will be thrown.
     *
     * @param task the description of the event
     * @param from the start date of the event in "yyyy-mm-dd" format
     * @param to   the end date of the event in "yyyy-mm-dd" format
     * @throws IllegalException if the date format is incorrect
     */
    public Event(String task, String from, String to) throws IllegalException {
        super(task);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new IllegalException("Wrong format bro... it's this format -> yyyy-mm-dd");
        }
    }

    /**
     * Returns a string representation of the event.
     * The format includes the event's completion status, description, and its time range (from and to dates).
     *
     * @return a string representing the event's status, description, and time range
     */
    @Override
    public String toString() {
        // formatted for displaying dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Returns the start date of the event.
     *
     * @return the start date of the event
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Returns the end date of the event.
     *
     * @return the end date of the event
     */
    public LocalDate getTo() {
        return this.to;
    }
}
