package com.kodilla.librarybackend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Volume {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="BOOK_ID", unique = true)
    private Long id;
    private String title;
    private String authors;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    public static class VolumeBuilder{

        private String title;
        private String authors;
        private Genre genre;


        public VolumeBuilder title (String title) {
            this.title = title;
            return this;
        }

        public VolumeBuilder authors (String authors) {
            this.authors = authors;
            return this;
        }

        public VolumeBuilder genre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public Volume build(){
            return new Volume(title,authors,genre);
        }
    }

    public Volume() {
    }

    public Volume(@NotNull Long id, String title,String authors, Genre genre) {
        this.id = id;
        this.title=title;
        this.authors=authors;
        this.genre = genre;
    }

    public Volume(String title,String authors , Genre genre) {
        this.title=title;
        this.authors=authors;
        this.genre = genre;
    }

    public Volume(@NotNull Long id, String title,String authors, Cart cart, Genre genre, Reader reader) {
        this.id = id;
        this.title=title;
        this.authors=authors;
        this.cart = cart;
        this.genre = genre;
        this.reader = reader;
    }

    public Volume(@NotNull Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public Cart getCart() {
        return cart;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volume volume = (Volume) o;
        return Objects.equals(id, volume.id) &&
                Objects.equals(title, volume.title) &&
                Objects.equals(authors, volume.authors) &&
                Objects.equals(cart, volume.cart) &&
                Objects.equals(genre, volume.genre) &&
                Objects.equals(reader, volume.reader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, authors, cart, genre, reader);
    }
}
