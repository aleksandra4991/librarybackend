package com.kodilla.librarybackend.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="RESERVATION_ID", unique = true)
    private Long id;
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    private LocalDate reservationDateCreation;

    public Reservation() {
    }

    public Reservation(Cart cart,Reader reader) {
        this.cart = cart;
        this.reader = reader;
    }

    public Long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Reader getReader() {
        return reader;
    }

    public Cart getCart() {
        return cart;
    }

    public LocalDate getReservationDateCreation() {
        return reservationDateCreation;
    }


}
