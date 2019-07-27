package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.BookDto;
import com.kodilla.librarybackend.exceptions.BookNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/myLibrary")
public class BookController {

    @RequestMapping(method = RequestMethod.GET, value = "getAllBooks")
    public List<BookDto> getAllBooks(){
        return new ArrayList<>(Arrays.asList(new BookDto(1L,"Rok 1984","George Orwell",(long)1949,true,"1L"),
                new BookDto(2L,"Tytuł","Autor", (long) 2000,false,"2L")));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAvaiableToRentBooks")
    public List<BookDto> getAvaiableToRentBooks(@RequestParam boolean rented){
        return new ArrayList<>(Arrays.asList(new BookDto(1L,"Rok 1984","George Orwell",(long)1949,false,"1L"),
                new BookDto(2L,"Tytuł","Autor", (long) 2000,false,"2L")));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAlreadyRentedBooks")
    public List<BookDto> getAlreadyRentedBooks(@RequestParam boolean rented){
        return new ArrayList<>(Arrays.asList(new BookDto(1L,"Rok 1984","George Orwell",(long)1949,true,"1L"),
                new BookDto(2L,"Tytuł","Autor", (long) 2000,true,"2L")));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBooksOfDefiniedAuthor")
    public List<BookDto> getBooksOfDefiniedAuthor (@RequestParam String author){
        return new ArrayList<>(Arrays.asList(new BookDto(1L,"Rok 1984","George Orwell",(long)1949,true,"1L"),
                new BookDto(2L,"Tytuł","George Orwell", (long) (long)1952,true,"2L")));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBook")
    public BookDto getBook(@RequestParam Long bookId) throws BookNotFoundException {
        return new BookDto(2L,"Tytuł","Autor", (long) 2000,false,"2L");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createBook", consumes = APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto){

    }

    @RequestMapping(method = RequestMethod.PUT,value = "updateBook",consumes = APPLICATION_JSON_VALUE)
    public BookDto updateBook(@RequestParam Long bookId){
        return new BookDto(2L,"Tytuł","Autor", (long) 2000,true,"2L");
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "deleteBook")
    public void deleteBook(@RequestParam Long bookId) throws BookNotFoundException {
    }


}
