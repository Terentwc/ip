import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String task, String by) {
        super(task);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            System.out.println("Deadline this!\n" + "I warned you!!");
            System.exit(1);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
