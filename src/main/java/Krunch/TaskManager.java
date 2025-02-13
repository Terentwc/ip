package Krunch;

import Krunch.exceptions.IllegalException;
import Krunch.task.Deadline;
import Krunch.task.Event;
import Krunch.task.Task;
import Krunch.task.ToDo;

import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> tasks;
    UI ui = new UI();


    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void doTask(String[] words, String UserInput) throws IllegalException {
        switch (words[0]) {
            case "list":
                getList();
                break;
            case "mark":
                editMark(words[1], true);
                break;
            case "unmark":
                editMark(words[1], false);
                break;
            case "todo":
                String description = UserInput.substring(words[0].length()).trim();
                addToDo(description);
                break;
            case "deadline":
                addDeadline(words, UserInput);
                break;
            case "event":
                addEvent(words, UserInput);
                break;
            case "delete":
                deleteTask(words);
                break;
        }

    }

    public void getList() {
        for (int i = 1; i <= tasks.size(); i++) {
            // 1.[] blah
            ui.showMessage(i + ". " + tasks.get(i - 1));
        }
    }

    public void editMark(String stringNumber, boolean shouldMark) throws IllegalException {
        int listNumber = Integer.parseInt(stringNumber);
        if (tasks.isEmpty()) {
            throw new IllegalException("Oh you ain't getting me this time!\n" +
                    "Psst! Make a task first! Don't tell anyone I told you this!");
        }
        if (listNumber >= tasks.size()) {
            if (shouldMark) {
                throw new IllegalException("Marking imaginary task as done! Aren't I sooo... helpful?");
            } else {
                throw new IllegalException("You couldn't even do an imaginary task? Quickly! Start on it!");
            }
        }
        // checking if it is not done
        if (!tasks.get(listNumber).isDone()) {
            if (!shouldMark) {
                throw new IllegalException("So... when are you going to start exactly?");
            } else {
                ui.showMessage("Yes yes... very well done.");
            }
        } else { //changing to unmarked
            ui.showMessage("Hm... alright. It is unmarked.");
            tasks.get(listNumber).toggleDone();
        }
        // printing status
        ui.showMessage(tasks.get(listNumber).toString());
    }

    private void addToDo(String description) {
        Task addend = new ToDo(description);
        tasks.add(addend);
        ui.addedAcknowledgement(addend, tasks.size());
    }

    private void addDeadline(String[] words, String UserInput) {
        String[] parts = UserInput.split("/by");
        String description = parts[0].substring(words[0].length()).trim();
        String timeframe = parts[1].trim();
        Task addend = new Deadline(description, timeframe);
        tasks.add(addend);
        ui.addedAcknowledgement(addend, tasks.size());
    }

    private void addEvent(String[] words, String UserInput) {
        String[] parts = UserInput.split("/from"); // first split parts
        // event description
        String description = parts[0].substring(words[0].length()).trim();

        //timeframe from
        String[] parts2 = parts[1].split("/to");
        String from = parts2[0].trim(); //from

        //timeframe to
        String to = parts2[1].trim(); //to

        Task addend = new Event(description, from, to);
        tasks.add(addend);
        ui.addedAcknowledgement(addend, tasks.size());
    }

    private void deleteTask(String[] words) throws IllegalException {
        if (tasks.isEmpty()) {
            throw new IllegalException("Oh... And what do you want to delete? My memory is empty.\n" +
                    "Psst! Make a task first! Don't tell anyone I told you this!");
        }
        int num = Integer.parseInt(words[1]) - 1;
        if (num >= tasks.size()) {
            throw new IllegalException("Deleting!... Deleted!! What did I delete? The imaginary task of course!");
        }
        // Deleting the task
        Task removedTask = tasks.remove(num);
        ui.showMessage("Task deleted: " + removedTask);
        if (tasks.isEmpty()) {
            ui.showMessage("You now have 0 tasks left.\n" +
                    "Thank you for deleting parts of my memory. It sure feels nice to remember nothing.");
        } else {
            ui.showMessage("You now have " + tasks.size() + " tasks left.\n" +
                    "Thank you for deleting parts of my memory. It sure feels nice to remember lesser things.");
        }
    }

}
