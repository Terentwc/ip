package Krunch;

import Krunch.exceptions.IllegalException;

/**
 * The Parser class is responsible for parsing and validating user input commands.
 * It processes commands related to tasks such as marking, unmarking, deleting,
 * adding, and finding tasks. It ensures that user input is correctly formatted
 * and returns the appropriate response or throws exceptions if the input is invalid.
 */
public class Parser {
    UI ui = new UI();

    /**
     * Parses the user input and determines the appropriate command to execute.
     * It validates the input and returns the parsed information or throws an exception
     * if the input is invalid.
     *
     * @param UserInput the full user input string
     * @return an array of strings containing parsed command details
     * @throws IllegalException if the user input is invalid or improperly formatted
     */
    public String[] parsedInfo(String UserInput) throws IllegalException {
        String[] words = UserInput.split(" ");

        if (words[0].startsWith("bye")) {
            byeMessage(words);
            return new String[0]; // will never execute but needed
        } else if (words[0].equals("list")) {
            return listMessage(words);
        } else if (words[0].equals("unmark")) {
            return parseMark(words);
        } else if (words[0].equals("mark")) {
            return parseMark(words);
        } else if (words[0].equals("todo")) {
            return parseToDo(words);
        } else if (words[0].equals("deadline")) {
            return parseDeadline(words, UserInput);
        } else if (words[0].equals("event")) {
            return parseEvent(words, UserInput);
        } else if (words[0].equals("delete")) {
            return parseDelete(words);
        } else if (words[0].equals("find")) {
            return parseFind(words);
        } else if (words[0].isEmpty()) {
            throw new IllegalException("W-wha?... h-hey! Don't give me the silent treatment!");
        } else {
            throw new IllegalException(UserInput);
        }
    }

    /**
     * Displays a goodbye message based on the user's input.
     * If the user just types "bye", a formal farewell is shown before exiting.
     * Otherwise, a cheeky message is displayed if there are extra words.
     *
     * @param words the parsed input words from the user
     */
    private String byeMessage(String[] words) {
        String response = "";
        if (words.length == 1 && words[0].equals("bye")) {
            ui.showMessage("Oh, I see how it is. No need to pretend you'll miss me. Go on, then. Goodbye.");
            System.exit(0);
        } else {
            response = "Goodbye...\n"
                    + "You can't get rid of me that easily! >:)";
        }
        return response;
    }

    /**
     * Validates the "list" command. If the command is not correctly formatted,
     * an exception is thrown.
     *
     * @param words the parsed input words from the user
     * @return the input words if the command is valid
     * @throws IllegalException if the command is invalid
     */
    private String[] listMessage(String[] words) throws IllegalException {
        if (words.length == 1) {
            // return "list"
            return words;
        } else {
            throw new IllegalException("Printing list now!...\n" + "Oh wait! Wrong command!");
        }
    }

    /**
     * Validates the "mark" and "unmark" commands.
     * Checks that a valid task number is provided.
     *
     * @param words the parsed input words from the user
     * @return the input words if the command is valid
     * @throws IllegalException if the command is invalid or the task number is missing/incorrect
     */
    private String[] parseMark(String[] words) throws IllegalException {
        String word = words[0];
        if (words.length != 2) {
            throw new IllegalException("Alright. It is" + word + "ed... What did you want to " + word + " exactly?\n"
                    + "Come on, do it like this. " + word + " (task number). It's just that easy");
        } else {
            // calls to edit list
            try {
                // test if words[1] is an integer
                Integer.parseInt(words[1]);
                return words;
            } catch (NumberFormatException e) {
                throw new IllegalException("Ah... It has to be a number. (E.g. " + word + " 1)");
            }
        }
    }

    /**
     * Validates the "todo" command.
     * Ensures that a description is provided for the "ToDo" task.
     *
     * @param words the parsed input words from the user
     * @return the input words if the command is valid
     * @throws IllegalException if the command is missing a description
     */
    private String[] parseToDo(String[] words) throws IllegalException {
        if (words.length < 2) {
            throw new IllegalException("Hey hey, tli man... Give me something to work with.");
        }
        return words;
    }

    /**
     * Validates the "deadline" command.
     * Ensures that a description and deadline date are provided.
     * The deadline should be specified with the "/by" flag.
     *
     * @param words     the parsed input words from the user
     * @param UserInput the full user input string
     * @return the input words if the command is valid
     * @throws IllegalException if the command is missing components or is improperly formatted
     */
    private String[] parseDeadline(String[] words, String UserInput) throws IllegalException {
        if (words.length < 2) {
            throw new IllegalException("Yes...? You seem to be missing... a description.\n"
                    + " And a /by along with a date. But you are smart, you probably did this to test me... Right?");
        }
        if (!UserInput.contains("/by")) {
            throw new IllegalException("Getting somewhere... but not there yet. Try again.\n"
                    + "Hint: Remember to use /by but you didn't hear this hint from me.");
        }
        String[] parts = UserInput.split("/by");
        if (parts.length <= 1) {
            throw new IllegalException("Nicely done! You remembered the /by but not there yet.\n"
                    + "Hint hint: You have to type something after the /by");
        }
        String description = parts[0].substring(words[0].length()).trim();
        if (description.isEmpty()) {
            throw new IllegalException("Okay. I think something is missing?...\n"
                    + "I can't really put a DESCRIPTION on what's missing.");
        }
        return words;
    }

    /**
     * Validates the "event" command.
     * Ensures that the event has a description, a "/from" time, and a "/to" time.
     *
     * @param words     the parsed input words from the user
     * @param UserInput the full user input string
     * @return the input words if the command is valid
     * @throws IllegalException if the event details are incomplete or improperly formatted
     */
    private String[] parseEvent(String[] words, String UserInput) throws IllegalException {

        if (words.length <= 1) {
            throw new IllegalException("Hey hey, tli man... Give me something to work with.");
        }
        if ((!UserInput.contains("/from")) || (!UserInput.contains("/to"))) {
            throw new IllegalException("event needs to be followed with a description"
                    + "then with /from (time) /to (time)");
        }
        if (UserInput.indexOf("/from") > UserInput.indexOf("/to")) {
            throw new IllegalException("Ah... wise guy. I caught you. "
                    + "It is /from and then /to.");
        }
        String[] parts = UserInput.split("/from"); // first split parts
        // event description
        String description = parts[0].substring(words[0].length()).trim();
        if (description.isEmpty()) {
            throw new IllegalException("Hey smarty pants... Where's the description?");
        }
        //timeframe from
        String[] parts2 = parts[1].split("/to");
        String from = parts2[0].trim(); //from
        if (from.isEmpty()) {
            throw new IllegalException("When is it?! WHEN IS IT?!!!");
        }
        //timeframe to
        if (parts2.length < 2) {
            throw new IllegalException("I mean... I also procrastinate "
                    + "but... no... just no...");
        }
        return words;
    }

    /**
     * Validates the "delete" command.
     * Ensures that a task number is provided to delete a task.
     *
     * @param words the parsed input words from the user
     * @return the input words if the command is valid
     * @throws IllegalException if the command is missing the task number or the task number is invalid
     */
    public String[] parseDelete(String[] words) throws IllegalException {
        if (words.length != 2) {
            throw new IllegalException("Alright. What task do you want to delete?\n" +
                    "Come on, do it like this. delete (task number). It's just that easy");
        }
        try {
            Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new IllegalException("Deleting~ Oh wait... Which task do i delete?");
        }

        return words;
    }

    /**
     * Validates the "find" command.
     * Ensures that a keyword is provided to search for tasks.
     *
     * @param words the parsed input words from the user
     * @return the input words if the command is valid
     * @throws IllegalException if the command is missing a keyword
     */
    public String[] parseFind(String[] words) throws IllegalException {
        if (words.length < 2) {
            throw new IllegalException("Well... I'm not a mind reader you know?\n" +
                    " You gotta tell me what you want me to find. Try again.");
        }
        return words;
    }

}
