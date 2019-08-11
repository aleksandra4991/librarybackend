package com.kodilla.librarybackend.domain;

public class GenreDto {

    private Long genreId;
    private String name;

    public GenreDto() {
    }

    public GenreDto(String name) {
        this.name = name;
    }

    public GenreDto(Long genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    public Long getGenreId() {
        return genreId;
    }

    public String getName() {
        return name;
    }

}