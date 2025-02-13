package Krunch;

import Krunch.exceptions.IllegalException;
import Krunch.task.Deadline;
import Krunch.task.Event;
import Krunch.task.Task;
import Krunch.task.ToDo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The TaskLoader class is responsible for loading tasks from a file (tasks.txt) and
 * restoring them into task objects. It parses the file, reads each line, and creates
 * the appropriate task object (ToDo, Deadline, or Event) based on the data format.
 */
public class TaskLoader {

    /**
     * Loads tasks from the "tasks.txt" file and returns a list of Task objects.
     * The file format should contain task data in the following structure:
     *
     *  - T | <done-status> | <task-name>
     *  - D | <done-status> | <task-name> | <deadline-date>
     *  - E | <done-status> | <task-name> | <from-date> | <to-date>
     *
     * The method will create the corresponding task objects and restore their
     * completion status from the file data.
     *
     * @return a list of tasks loaded from the file
     * @throws IllegalException if an error occurs while parsing the file or creating tasks
     */
    public static ArrayList<Task> loadTasks() throws IllegalException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| "); // Split by " | "
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String taskName = parts[2];

                Task task;
                if (type.equals("T")) {
                    task = new ToDo(taskName);
                } else if (type.equals("D")) {
                    task = new Deadline(taskName, parts[3]);
                } else if (type.equals("E")) {
                    task = new Event(taskName, parts[3], parts[4]);
                } else {
                    continue; // Skip invalid lines
                }
                // Restore task completion status
                if (isDone) {
                    task.toggleDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            return tasks;
        }
        return tasks;
    }
}
