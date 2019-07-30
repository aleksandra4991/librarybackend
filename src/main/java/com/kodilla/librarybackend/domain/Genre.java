package com.kodilla.librarybackend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Genre {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="GENRE_ID", unique = true)
    private Long id;
    private String name;

    @OneToMany(targetEntity = Book.class, mappedBy = "genre",
            cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
