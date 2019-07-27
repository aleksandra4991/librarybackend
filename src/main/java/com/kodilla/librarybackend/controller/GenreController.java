package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.GenreDto;
import com.kodilla.librarybackend.exceptions.GenreNotFoundException;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/myLibrary")
public class GenreController {

    GenreDto romance = new GenreDto(1L,"romance");
    GenreDto thriller = new GenreDto(2L,"thiller");

    @RequestMapping(method = RequestMethod.GET, value = "getAllGenres" )
    public List <GenreDto> getAllGenres (){
        return new ArrayList<>(Arrays.asList(romance,thriller));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getSpecifiedGenre")
    public GenreDto getSpecifiedGenre(@RequestParam Long genreId) throws GenreNotFoundException {
        return thriller;
    }

    @RequestMapping(method = RequestMethod.PUT, name = "updateSpecifiedGenre")
    public GenreDto updateSepcifiedGenre(@RequestParam Long genreId){
        return new GenreDto(1L,"romance");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createNewGenre", consumes = APPLICATION_JSON_VALUE)
    public void createNewGenre(@RequestBody GenreDto genreDto){

    }

}
