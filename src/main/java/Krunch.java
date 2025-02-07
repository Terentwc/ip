import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Krunch {
    // Saves task
    public static void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))) {
            for (Task task : tasks) {
                if (task instanceof ToDo) {
                    writer.write("T | " + (task.isDone() ? "1" : "0") + " | "
                            + task.getTask());
                } else if (task instanceof Deadline) {
                    writer.write("D | " + (task.isDone() ? "1" : "0") + " | "
                            + task.getTask() + " | " + ((Deadline) task).by);
                } else if (task instanceof Event) {
                    writer.write("E | " + (task.isDone() ? "1" : "0") + " | "
                            + task.getTask() + " | " + ((Event) task).from + " | " + ((Event) task).to);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks");
        }
    }

    // Loads Tasks
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| "); // Split by " | "
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String taskName = parts[2];

                Task task;
                if (type.equals("T")) {
                    task = new ToDo(taskName);
                } else if (type.equals("D")) {
                    task = new Deadline(taskName, parts[3]);
                } else if (type.equals("E")) {
                    task = new Event(taskName, parts[3], parts[4]);
                } else {
                    continue; // Skip invalid lines
                }
                // Restore task completion status
                if (isDone) {
                    task.toggleDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            return tasks;
        }
        return tasks;
    }


    // Main
    public static void main(String[] args) {
        // Scanner for user stuff
        Scanner scanner = new Scanner(System.in);

        //Greet
        System.out.println("_____________________________________________________________________________");
        System.out.println("Oh, what a treat. Finally, you've graced me with your presence.");
        //Ask
        System.out.println("Name's Krunch. So, what mighty task are we tackling today?");
        System.out.println("_____________________________________________________________________________");

        // Will delete after making deadline and event flexible
        // Still thinking on how to do
        System.out.println("By the way, event and deadline requires a format of yyyy-mm-dd");
        System.out.println("I'll be unhappy and leave if you give it to me any other way!");
        System.out.println("_____________________________________________________________________________");

        // created a task list
        //ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Task> tasks = loadTasks();

        //first pass
        boolean isFirst = true;

        while(true) {
            if (!isFirst) {
                saveTasks(tasks);
            }
            isFirst = false;
            String UserInput = scanner.nextLine();
            // splitting up words here [0] = task [1]++ = descriptions
            String[] words = UserInput.split(" ");
            if (words[0].equalsIgnoreCase("bye")) {
                System.out.println("Oh, I see how it is. No need to pretend you’ll miss me. Go on, then. Goodbye.");
                System.out.println("_____________________________________________________________________________");
                break;
            // printing out the list
            } else if (words[0].equals("list")) {
                try {
                    if (tasks.isEmpty()) {
                        throw new IllegalException("Oh you ain't getting me this time!\n" +
                                "Psst! Make a task first! Don't tell anyone I told you this!");
                    }
                    System.out.println("Oh, just in case you totally forgot, here’s a little reminder of your super exciting tasks.");
                    for (int i = 1; i <= tasks.size(); i++) {
                        // 1.[] blah
                        System.out.println(i + ". " + tasks.get(i - 1));
                    }
                } catch (IllegalException e) {
                    System.out.println(e.getMessage());
                }
            // unmarkng a task
            } else if (words[0].contains("unmark")) {
                // unmark (num)  <- -1
                try {
                    if (tasks.isEmpty()) {
                        throw new IllegalException("Oh you ain't getting me this time!\n" +
                                "Psst! Make a task first! Don't tell anyone I told you this!");
                    }
                    if (words.length < 2) {
                        throw new IllegalException("Alright. It is unmar-... What did you want to unmark exactly?\n" +
                                "Come on, do it like this. unmark (task number). It's just that easy");
                    }
                    int num = Integer.parseInt(words[1]) - 1;
                    if (num >= tasks.size()) {
                        throw new IllegalException("You couldn't even do an imaginary task? Quickly! Start on it!");
                    }
                    // checking if it is not done
                    if (!tasks.get(num).isDone()) {
                        System.out.println("So... when are you going to start exactly?");
                    } else { //changing to unmarked
                        System.out.println("Hm... alright. It is unmarked.");
                        tasks.get(num).toggleDone();
                    }
                    // printing status
                    System.out.println(tasks.get(num));
                } catch (IllegalException e) {
                    System.out.println(e.getMessage());
                }
            // marking a task
            } else if (words[0].contains("mark")) {
                // mark (num)  <- -1
                try {
                    //list empty
                    if (tasks.isEmpty()) {
                        throw new IllegalException("Oh you ain't getting me this time!\n" +
                                "Psst! Make a task first! Don't tell anyone I told you this!");
                    }
                    //check for length
                    if (words.length < 2) {
                        throw new IllegalException("Well done! Marking nothing new as being done!\n" +
                                "Come on, do it like this. mark (task number). It's just that easy");
                    }
                    int num = Integer.parseInt(words[1]) - 1;
                    // marking a task that doesn't exist
                    if (num >= tasks.size()) {
                        throw new IllegalException("Marking imaginary task as done! Aren't I sooo... helpful?");
                    }
                    // already done
                    if (tasks.get(num).isDone()) {
                        System.out.println("Wow... oh mighty and wise one... the task is already done");
                    } else { //changing to done
                        System.out.println("Yes yes... very well done.");
                        tasks.get(num).toggleDone();
                    }
                    // printing status
                    System.out.println(tasks.get(num));
                } catch (IllegalException e) {
                    System.out.println(e.getMessage());
                }
            // to do part
            } else if (words[0].contains("todo")) {
                try {
                    if (words.length < 2) {
                        throw new IllegalException("Hey hey, tli man... Give me something to work with.");
                    }
                    System.out.println("It is added... anything else?");
                    String description = UserInput.substring(words[0].length()).trim();
                    Task addend = new ToDo(description);
                    tasks.add(addend);
                    System.out.println(addend);
                    System.out.println("In case you wanted to know, you have " + tasks.size() + " tasks in the list.");
                } catch (IllegalException e) {
                    System.out.println(e.getMessage());
                }
            // deadline
            } else if (words[0].contains("deadline")) {
                try {
                    //check length
                    if (words.length < 2) {
                        throw new IllegalException("Hey hey, tli man... Give me something to work with.");
                    }
                    if (!UserInput.contains("/by")) {
                        throw new IllegalException("Getting somewhere... but not there yet. Try again." +
                                "Hint: Remember to use /by but you didn't hear this hint from me.");
                    }
                    String[] parts = UserInput.split("/by");
                    if (parts.length <= 1) {
                        throw new IllegalException("Nicely done! You remembered the /by but not there yet." +
                                "Hint hint: You have to type something after the /by");
                    }
                    String description = parts[0].substring(words[0].length()).trim();
                    if (description.isEmpty()) {
                        throw new IllegalException("Okay. I think something is missing?..." +
                                "I can't really put a DESCRIPTION on what's missing.");
                    }
                    String timeframe = parts[1].trim();
                    Task addend = new Deadline(description, timeframe);
                    tasks.add(addend);
                    System.out.println("It is added... anything else?");
                    System.out.println(addend);
                    System.out.println("In case you wanted to know, you have " + tasks.size() + " tasks in the list.");
                } catch (IllegalException e) {
                    System.out.println(e.getMessage());
                }
            } else if (words[0].contains("event")) {
                try {
                    if (words.length <= 1) {
                        throw new IllegalException("Hey hey, tli man... Give me something to work with.");
                    }
                    if ((!UserInput.contains("/from")) || (!UserInput.contains("/to"))) {
                        throw new IllegalException("event needs to be followed with a description" +
                                "then with /from (time) /to (time)");
                    }
                    if (UserInput.indexOf("/from") > UserInput.indexOf("/to")) {
                        throw new IllegalException("Ah... wise guy. I caught you. " +
                                "It is /from and then /to.");
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
                        throw new IllegalException("I mean... I also procrastinate " +
                                "but just leave an idk instead of a blank please...");
                    }
                    String to = parts2[1].trim(); //to
                    Task addend = new Event(description, from, to);
                    tasks.add(addend);
                    System.out.println("It is added... anything else?");
                    System.out.println(addend);
                    System.out.println("In case you wanted to know, you have " + tasks.size() + " tasks in the list.");
                } catch (IllegalException e) {
                    System.out.println(e.getMessage());
                }
            } else if (words[0].equals("delete")) {
                try {
                    if (tasks.isEmpty()) {
                        throw new IllegalException("Oh... And what do you want to delete? My memory is empty.\n" +
                                "Psst! Make a task first! Don't tell anyone I told you this!");
                    }
                    if (words.length < 2) {
                        throw new IllegalException("Alright. What task do you want to delete?\n" +
                                "Come on, do it like this. delete (task number). It's just that easy");
                    }
                    int num = Integer.parseInt(words[1]) - 1;
                    if (num >= tasks.size()) {
                        throw new IllegalException("Deleting!... Deleted!! What did I delete? The imaginary task of course!");
                    }
                    // Deleting the task
                    Task removedTask = tasks.remove(num);
                    System.out.println("Task deleted: " + removedTask);
                    if (tasks.isEmpty()) {
                        System.out.println("You now have 0 tasks left.\n" +
                                "Thank you for deleting parts of my memory. It sure feels nice to remember nothing.");
                    } else {
                        System.out.println("You now have " + tasks.size() + " tasks left.\n" +
                                "Thank you for deleting parts of my memory. It sure feels nice to remember lesser things.");
                    }
                } catch (IllegalException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if (words[0].isEmpty()) {
                System.out.println("W-wha?... h-hey! Don't give me the silent treatment!");
            } else { //echoing if anything else as an error message
                    System.out.println(UserInput + "! smartass.");
            }
        }

    }
}
