package edu.codeU.assignment2;

public class BinaryTreeInitializationException extends Exception{
    public BinaryTreeInitializationException() {
    }

    public BinaryTreeInitializationException(String message) {
        super(message);
    }

    public BinaryTreeInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BinaryTreeInitializationException(Throwable cause) {
        super(cause);
    }

    public BinaryTreeInitializationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
