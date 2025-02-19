package Krunch;

import Krunch.task.Task;

import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);

    /**
     * Greets the user and introduces the program.
     * Displays initial messages about the program's functionality and requirements.
     */
    public String greet() {
        return "Oh, what a treat. Finally, you've graced me with your presence.\n"
                + "Name's Krunch. In case you didn't already figure that out.\n"
                + "So, what mighty task are we tackling today? Just don't expect me to do everything for you...\n"
                + "But I guess I can help, if I have to.\n";
    }

    /**
     * Reads the next command entered by the user.
     *
     * @return the command entered by the user as a String
     */
    public String nextCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message the message to be displayed to the user
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Acknowledges that a task has been added to the list.
     * Displays the added task and the current number of tasks in the list.
     *
     * @param added the task that was added
     * @param size  the total number of tasks in the list
     */
    public String addedAcknowledgement(Task added, int size) {
        return "It is added... anything else?\n"
                + added + "\n"
                + "In case you wanted to know, you have " + size + " tasks in the list.\n";
    }
}
