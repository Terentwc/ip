package Krunch;

import Krunch.exceptions.IllegalException;

/**
 * The Krunch class is the main entry point for the program.
 * It initializes the TaskManager, UI, and Parser classes and handles the main loop for executing user commands.
 */
public class Krunch {
    private final TaskManager taskManager;
    private final UI ui;
    private final Parser parser;

    /**
     * Constructs a new Krunch instance by initializing the TaskManager, UI, and Parser.
     * It also loads the existing tasks from storage.
     *
     * @throws IllegalException if there is an error while loading tasks
     */
    public Krunch() throws IllegalException{
        taskManager = new TaskManager(TaskLoader.loadTasks());
        ui = new UI();
        parser = new Parser();
    }

    // Main
    /**
     * The main method that starts the Krunch program.
     * It creates an instance of Krunch and calls the run method to begin handling user input.
     *
     * @param args the command-line arguments (not used in this program)
     * @throws IllegalException if there is an error while initializing or running the program
     */
    public static void main(String[] args) throws IllegalException {

        new Krunch().run();
    }

    /**
     * Runs the Krunch program. This method greets the user, enters an infinite loop to process user input,
     * and handles the commands using the TaskManager, Parser, and UI classes.
     * It continues to run until the program is closed.
     *
     * @throws IllegalException if there is an error during task processing
     */
    public void run() throws IllegalException {
        ui.greet();
        while (true) {
            try {
                // get user input and parse it
                String UserInput = ui.nextCommand();
                String[] parsedInput = parser.parsedInfo(UserInput);

                // if parsed input is empty, continue to the next iteration
                if (parsedInput.length == 0) {
                    continue;
                }

                // executes task based on parsed input
                taskManager.doTask(parsedInput, UserInput);
            } catch (IllegalException e) {
                // If an error occurs, displays the error message
                ui.showMessage(e.getMessage());
            }
        }
    }
}
