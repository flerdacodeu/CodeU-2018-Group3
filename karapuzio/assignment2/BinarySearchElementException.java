package edu.codeU.assignment2;

public class BinarySearchElementException extends Exception {
    public BinarySearchElementException() {
    }

    public BinarySearchElementException(String message) {
        super(message);
    }

    public BinarySearchElementException(String message, Throwable cause) {
        super(message, cause);
    }

    public BinarySearchElementException(Throwable cause) {
        super(cause);
    }

    public BinarySearchElementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
