package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.Book;
import com.kodilla.librarybackend.domain.BookDto;
import com.kodilla.librarybackend.exceptions.BookNotFoundException;
import com.kodilla.librarybackend.mapper.BookMapper;
import com.kodilla.librarybackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/myLibrary")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/books")
    public List<BookDto> getAllBooks(){
        return bookMapper.mapToBookDtoList(bookService.getAllBooks());
    }

    /*@RequestMapping(method = RequestMethod.GET, value = "getAvaiableToRentBooks")
    public List<BookDto> getAvaiableToRentBooks(@RequestParam boolean rented){
        return bookMapper.mapToBookDtoList(bookService.getAvaiableToRentBooks(rented));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAlreadyRentedBooks")
    public List<BookDto> getAlreadyRentedBooks(@RequestParam boolean rented){
        return bookMapper.mapToBookDtoList(bookService.getAlreadyRentedBooks(rented));
    }*/

    @RequestMapping(method = RequestMethod.GET, value = "/books/author")
    public List<BookDto> getBooksOfDefiniedAuthor (@RequestParam String author){
        return bookMapper.mapToBookDtoList(bookService.getBooksOfDefiniedAuthor(author));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/title")
    public List<BookDto> getBooksOfSpecifiedTitle (@RequestParam String title){
        return bookMapper.mapToBookDtoList(bookService.findByTitle(title));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book")
    public BookDto getBook(@RequestParam Long bookId) throws BookNotFoundException {
        return bookMapper.mapToBookDto((Book) bookService.getBook(bookId));
    }


    @RequestMapping(method = RequestMethod.POST, value = "/book", consumes = APPLICATION_JSON_VALUE)
    public BookDto createBook(@RequestBody BookDto bookDto){
        return bookMapper.mapToBookDto(bookService.createBook(bookMapper.mapToBook(bookDto)));
    }

    /*@RequestMapping(method = RequestMethod.PUT,value = "updateBook",consumes = APPLICATION_JSON_VALUE)
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookMapper.mapToBookDto(bookService.createBook(bookMapper.mapToBook(bookDto)));
    }*/

    @RequestMapping(method = RequestMethod.DELETE,value = "/book")
    public void deleteBook(@RequestParam Long bookId) throws BookNotFoundException {
        bookService.deleteBook(bookId);
    }

}
