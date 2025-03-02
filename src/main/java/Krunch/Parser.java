package Krunch;

import Krunch.exceptions.IllegalException;

/**
 * The Parser class is responsible for parsing user input and determining the appropriate
 * actions or commands that need to be executed based on the input. It validates user
 * input, ensures correct formatting, and throws exceptions for invalid inputs. The
 * class incorporates methods for handling a variety of commands that the user may input.
 */
public class Parser {
    UI ui = new UI();

    /**
     * Parses the user's input and determines its type based on predefined commands.
     * Depending on the input, the appropriate method for processing that command is executed.
     * Throws an exception if the input does not match any expected command or is improperly formatted.
     *
     * @param UserInput the complete string entered by the user
     * @return an array of strings containing the parsed input words if the command is recognized and valid
     * @throws IllegalException if the input is null, unrecognized, improperly formatted, or missing required data
     */
    public String[] parsedInfo(String UserInput) throws IllegalException {
        assert UserInput != null : "User input should never be null";
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
     * Processes a "bye" command from the user and performs necessary actions based on the input.
     * If the input contains only the word "bye", the program exits after displaying a farewell message.
     * Otherwise, an exception is thrown indicating the inability to exit.
     *
     * @param words the parsed input words from the user
     * @return an empty string since execution ends with the system exit or an exception is thrown
     * @throws IllegalException if the input does not contain only the word "bye"
     */
    private String byeMessage(String[] words) throws IllegalException {
        String response = "";
        if (words.length == 1 && words[0].equals("bye")) {
            ui.showMessage("Oh, I see how it is. No need to pretend you'll miss me. Go on, then. Goodbye.");
            System.exit(0);
        } else {
            throw new IllegalException("Goodbye...\n"
                    + "You can't get rid of me that easily! >:)");
        }
        return response;
    }

    /**
     * Processes the "list" command by returning the input words if valid.
     * If the command is invalid, an exception is thrown with an appropriate message.
     *
     * @param words the parsed input words from the user
     * @return an array of strings containing the input words if the command is valid
     * @throws IllegalException if the input does not match the expected format of the "list" command
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
     * Validates and parses the "mark" command from the user's input.
     * Ensures the command format has exactly two components: the command keyword
     * and a task number. The task number must be a valid integer.
     *
     * @param words the parsed input words from the user; expected to consist of
     *              the "mark" command and a task number
     * @return an array of strings containing the validated command and task number
     * @throws IllegalException if the number of components in the input is not
     *                          exactly two or if the task number is not a valid integer
     */
    private String[] parseMark(String[] words) throws IllegalException {
        String word = words[0];
        if (words.length != 2) {
            throw new IllegalException("Alright. It is" + word + "ed... What did you want to " + word + " exactly?\n"
                    + "Come on, do it like this. " + word + " (task number). It's just that easy");
        }
        // calls to edit list
        try {
            // test if words[1] is an integer
            Integer.parseInt(words[1]);
            return words;
        } catch (NumberFormatException e) {
            throw new IllegalException("Ah... It has to be a number. (E.g. " + word + " 1)");
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
