import java.util.Scanner;
import java.util.ArrayList;

public class Krunch {
    public static void main(String[] args) {
        // Scanner for user stuff
        Scanner scanner = new Scanner(System.in);

        //Greet
        System.out.println("_____________________________________________________________________________");
        System.out.println("Oh, what a treat. Finally, you've graced me with your presence.");
        //Ask
        System.out.println("Name's Krunch. So, what mighty task are we tackling today?");
        System.out.println("_____________________________________________________________________________");

        // created a task list
        ArrayList<Task> tasks = new ArrayList<>();

        while(true) {
            String UserInput = scanner.nextLine();
            // splitting up words here [0] = task [1]++ = descriptions
            String[] words = UserInput.split(" ");
            if (words[0].equalsIgnoreCase("bye")) {
                System.out.println("Oh, I see how it is. No need to pretend you’ll miss me. Go on, then. Goodbye.");
                System.out.println("_____________________________________________________________________________");
                break;
            // printing out the list
            } else if (words[0].equals("list")) {
                System.out.println("Oh, just in case you totally forgot, here’s a little reminder of your super exciting tasks.");
                for (int i = 1; i <= tasks.size(); i++) {
                    // 1.[] blah
                    System.out.println(i + ". " + tasks.get(i-1));
                }
            // unmarkng a task
            } else if (words[0].contains("unmark")){
                // unmark (num)  <- -1
                int num = Integer.parseInt(words[1]) - 1;
                // checking if it is not done
                if (!tasks.get(num).isDone()) {
                    System.out.println("So... when are you going to start exactly?");
                } else { //changing to unmarked
                    System.out.println("Hm... alright. It is unmarked.");
                    tasks.get(num).toggleDone();
                }
                // printing status
                System.out.println(tasks.get(num));
            // marking a task
            } else if (words[0].contains("mark")) {
                // mark (num)  <- -1
                int num = Integer.parseInt(words[1]) - 1;
                // already done
                if (tasks.get(num).isDone()) {
                    System.out.println("Wow... oh mighty and wise one... the task is already done");
                } else { //changing to done
                    System.out.println("Yes yes... very well done.");
                    tasks.get(num).toggleDone();
                }
                // printing status
                System.out.println(tasks.get(num));
            // to do part
            } else if (words[0].contains("todo")) {
                System.out.println("It is added... anything else?");
                String description = UserInput.substring(words[0].length()).trim();
                Task addend = new ToDo(description);
                tasks.add(addend);
                System.out.println(addend);
                System.out.println("In case you wanted to know, you have " + tasks.size() + " tasks in the list.");
            // deadline
            } else if (words[0].contains("deadline")) {
                System.out.println("It is added... anything else?");
                String[] parts = UserInput.split("/");
                String description = parts[0].substring(words[0].length()).trim();
                String timeframe = parts[1].substring(2).trim(); // for by
                Task addend = new Deadline(description, timeframe);
                tasks.add(addend);
                System.out.println(addend);
                System.out.println("In case you wanted to know, you have " + tasks.size() + " tasks in the list.");
            } else if (words[0].contains("event")) {
                System.out.println("It is added... anything else?");
                String[] parts = UserInput.split("/"); //3 parts
                // event description
                String description = parts[0].substring(words[0].length()).trim();
                //timeframe from
                String from = parts[1].substring(4).trim(); // compensate from
                //timeframe to
                String to = parts[2].substring(2).trim(); //compensate to
                Task addend = new Event(description, from, to);
                tasks.add(addend);
                System.out.println(addend);
                System.out.println("In case you wanted to know, you have " + tasks.size() + " tasks in the list.");
            } else { //echoing if anything else
                System.out.println(UserInput + "!");
            }
        }

    }
}
