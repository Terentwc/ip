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
        ArrayList<String> tasks = new ArrayList<>();

        while(true) {
            String UserInput = scanner.nextLine();

            if(UserInput.equalsIgnoreCase("bye")) {
                System.out.println("Oh, I see how it is. No need to pretend you’ll miss me. Go on, then. Goodbye.");
                System.out.println("_____________________________________________________________________________");
                break;
            } else if(UserInput.equals("list")) {
                for (int i = 1; i <= tasks.size() ; i++ ) {
                    System.out.println(i + ". " + tasks.get(i - 1));
                }
            } else {
                // adding
                System.out.println("added: " + UserInput);
                tasks.add(UserInput);
            }
        }

    }
}
