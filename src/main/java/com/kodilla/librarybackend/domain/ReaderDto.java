package com.kodilla.librarybackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class ReaderDto {

    private Long id;
    private String readerName;
    private String phoneNumber;
    private String emailAdress;
    private String status;

    @JsonIgnore
    private List<ReservationDto> reservationDtoList = new ArrayList<>();

    @JsonIgnore
    private List<BookDto> bookDtoList = new ArrayList<>();

    public ReaderDto() {
    }

    public ReaderDto(Long id, String readerName, String phoneNumber, String emailAdress, String status) {
        this.id = id;
        this.readerName = readerName;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;
        this.status = status;
    }

    public ReaderDto(String readerName) {
        this.readerName = readerName;
        this.status = "UNBLOCKED";
    }

    public ReaderDto(Long id, String readerName) {
        this.id = id;
        this.readerName = readerName;
    }

    public void blockUser(){
        this.status = "BLOCKED";
    }

    public Long getId() {
        return id;
    }

    public String getReaderName() {
        return readerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public String getStatus() {
        return status;
    }

    public List<ReservationDto> getReservationDtoList() {
        return reservationDtoList;
    }

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }

    public void setReservationDtoList(List<ReservationDto> reservationDtoList) {
        this.reservationDtoList = reservationDtoList;
    }

    public void setBookDtoList(List<BookDto> bookDtoList) {
        this.bookDtoList = bookDtoList;
    }
}
