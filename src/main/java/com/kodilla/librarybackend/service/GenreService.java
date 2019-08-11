package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Genre;
import com.kodilla.librarybackend.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    public Genre getSpecifiedGenre(final Long id){
        return genreRepository.getOne(id);
    }

    public Genre createNewGenre(final Genre genre){
        return genreRepository.save(genre);
    }

    @Transactional
    public void deleteGenre(final Long id){
        genreRepository.deleteById(id);
    }


}
