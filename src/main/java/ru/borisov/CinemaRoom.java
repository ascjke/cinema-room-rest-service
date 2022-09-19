package ru.borisov;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CinemaRoom {

    private int totalRows;
    private int totalColumns;
    private List<Seat> availableSeats;

    @JsonIgnore
    private Map<UUID, Seat> purchasedTickets = new HashMap<>();

    @Autowired
    public CinemaRoom(@Value("${rows}") int totalRows,
                      @Value("${columns}") int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = initSeats();
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Map<UUID, Seat> getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(Map<UUID, Seat> purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }

    private List<Seat> initSeats() {

        List<Seat> availableSeats = new ArrayList<>();
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                availableSeats.add(new Seat(i, j));
            }
        }

        return availableSeats;
    }

    @Override
    public String toString() {
        return "CinemaRoom{" +
                "totalRows=" + totalRows +
                ", totalColumns=" + totalColumns +
                ", availableSeats=" + availableSeats +
                ", purchasedTickets=" + purchasedTickets +
                '}';
    }
}
