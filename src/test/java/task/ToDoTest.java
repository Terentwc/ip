package task;

import Krunch.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class ToDoTest {

    @Test
    void testToDoCreation() {
        ToDo todo = new ToDo("Finish homework");
        assertEquals("Finish homework", todo.getTask()); // Assuming Task has getDescription()
        assertFalse(todo.isDone()); // Assuming Task has isDone()
    }

    @Test
    void testToString() {
        ToDo todo = new ToDo("Buy milk");
        String expectedOutput = "[T][ ] Buy milk"; // Assuming Task's toString() includes completion status
        assertEquals(expectedOutput, todo.toString());
    }
}
