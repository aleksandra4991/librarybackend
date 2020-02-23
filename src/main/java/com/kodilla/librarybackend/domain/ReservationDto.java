package com.kodilla.librarybackend.domain;

public class ReservationDto {

    private  boolean active;
    private Long reservationId;
    private String reader;
    private String reservedBooks;


    public ReservationDto() {
    }

    public ReservationDto(boolean active, String reader, String reservedBooks) {
        this.active = active;
        this.reader = reader;
        this.reservedBooks = reservedBooks;
    }

    public Long geReservationId() { return reservationId; }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getReader() {
        return reader;
    }

    public String getReservedBooks() {
        return reservedBooks;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
