package com.kodilla.librarybackend.domain;

import java.util.Objects;

public class VolumeDto {

    private Long bookId;
    private String title;
    private String authors;
    private String genreId;

    public VolumeDto() {
    }

    public VolumeDto(Long bookId,String title,String authors , String genreId) {
        this.bookId = bookId;
        this.title=title;
        this.authors=authors;
        this.genreId = genreId;
    }

    public VolumeDto(String title,String authors) {
        this.title=title;
        this.authors=authors;
    }

    public VolumeDto(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumeDto volumeDto = (VolumeDto) o;
        return Objects.equals(bookId, volumeDto.bookId) &&
                Objects.equals(title, volumeDto.title) &&
                Objects.equals(authors, volumeDto.authors) &&
                Objects.equals(genreId, volumeDto.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, authors, genreId);
    }

    @Override
    public String toString() {
        return "VolumeDto{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", genreId='" + genreId + '\'' +
                '}';
    }
}
