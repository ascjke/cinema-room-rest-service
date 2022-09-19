package ru.borisov.error;


public class TicketException extends RuntimeException {

    private String message;

    public TicketException() {
    }

    public TicketException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
