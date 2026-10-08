package io.tonesniper.domain;

public class NotInsertedException extends RuntimeException {
    public NotInsertedException() {
        super("Not inserted");
    }
    public NotInsertedException(String message) {
        super(message);
    }
    public NotInsertedException(String message, Throwable cause) {
        super(message, cause);
    }
}
