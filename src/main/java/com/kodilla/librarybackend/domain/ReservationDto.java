package com.kodilla.librarybackend.domain;

public class ReservationDto {

    private Long reservationId;
    private String reader;
    private String reservedBooks;
    private boolean active;

    public ReservationDto() {
    }

    public ReservationDto(Long reservationId, String reader, String reservedBooks) {
        this.reservationId = reservationId;
        this.reader = reader;
        this.reservedBooks = reservedBooks;
    }

    public ReservationDto(Long reservationId, String reader, String reservedBooks, boolean active) {
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

    public boolean isActive() {
        return active;
    }
}
