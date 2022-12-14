package ru.borisov;


import java.util.UUID;

public class Ticket {

    private UUID token;
    private Seat ticket;

    public Ticket() {
    }

    public Ticket(Seat ticket) {
        this.token = UUID.randomUUID();
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}



