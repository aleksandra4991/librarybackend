package com.kodilla.librarybackend.domain;

public class CartBookRemoverDto {

    private Long id;
    private Long BookId;

    public CartBookRemoverDto() {
    }

    public CartBookRemoverDto(Long id, Long bookId) {
        this.id = id;
        BookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return BookId;
    }
}
