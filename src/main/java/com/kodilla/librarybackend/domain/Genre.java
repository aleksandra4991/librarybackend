package com.kodilla.librarybackend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Genre {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="GENRE_ID", unique = true)
    private Long id;
    private String name;

    @OneToMany(targetEntity = Volume.class, mappedBy = "genre",
            cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Volume> volumes = new ArrayList<>();

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre(@NotNull Long id, String name, List<Volume> volumes) {
        this.id = id;
        this.name = name;
        this.volumes = volumes;
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

    public List<Volume> getBooks() {
        return volumes;
    }

    public void setBooks(List<Volume> volumes) {
        this.volumes = volumes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) &&
                Objects.equals(name, genre.name) &&
                Objects.equals(volumes, genre.volumes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, volumes);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", volumes=" + volumes +
                '}';
    }
}
