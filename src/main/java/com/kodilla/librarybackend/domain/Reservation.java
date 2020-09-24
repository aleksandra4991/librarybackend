package com.kodilla.librarybackend.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Reservation {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="RESERVATION_ID", unique = true)
    private Long id;
    private boolean active;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    private LocalDate reservationDateCreation;

    public Reservation() {
    }

    public Reservation(boolean active, Reader reader, Cart cart) {
        this.active = active;
        this.reader = reader;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return active == that.active &&
                Objects.equals(id, that.id) &&
                Objects.equals(reader, that.reader) &&
                Objects.equals(cart, that.cart) &&
                Objects.equals(reservationDateCreation, that.reservationDateCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active, reader, cart, reservationDateCreation);
    }
}
