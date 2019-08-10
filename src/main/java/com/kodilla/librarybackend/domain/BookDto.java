package com.kodilla.librarybackend.domain;

import java.util.Objects;

public class BookDto {

    private Long bookId;
    private String title;
    private String author;
    private Long year;
    private String signature;
    private boolean rented;
    private String genreId;

    public BookDto() {
    }

    public BookDto(String title, String author, Long year, String signature, boolean rented, String genreId) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.signature = signature;
        this.rented = rented;
        this.genreId = genreId;
    }

    public BookDto(String title, String author, Long year, String signature, String genreId) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.signature = signature;
        this.genreId = genreId;
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

    public void setRented(boolean rented) {
        this.rented = rented;
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
        BookDto bookDto = (BookDto) o;
        return isRented() == bookDto.isRented() &&
                getBookId().equals(bookDto.getBookId()) &&
                getTitle().equals(bookDto.getTitle()) &&
                getAuthor().equals(bookDto.getAuthor()) &&
                getYear().equals(bookDto.getYear()) &&
                getGenreId().equals(bookDto.getGenreId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookId(), getTitle(), getAuthor(), getYear(), isRented(), getGenreId());
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
