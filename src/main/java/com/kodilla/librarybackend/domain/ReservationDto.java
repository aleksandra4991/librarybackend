package com.kodilla.librarybackend.domain;

import java.util.concurrent.atomic.AtomicBoolean;

public class ReservationDto {

    private Long reservationId;
    private String reader;
    private String reservedBooks;
    private AtomicBoolean active;

    public ReservationDto() {
    }

    public ReservationDto(Long reservationId, String reader, String reservedBooks, AtomicBoolean active) {
        this.reservationId = reservationId;
        this.reader = reader;
        this.reservedBooks = reservedBooks;
        this.active = active;
    }

    public Long geReservationId() { return reservationId; }

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
