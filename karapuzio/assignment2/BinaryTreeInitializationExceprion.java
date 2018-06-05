package edu.codeU.assignment2;

public class BinaryTreeInitializationExceprion extends Exception{
    public BinaryTreeInitializationExceprion() {
    }

    public BinaryTreeInitializationExceprion(String message) {
        super(message);
    }

    public BinaryTreeInitializationExceprion(String message, Throwable cause) {
        super(message, cause);
    }

    public BinaryTreeInitializationExceprion(Throwable cause) {
        super(cause);
    }

    public BinaryTreeInitializationExceprion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
