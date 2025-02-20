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
     * @param words     the split command words from the user input
     * @param UserInput the full user input string
     * @throws IllegalException if an error occurs due to invalid input or task
     */
    public String doTask(String[] words, String UserInput) throws IllegalException {
        String response = "";
        switch (words[0]) {
        case "list":
            response = getList();
            break;
        case "mark":
            response = editMark(words[1], true);
            break;
        case "unmark":
            response = editMark(words[1], false);
            break;
        case "todo":
            String description = UserInput.substring(words[0].length()).trim();
            response = addToDo(description);
            break;
        case "deadline":
            response = addDeadline(words, UserInput);
            break;
        case "event":
            response = addEvent(words, UserInput);
            break;
        case "delete":
            response = deleteTask(words);
            break;
        case "find":
            response = findTask(words, UserInput);
            break;
        }
        taskSaver.saveTasks(tasks);
        return response;
    }

    /**
     * Lists all tasks in the task manager.
     * Throws an exception if there are no tasks.
     *
     * @return
     * @throws IllegalException if there are no tasks to list
     */
    public String getList() throws IllegalException {
        if (tasks.isEmpty()) {
            throw new IllegalException("No tasks. Good job");
        }
        StringBuilder response = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            // 1.[] blah
            response.append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        return response.toString();
    }

    /**
     * Marks or unmarks a task based on the provided task number.
     *
     * @param stringNumber the task number to mark/unmark
     * @param shouldMark   true if the task should be marked, false if it should be unmarked
     * @throws IllegalException if the task number is invalid or other errors occur
     */
    public String editMark(String stringNumber, boolean shouldMark) throws IllegalException {
        int listNumber = Integer.parseInt(stringNumber);
        StringBuilder response = new StringBuilder();
        if (tasks.isEmpty()) {
            throw new IllegalException("Oh you ain't getting me this time!\n" +
                    "Psst! Make a task first! Don't tell anyone I told you this!");
        }
        // imaginary list number
        if (listNumber >= tasks.size()) {
            if (shouldMark) {
                throw new IllegalException("Marking imaginary task as done! Aren't I sooo... helpful?\n");
            } else {
                throw new IllegalException("You couldn't even do an imaginary task? Quickly! Start on it!");
            }
        }
        // checking if task is done
        if (tasks.get(listNumber - 1).isDone()) {
            // changing to mark
            if (!shouldMark) {
                response.append("Hm... alright. It is unmarked.\n");
            } else {
                throw new IllegalException("Hey, hey. You marked this already!");
            }
        } else { //changing to marked
            if (!shouldMark) {
                throw new IllegalException("So... when are you going to start exactly?");
            } else {
                response.append("Nice job. You deserve a praise... but not from me.\n");
            }
        }
        tasks.get(listNumber - 1).toggleDone();
        // printing status
        return response.append(tasks.get(listNumber - 1).toString()).toString();
    }


    /**
     * Adds a new "To Do" task to the task list.
     *
     * @param description the description of the "To Do" task
     */
    private String addToDo(String description) {
        Task addend = new ToDo(description);
        tasks.add(addend);
        return ui.addedAcknowledgement(addend, tasks.size());
    }

    /**
     * Adds a new "Deadline" task to the task list.
     *
     * @param words     the split command words from the user input
     * @param UserInput the full user input string containing the deadline details
     * @throws IllegalException if the input is invalid
     */
    private String addDeadline(String[] words, String UserInput) throws IllegalException {
        String[] parts = UserInput.split("/by");
        String description = parts[0].substring(words[0].length()).trim();
        String timeframe = parts[1].trim();
        Task addend = new Deadline(description, timeframe);
        tasks.add(addend);
        return ui.addedAcknowledgement(addend, tasks.size());
    }

    /**
     * Adds a new "Event" task to the task list.
     *
     * @param words     the split command words from the user input
     * @param UserInput the full user input string containing the event details
     * @throws IllegalException if the input is invalid
     */
    private String addEvent(String[] words, String UserInput) throws IllegalException {
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
        return ui.addedAcknowledgement(addend, tasks.size());
    }

    /**
     * Deletes a task based on the task number provided by the user.
     *
     * @param words the split command words from the user input
     * @throws IllegalException if the task number is invalid or there are no tasks to delete
     */
    private String deleteTask(String[] words) throws IllegalException {
        StringBuilder response = new StringBuilder();
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
        response.append("Task deleted: " + removedTask + "\n");
        if (tasks.isEmpty()) {
            response.append("You now have 0 tasks left.\n" +
                    "Thank you for deleting parts of my memory. It sure feels nice to remember nothing.");
        } else {
            response.append("You now have " + tasks.size() + " tasks left.\n" +
                    "Thank you for deleting parts of my memory. It sure feels nice to remember lesser things.");
        }
        return response.toString();
    }

    /**
     * Finds tasks that contain a specific keyword in their description.
     *
     * @param words     the split command words from the user input
     * @param UserInput the full user input string containing the keyword
     */
    public String findTask(String[] words, String UserInput) {
        String keyword = UserInput.substring(words[0].length()).trim();
        ArrayList<Task> matchingtasks = new ArrayList<>();
        // Initialising response and message
        StringBuilder response = new StringBuilder("Please wait as I find the tasks...");

        if (tasks.isEmpty()) {
            response.append("Oh... I found nothing... because there are no tasks!!");
        }

        for (Task matching : tasks) {
            if (matching.getTask().contains(keyword)) {
                matchingtasks.add(matching);
            }
        }

        if (matchingtasks.isEmpty()) {
            response.append("Hey there, just checked my mind. There are no tasks with " + keyword
                    + ".\nThanks for waiting while I checked.");
        }

        for (int i = 1; i <= matchingtasks.size(); i++) {
            // 1.[] blah
            response.append(i + ". " + matchingtasks.get(i - 1));
        }
        return response.toString();
    }

}
