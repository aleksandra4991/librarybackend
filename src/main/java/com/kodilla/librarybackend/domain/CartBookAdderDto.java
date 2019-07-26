package com.kodilla.librarybackend.domain;

public class CartBookAdderDto {

    private Long id;
    private Long BookId;

    public CartBookAdderDto() {
    }

    public CartBookAdderDto(Long id, Long bookId) {
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
