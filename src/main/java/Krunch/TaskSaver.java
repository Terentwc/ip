package Krunch;

import Krunch.task.Deadline;
import Krunch.task.Event;
import Krunch.task.Task;
import Krunch.task.ToDo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The TaskSaver class is responsible for saving tasks to a file.
 * It converts task objects into a specific string format and writes them to a file for persistent storage.
 */
public class TaskSaver {
    private static final String FILE_NAME = "tasks.txt";
    UI ui = new UI();

    /**
     * Converts a task object into a string representation that can be saved to a file.
     * The format depends on the type of task (ToDo, Deadline, or Event).
     *
     * @param task the task to be converted into a string
     * @return the string representation of the task
     */
    public static String taskToString(Task task) {
        if (task instanceof ToDo) {
            // converts ToDo task to string format
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getTask();
        } else if (task instanceof Deadline) {
            // converts Deadline task to string format
            return "D | " + (task.isDone() ? "1" : "0") + " | " + task.getTask()
                    + " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            // converts Event task to string format
            return "E | " + (task.isDone() ? "1" : "0") + " | " + task.getTask()
                    + " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
        }
        return "";
    }

    /**
     * Saves the list of tasks to a file.
     * Each task is written to the file in a specific format.
     *
     * @param tasks the list of tasks to be saved
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(taskToString(task));
                writer.newLine();
            }
            // ui.showMessage("Saved task");
        } catch (IOException e) {
            ui.showMessage("Error saving tasks");
        }
    }

}
