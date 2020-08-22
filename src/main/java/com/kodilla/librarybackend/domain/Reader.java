package com.kodilla.librarybackend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reader {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "READER_ID" ,unique = true)
    private Long id;

    @GeneratedValue
    private String uuid;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private boolean status;
    private String password;

    @OneToMany(targetEntity = Reservation.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(targetEntity = Book.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Book> bookList = new ArrayList<>();

    public Reader() {
    }

    public Reader(@NotNull Long id, String uuid, String name, String phoneNumber, String emailAddress, boolean status, String password, List<Reservation> reservations, List<Book> bookList) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.status = status;
        this.password = password;
        this.reservations = reservations;
        this.bookList = bookList;
    }

    public Reader(Long id, String name, String phoneNumber, String emailAddress, boolean status, String password, String uuid) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.status = status;
        this.password = password;
    }

    public Reader(String name, String phoneNumber, String emailAddress, boolean status, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.status = status;
        this.password = password;
    }

    public Reader(String name, String phoneNumber, String emailAddress, boolean status) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.status = status;
    }

    public Reader(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isStatus() {
        return status;
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
