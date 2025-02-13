package Krunch;

import Krunch.exceptions.IllegalException;

public class Krunch {
    private final TaskManager taskManager;
    private final UI ui;
    private final Parser parser;


    public Krunch() throws IllegalException{
        taskManager = new TaskManager(TaskLoader.loadTasks());
        ui = new UI();
        parser = new Parser();
    }

    // Main
    public static void main(String[] args) throws IllegalException {

        new Krunch().run();
    }

    public void run() throws IllegalException {
        ui.greet();
        while (true) {
            try {
                String UserInput = ui.nextCommand();
                String[] parsedInput = parser.parsedInfo(UserInput);
                if (parsedInput.length == 0) {
                    continue;
                }
                taskManager.doTask(parsedInput, UserInput);
            } catch (IllegalException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }
}
