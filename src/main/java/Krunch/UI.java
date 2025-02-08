package Krunch;

import Krunch.task.Task;

import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);

    public void greet() {
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
    }

    public String nextCommand() {
        return scanner.nextLine();
    }

    public void showMessage (String message) {
        System.out.println(message);
    }

    public void addedAcknowledgement (Task added, int size) {
        System.out.println("It is added... anything else?");
        System.out.println(added);
        System.out.println("In case you wanted to know, you have " + size + " tasks in the list.");
    }
}
