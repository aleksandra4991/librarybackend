package com.kodilla.librarybackend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Book {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="BOOK_ID", unique = true)
    private Long id;
    private String title;
    private String author;
    private Long year;
    private String signature;
    private boolean rented;

    //@ManyToOne(cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "CART_ID")
    //private Cart cart;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;


    public Book() {
    }

    public Book(String title, String author, Long year, String signature, Genre genre) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.signature = signature;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Long getYear() {
        return year;
    }

    public String getSignature() {
        return signature;
    }

    public boolean isRented() {
        return rented;
    }

    /*public Cart getCart() {
        return cart;
    }*/

    public Genre getGenre() {
        return genre;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    /*public void setCart(Cart cart) {
        this.cart = cart;
    }*/
}
