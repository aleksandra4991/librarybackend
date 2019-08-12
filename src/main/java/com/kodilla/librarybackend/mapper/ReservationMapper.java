package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.*;
import com.kodilla.librarybackend.repository.ReservationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {
/*
    private ReservationRepository reservationRepository;

    public List<ReservationDto> mapToReservationDtoList(final List<Reservation> reservations) {
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationDto reservationDto = new ReservationDto(reservation.getId(),String.valueOf(reservation.getReader(),reservation.getCart(),reservation.getReservationDateCreation());
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
    }*/
}
