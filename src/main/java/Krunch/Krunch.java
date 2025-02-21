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
    public Krunch() throws IllegalException {
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
     * Executes the main logic of the Krunch program.
     * This method continuously listens for and processes user commands until the program is terminated.
     * It performs the following steps in a loop:
     * - Greets the user at the start of the session.
     * - Reads and parses the user input.
     * - If the input is valid, executes the corresponding task using the TaskManager.
     * - If an error occurs during parsing or task execution, displays an error message to the user.
     *
     * @throws IllegalException if an illegal operation occurs, such as invalid input or a failure in task execution.
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

    /**
     * Processes the input command provided by the user and generates the corresponding response.
     * This method parses the input string, determines the appropriate task to perform,
     * and returns the result or error message, if applicable.
     *
     * @param input the command string entered by the user
     * @return the result of executing the command, or an error message if something goes wrong
     */
    public String getResponse(String input) {
        try {
            String[] parsedInput = parser.parsedInfo(input);
            return taskManager.doTask(parsedInput, input);
        } catch (IllegalException e) {
            return e.getMessage();
        }
    }

}
