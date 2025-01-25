public class Task {
    protected String task;
    protected boolean done;

    // initialing task as not done
    public Task (String task) {
        this.task = task;
        this.done = false;
    }

    public String stringDone() {
        return done ? "X" : " ";
    }

    // toggling between done and not done.
    // don't need to care about done marked as done or undone marked as undone.
    public void toggleDone() {
        this.done = !this.done;
    }

    // check to see if done or not
    public boolean isDone () {
        return this.done;
    }

    // Task name
    public String getTask() {
        return this.task;
    }
}
