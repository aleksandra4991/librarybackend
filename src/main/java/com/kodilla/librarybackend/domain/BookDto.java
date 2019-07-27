package com.kodilla.librarybackend.domain;

import java.util.Objects;

public class BookDto {

    private Long bookId;
    private String title;
    private String author;
    private Long year;
    private boolean rented;
    private String groupId;

    public BookDto() {
    }

    public BookDto(Long bookId, String title, String author, Long year, boolean rented, String groupId) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
        this.rented = rented;
        this.groupId = groupId;
    }

    public BookDto(String title, String author, Long year, boolean rented, String groupId) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.rented = rented;
        this.groupId = groupId;
    }

    public BookDto(String title, String author, Long year, String groupId) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.groupId = groupId;
    }

    public Long getBookId() {
        return bookId;
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

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
                getGroupId().equals(bookDto.getGroupId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookId(), getTitle(), getAuthor(), getYear(), isRented(), getGroupId());
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
