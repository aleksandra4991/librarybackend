package com.kodilla.librarybackend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cart {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="CART_ID", unique = true)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            targetEntity = Reservation.class,
            mappedBy = "cart")
    @JoinColumn(name = "ORDER_ID")
    private Reservation reservation;

    @OneToMany(targetEntity = Volume.class, mappedBy = "cart",
            cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Volume> volumes = new ArrayList<>();
    private LocalDate cartUpdate;

    public Cart() {
    }

    public Cart(List<Volume> volumes) {
        this.volumes = volumes;
    }

    public Long getId() {
        return id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public List<Volume> getBooks() {
        return volumes;
    }

    public void setBooks(List<Volume> volumes) {
        this.volumes = volumes;
    }

    public LocalDate getCartUpdate() {
        return cartUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) &&
                Objects.equals(reservation, cart.reservation) &&
                Objects.equals(volumes, cart.volumes) &&
                Objects.equals(cartUpdate, cart.cartUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservation, volumes, cartUpdate);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", reservation=" + reservation +
                ", volumes=" + volumes +
                ", cartUpdate=" + cartUpdate +
                '}';
    }
}
