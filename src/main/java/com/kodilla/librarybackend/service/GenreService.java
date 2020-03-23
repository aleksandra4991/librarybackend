package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Genre;
import com.kodilla.librarybackend.repository.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(GenreService.class);

    public List<Genre> getAllGenres(){
        LOGGER.info("Getting all genres.");
        return genreRepository.findAll();
    }

    public Genre getSpecifiedGenre(final Long id){
        LOGGER.info("Getting genre with id:"+id.toString());
        return genreRepository.getOne(id);
    }

    public Genre createNewGenre(final Genre genre){
        LOGGER.info("Crating new genre");
        return genreRepository.save(genre);
    }

    /*public void updateGenre(final Long id){
        LOGGER.info("Starting updating genre with id:"+id.toString());
        genreRepository.updateGenreNameForId(new String(),id);
        Genre genre = getSpecifiedGenre(id);
        entityManager.refresh(genre);
        LOGGER.info("Finished: updating genre with id:"+id.toString());
    }*/

    @Transactional
    public void deleteGenre(final Long id){
        LOGGER.info("Deleting genre with id:"+id.toString());
        genreRepository.deleteById(id);
    }


}
