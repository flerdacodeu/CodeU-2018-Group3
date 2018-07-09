package edu.codeU.assignment1.list;

/**
 * The exception class that used in cases
 * where we made some operations when the list is empty
 * or when we ask for element at position
 * and position is greater the size of list.
 * */
public class FindListElemException extends Exception{
    public FindListElemException() {
    }

    public FindListElemException(String message) {
        super(message);
    }

    public FindListElemException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindListElemException(Throwable cause) {
        super(cause);
    }

    public FindListElemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
