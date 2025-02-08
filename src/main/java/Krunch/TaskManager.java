package Krunch;

import Krunch.task.Task;
import Krunch.exceptions.IllegalException;

import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;


    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void getList() {
        for (int i = 1; i <= tasks.size(); i++) {
            // 1.[] blah
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    public void editMark(int listNumber, boolean shouldMark) throws IllegalException {
        try {
            if (tasks.isEmpty()) {
                throw new IllegalException("Oh you ain't getting me this time!\n" +
                        "Psst! Make a task first! Don't tell anyone I told you this!");
            }
            if (listNumber >= tasks.size()) {
                throw new IllegalException("You couldn't even do an imaginary task? Quickly! Start on it!");
            }
            // checking if it is not done
            if (!tasks.get(listNumber).isDone()) {
                System.out.println("So... when are you going to start exactly?");
            } else { //changing to unmarked
                System.out.println("Hm... alright. It is unmarked.");
                tasks.get(listNumber).toggleDone();
            }
            // printing status
            System.out.println(tasks.get(listNumber));
        } catch (IllegalException e) {
            System.out.println(e.getMessage());
        }
    }

}
