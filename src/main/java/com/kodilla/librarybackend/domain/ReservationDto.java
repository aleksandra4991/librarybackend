package com.kodilla.librarybackend.domain;

import java.util.concurrent.atomic.AtomicBoolean;

public class ReservationDto {

    private AtomicBoolean active;
    private Long reservationId;
    private String reader;
    private String reservedBooks;


    public ReservationDto() {
    }

    public ReservationDto(AtomicBoolean active, String reader, String reservedBooks) {
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

    public AtomicBoolean getActive() {
        return active;
    }

    public void setActive(AtomicBoolean active) {
        this.active = active;
    }
}
