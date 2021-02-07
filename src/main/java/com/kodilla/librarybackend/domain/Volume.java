package com.kodilla.librarybackend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Volume {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "BOOK_ID", unique = true)
    private Long id;
    private String title;
    private String authors;
    private String publishedDate;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    public static class VolumeBuilder {

        private String title;
        private String auth;
        private String publishedDate;
        private String description;


        public VolumeBuilder title(String title) {
            this.title = title;
            return this;
        }

        public VolumeBuilder authors(String authors) {
            this.auth = authors;
            return this;
        }

        public VolumeBuilder publishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
            return this;
        }

        public VolumeBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Volume build() {
            return new Volume(title, auth, publishedDate, description);
        }
    }

    public Volume() {
    }

    public Volume(String title, String authors, String publishedDate, String description) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
    }

    public Volume(@NotNull Long id, String title, String authors, String publishedDate, String description, Genre genre) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.genre = genre;
    }

    public Volume(@NotNull Long id, String title, String authors, String publishedDate, String description, Cart cart, Genre genre, Reader reader) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Cart getCart() {
        return cart;
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
        if (!(o instanceof Volume)) return false;
        Volume volume = (Volume) o;
        return getId().equals(volume.getId()) &&
                getTitle().equals(volume.getTitle()) &&
                Objects.equals(getAuthors(), volume.getAuthors()) &&
                Objects.equals(getPublishedDate(), volume.getPublishedDate()) &&
                Objects.equals(getDescription(), volume.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getAuthors(), getPublishedDate(), getDescription());
    }
}
