package ru.borisov.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ru.borisov.CinemaRoom;
import ru.borisov.Seat;
import ru.borisov.Stats;
import ru.borisov.Ticket;
import ru.borisov.error.InvalidPasswordException;
import ru.borisov.error.TicketException;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@RestController
public class RoomController {

    private final CinemaRoom cinemaRoom;
    private String managerPassword;

    @Autowired
    public RoomController(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    @Value("${managerPassword}")
    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    @GetMapping("/seats")
    CinemaRoom getCinemaRoomInfo() {

        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public Ticket purchaseTicket(@RequestBody Seat seatWithoutPriceInfo) {

        int row = seatWithoutPriceInfo.getRow();
        int column = seatWithoutPriceInfo.getColumn();

        if (row > cinemaRoom.getTotalRows() || row < 1 ||
                column > cinemaRoom.getTotalColumns() || column < 1) {
            throw new TicketException("The number of a row or a column is out of bounds!");
        }

        if (cinemaRoom.getAvailableSeats().remove(seatWithoutPriceInfo)) {
            Ticket ticket = new Ticket(new Seat(row, column));
            cinemaRoom.getPurchasedTickets().put(ticket.getToken(), ticket.getTicket());
            return ticket;
        } else {
            throw new TicketException("The ticket has been already purchased!");
        }
    }

    @PostMapping("/return")
    public ConcurrentHashMap<String, Seat> returnTicket(@RequestBody Ticket ticketWithoutSeatInfo) {

        ConcurrentHashMap<String, Seat> responseEntity = new ConcurrentHashMap<>();
        UUID token = ticketWithoutSeatInfo.getToken();

        if (cinemaRoom.getPurchasedTickets().containsKey(token)) {
            Seat returnedSeat = cinemaRoom.getPurchasedTickets().get(token);
            responseEntity.put("returned_ticket", returnedSeat);
            cinemaRoom.getAvailableSeats().add(returnedSeat);
            Collections.sort(cinemaRoom.getAvailableSeats());
            cinemaRoom.getPurchasedTickets().remove(token);
        } else {
            throw new TicketException("Wrong token!");
        }

        return responseEntity;
    }

    @PostMapping("/stats")
    public Stats getStats(@RequestParam(required = false) String password) {
        if (managerPassword.equals(password)) {
            int availableSeatsCount = cinemaRoom.getAvailableSeats().size();
            int purchasedTicketsCount = cinemaRoom.getPurchasedTickets().size();
            int currentIncome = cinemaRoom.getPurchasedTickets().values().stream()
                    .mapToInt(Seat::getPrice)
                    .sum();
            return new Stats(currentIncome, availableSeatsCount, purchasedTicketsCount);
        } else {
            throw new InvalidPasswordException("The password is wrong!");
        }
    }
}
