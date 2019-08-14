package com.kodilla.librarybackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class ReaderDto {

    private Long readerId;
    private String readerName;
    private String phoneNumber;
    private String emailAdress;
    private boolean status;
    private String password;

    @JsonIgnore
    private List<ReservationDto> reservationDtoList = new ArrayList<>();

    @JsonIgnore
    private List<BookDto> bookDtoList = new ArrayList<>();

    public ReaderDto() {
    }

    public ReaderDto(String readerName, boolean status) {
        this.readerName = readerName;
        this.status = status;
    }

    public ReaderDto(String readerName, String phoneNumber, String emailAdress) {
        this.readerName = readerName;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;
    }

    public void blockUser(){
        this.status = false;
    }

    public Long getReaderId() {
        return readerId;
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

    public boolean getStatus() {
        return status;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
