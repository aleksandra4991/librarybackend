package com.kodilla.librarybackend.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicBoolean;

@Entity
public class Reservation {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="RESERVATION_ID", unique = true)
    private Long id;
    private AtomicBoolean active;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    private LocalDate reservationDateCreation;

    public Reservation() {
    }

    public Reservation(Reader reader, Cart cart) {
        this.reader = reader;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public AtomicBoolean getActive() {
        return active;
    }

    public void setActive(AtomicBoolean active) {
        this.active = active;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Cart getCart() {
        return cart;
    }

    public LocalDate getReservationDateCreation() {
        return reservationDateCreation;
    }


}
