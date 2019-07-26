package com.kodilla.librarybackend.domain;

import java.util.ArrayList;
import java.util.List;

public class GenreDto {

    private Long id;
    private String name;

    public GenreDto() {
    }

    public GenreDto(String name) {
        this.name = name;
    }

    public GenreDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
