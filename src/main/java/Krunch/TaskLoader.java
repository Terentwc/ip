package Krunch;

import Krunch.task.Deadline;
import Krunch.task.Event;
import Krunch.task.Task;
import Krunch.task.ToDo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskLoader {

    public static ArrayList<Task> loadTasks() {
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
