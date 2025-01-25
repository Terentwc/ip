import java.util.Scanner;

public class Krunch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Greet
        System.out.println("_____________________________________________________________________________");
        System.out.println("Oh, what a treat. Finally, you've graced me with your presence.");
        //Ask
        System.out.println("Name's Krunch. So, what mighty task are we tackling today?");
        System.out.println("_____________________________________________________________________________");

        while(true) {
            String UserInput = scanner.nextLine();

            if(UserInput.equalsIgnoreCase("bye")) {
                System.out.println("Oh, I see how it is. No need to pretend you’ll miss me. Go on, then. Goodbye.");
                System.out.println("_____________________________________________________________________________");
                break;
            } else {
                System.out.println(UserInput);
            }
        }



        //Bye
//        System.out.println("Bye. Hope to see you again soon!");
//        System.out.println("________________________________");
//        System.exit(0);

    }
}
