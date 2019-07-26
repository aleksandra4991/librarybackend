package com.kodilla.librarybackend.domain;

public class ReservationDto {

    private Long id;
    private String reader;
    private String reservedBooks;
    private boolean active;

    public ReservationDto() {
    }

    public ReservationDto(Long id, String reader, String reservedBooks) {
        this.id = id;
        this.reader = reader;
        this.reservedBooks = reservedBooks;
    }

    public ReservationDto(Long id, String reader, String reservedBooks, boolean active) {
        this.id = id;
        this.reader = reader;
        this.reservedBooks = reservedBooks;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

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
