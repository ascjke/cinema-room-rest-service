package ru.borisov.error;

public class InvalidPasswordResponse {

    private String error;

    public InvalidPasswordResponse() {
    }

    public InvalidPasswordResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
