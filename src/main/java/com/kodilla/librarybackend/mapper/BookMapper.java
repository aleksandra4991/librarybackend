package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.domain.BookDto;
import com.kodilla.librarybackend.domain.Genre;
import com.kodilla.librarybackend.repository.BookRepository;
import com.kodilla.librarybackend.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    public List<BookDto> mapToBookDtoList(final List<Book> books) {
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            BookDto bookDto = new BookDto(book.getTitle(), book.getAuthor(),book.getYear(),book.getSignature(),book.isRented(),String.valueOf(book.getGenre().getId()));
            bookDto.setBookId(book.getId());
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

    public BookDto mapToBookDto(Book book) {
        BookDto bookDto = new BookDto(book.getTitle(), book.getAuthor(),book.getYear(),book.getSignature(),book.isRented(),String.valueOf(book.getGenre().getId()));
        bookDto.setBookId(book.getId());
        return bookDto;
    }

    public Book mapToBook(BookDto bookDto) {
        if(bookDto.getBookId() != null){
            return bookRepository.getOne(bookDto.getBookId());
        } else {
            Genre genre = genreRepository.findById(new Long(bookDto.getGenreId())).get();
            return new Book(bookDto.getTitle(), bookDto.getAuthor(),bookDto.getYear(),bookDto.getSignature(),bookDto.isRented(), genre);
        }

    }

    public List<Book> mapToBookList(final List<BookDto> bookDtos) {
        return bookDtos.stream()
                .map(b -> bookRepository.findById(b.getBookId()).get())
                .collect(Collectors.toList());
    }
}

