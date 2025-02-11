package Krunch;

import Krunch.task.Deadline;
import Krunch.task.Event;
import Krunch.task.Task;
import Krunch.task.ToDo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskSaver {
    private static final String FILE_NAME = "tasks.txt";

    // Saves task
    public static void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(taskToString(task));
                writer.newLine();
            }
            System.out.println("Saved task");
        } catch (IOException e) {
            System.out.println("Error saving tasks");
        }
    }

    public static String taskToString(Task task) {
        if (task instanceof ToDo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getTask();
        } else if (task instanceof Deadline) {
            return "D | " + (task.isDone() ? "1" : "0") + " | " + task.getTask()
                    + " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return "E | " + (task.isDone() ? "1" : "0") + " | " + task.getTask()
                    + " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
        }
        return "";
    }

}
