package ru.borisov.error;

public class InvalidPasswordException extends RuntimeException {

    private String message;

    public InvalidPasswordException() {
    }

    public InvalidPasswordException(String messagge) {
        this.message = messagge;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
