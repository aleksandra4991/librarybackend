package com.kodilla.librarybackend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="CART_ID", unique = true)
    private Long id;

    /*@OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            targetEntity = Reservation.class,
            mappedBy = "cart")
    @JoinColumn(name = "ORDER_ID")
    private Reservation reservation;
*/

    @OneToMany(targetEntity = Book.class, mappedBy = "cart",
            cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Book> books = new ArrayList<>();
    private LocalDate cartUpdate;

    public Cart() {
    }

    public Cart(List<Book> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    /*public Reservation getReservation() {
        return reservation;
    }*/

    /*public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }*/

    public List<Book> getBooks() {
        return books;
    }

    public LocalDate getCartUpdate() {
        return cartUpdate;
    }
}
