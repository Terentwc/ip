package Krunch;

import Krunch.exceptions.IllegalException;
import Krunch.task.Deadline;
import Krunch.task.Event;
import Krunch.task.Task;
import Krunch.task.ToDo;

import java.util.ArrayList;

/**
 * This class manages the tasks given to the chatbot.
 * It allows users to add, mark, unmark, list, delete, and find tasks.
 * Tasks are stored in an ArrayList and can be saved using the TaskSaver.
 */

public class TaskManager {
    protected ArrayList<Task> tasks;
    UI ui = new UI();
    TaskSaver taskSaver = new TaskSaver();

    /**
     * Constructor for TaskManager.
     * Initializes the TaskManager with a list of tasks.
     *
     * @param tasks the list of tasks to manage
     */
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Execute a task based on user input.
     * The input is parsed and the appropriate action is performed.
     *
     * @param words         the split command words from the user input
     * @param UserInput     the full user input string
     * @throws IllegalException if an error occurs due to invalid input or task
     */
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
            case "find":
                findTask(words, UserInput);
                break;
        }
        taskSaver.saveTasks(tasks);

    }

    /**
     * Lists all tasks in the task manager.
     * Throws an exception if there are no tasks.
     *
     * @throws IllegalException if there are no tasks to list
     */
    public void getList() throws IllegalException {
        if (tasks.isEmpty()){
            throw new IllegalException("No tasks. Good job");
        }
        for (int i = 1; i <= tasks.size(); i++) {
            // 1.[] blah
            ui.showMessage(i + ". " + tasks.get(i - 1));
        }
    }

    /**
     * Marks or unmarks a task based on the provided task number.
     *
     * @param stringNumber the task number to mark/unmark
     * @param shouldMark   true if the task should be marked, false if it should be unmarked
     * @throws IllegalException if the task number is invalid or other errors occur
     */
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


    /**
     * Adds a new "To Do" task to the task list.
     *
     * @param description the description of the "To Do" task
     */
    private void addToDo(String description) {
        Task addend = new ToDo(description);
        tasks.add(addend);
        ui.addedAcknowledgement(addend, tasks.size());
    }

    /**
     * Adds a new "Deadline" task to the task list.
     *
     * @param words      the split command words from the user input
     * @param UserInput  the full user input string containing the deadline details
     * @throws IllegalException if the input is invalid
     */
    private void addDeadline(String[] words, String UserInput) throws IllegalException {
        String[] parts = UserInput.split("/by");
        String description = parts[0].substring(words[0].length()).trim();
        String timeframe = parts[1].trim();
        Task addend = new Deadline(description, timeframe);
        tasks.add(addend);
        ui.addedAcknowledgement(addend, tasks.size());
    }

    /**
     * Adds a new "Event" task to the task list.
     *
     * @param words      the split command words from the user input
     * @param UserInput  the full user input string containing the event details
     * @throws IllegalException if the input is invalid
     */
    private void addEvent(String[] words, String UserInput) throws IllegalException {
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

    /**
     * Deletes a task based on the task number provided by the user.
     *
     * @param words the split command words from the user input
     * @throws IllegalException if the task number is invalid or there are no tasks to delete
     */
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

    /**
     * Finds tasks that contain a specific keyword in their description.
     *
     * @param words       the split command words from the user input
     * @param UserInput   the full user input string containing the keyword
     * @throws IllegalException if there are no tasks or no matching tasks
     */
    public void findTask(String[] words, String UserInput) throws IllegalException {
        String keyword = UserInput.substring(words[0].length()).trim();
        ArrayList<Task> matchingtasks = new ArrayList<>();

        ui.showMessage("Please wait as I find the tasks...");

        if (tasks.isEmpty()) {
            throw new IllegalException("Oh... I found nothing... because there are no tasks!!");
        }

        for (Task matching : tasks) {
            if (matching.getTask().contains(keyword)) {
                matchingtasks.add(matching);
            }
        }

        if (matchingtasks.isEmpty()) {
            ui.showMessage("Hey there, just checked my mind. There are no tasks with " + keyword
                    + ".\nThanks for waiting while I checked.");
        }

        for (int i = 1; i <= matchingtasks.size(); i++) {
            // 1.[] blah
            ui.showMessage(i + ". " + matchingtasks.get(i - 1));
        }
    }

}
