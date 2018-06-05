package edu.codeU.assignment2;

public class BinarySearchElementExeption extends Exception {
    public BinarySearchElementExeption() {
    }

    public BinarySearchElementExeption(String message) {
        super(message);
    }

    public BinarySearchElementExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public BinarySearchElementExeption(Throwable cause) {
        super(cause);
    }

    public BinarySearchElementExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
