package task;

import Krunch.exceptions.IllegalException;
import Krunch.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventTest {

    @Test
    void testEventCreation() throws IllegalException {
        Event event = new Event("Project meeting", "2024-02-10", "2024-02-12");

        assertEquals("Project meeting", event.getTask()); // Assuming Task has getDescription()
        assertEquals(LocalDate.of(2024, 2, 10), event.getFrom());
        assertEquals(LocalDate.of(2024, 2, 12), event.getTo());
    }

    @Test
    void testToString() throws IllegalException {
        Event event = new Event("Concert", "2025-06-15", "2025-06-16");
        String expectedOutput = "[E][ ] Concert (from: Jun 15 2025 to: Jun 16 2025)";
        assertEquals(expectedOutput, event.toString());
    }

    @Test
    void testInvalidDate() {
        Exception exception = assertThrows(IllegalException.class, () -> {
            new Event("Invalid event", "wrong-date", "2025-06-16");
        });

        assertEquals("Wrong format bro... it's this format -> yyyymmdd", exception.getMessage());
    }

}

