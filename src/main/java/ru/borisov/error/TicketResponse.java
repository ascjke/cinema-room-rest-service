package ru.borisov.error;

public class TicketResponse {

    private String error;

    public TicketResponse() {
    }

    public TicketResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
