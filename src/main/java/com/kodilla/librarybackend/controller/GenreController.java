package com.kodilla.librarybackend.controller;

import com.kodilla.librarybackend.domain.Genre;
import com.kodilla.librarybackend.domain.GenreDto;
import com.kodilla.librarybackend.exceptions.GenreNotFoundException;
import com.kodilla.librarybackend.mapper.GenreMapper;
import com.kodilla.librarybackend.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/myLibrary")
public class GenreController {

    @Lazy
    @Autowired
    private GenreService genreService;

    @Lazy
    @Autowired
    private GenreMapper genreMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/genres" )
    public List <GenreDto> getAllGenres (){
        return genreMapper.mapToGenreDtoList(genreService.getAllGenres());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/genre")
    public GenreDto getSpecifiedGenre(@RequestParam Long genreId) throws GenreNotFoundException {
        return genreMapper.mapToGenreDto((Genre)genreService.getSpecifiedGenre(genreId));
    }

    /*@RequestMapping(method = RequestMethod.PUT, value = "updateSpecifiedGenre")
    public GenreDto updateSpecifiedGenre(@RequestBody GenreDto genreDto){
        return genreMapper.mapToGenreDto(genreService.createNewGenre(genreMapper.mapToGenre(genreDto)));
    }*/

    @RequestMapping(method = RequestMethod.POST, value = "/genre", consumes = APPLICATION_JSON_VALUE)
    public GenreDto createNewGenre(@RequestBody GenreDto genreDto){
        return genreMapper.mapToGenreDto(genreService.createNewGenre(genreMapper.mapToGenre(genreDto)));
    }

}
