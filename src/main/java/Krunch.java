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

            if (UserInput.equalsIgnoreCase("bye")) {
                System.out.println("Oh, I see how it is. No need to pretend you’ll miss me. Go on, then. Goodbye.");
                System.out.println("_____________________________________________________________________________");
                break;
            } else if (UserInput.equals("list")) {
                System.out.println("Oh, just in case you totally forgot, here’s a little reminder of your super exciting tasks.");
                for (int i = 1; i <= tasks.size(); i++) {
                    // 1.[] blah
                    System.out.println(i + ". "
                            + "[" + tasks.get(i - 1).stringDone() + "] "
                            + tasks.get(i - 1).getTask());
                }
            } else if (UserInput.contains("unmark")){
                // unmark (num)  <- -1
                String[] seperate = UserInput.split(" ");
                int num = Integer.parseInt(seperate[1]) - 1;
                // checking if it is not done
                if (!tasks.get(num).isDone()) {
                    System.out.println("So... when are you going to start exactly?");
                } else { //changing to unmarked
                    System.out.println("Hm... alright. It is unmarked.");
                    tasks.get(num).toggleDone();
                }
                // printing status
                System.out.println("[" + tasks.get(num).stringDone() + "] "
                        + tasks.get(num).getTask());
            } else if (UserInput.contains("mark")) {
                // mark (num)  <- -1
                String[] seperate = UserInput.split(" ");
                int num = Integer.parseInt(seperate[1]) - 1;
                // already done
                if (tasks.get(num).isDone()) {
                    System.out.println("Wow... oh mighty and wise one... the task is already done");
                } else { //changing to done
                    System.out.println("Yes yes... very well done.");
                    tasks.get(num).toggleDone();
                }
                // printing status
                System.out.println("[" + tasks.get(num).stringDone() + "] "
                        + tasks.get(num).getTask());
            } else {
                // adding
                System.out.println("added: " + UserInput);
                Task addend = new Task(UserInput);
                tasks.add(addend);
            }
        }

    }
}
