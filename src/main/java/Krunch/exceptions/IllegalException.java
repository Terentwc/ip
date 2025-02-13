package Krunch.exceptions;

/**
 * Custom exception class to handle illegal or invalid operations in the Krunch application.
 * This class extends the built-in `Exception` class and allows for custom error messages
 * to be thrown when an illegal operation occurs.
 */
public class IllegalException extends Exception {

    /**
     * Constructs a new IllegalException with the specified detail message.
     * The message provides information about the illegal operation that occurred.
     *
     * @param e the detail message explaining the cause of the exception
     */
    public IllegalException(String e) {
        super(e);
    }
}
