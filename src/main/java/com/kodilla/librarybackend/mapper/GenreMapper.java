package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.Genre;
import com.kodilla.librarybackend.domain.GenreDto;
import com.kodilla.librarybackend.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapper {

    @Lazy
    @Autowired
    private GenreRepository genreRepository;

    public List<GenreDto> mapToGenreDtoList(final List<Genre> genres) {
            return genres.stream()
                    .map(g->new GenreDto(g.getId(),g.getName()))
                    .collect(Collectors.toList());
    }

    public GenreDto mapToGenreDto(Genre genre) {
        GenreDto genreDto = new GenreDto(genre.getId(),genre.getName());
        return genreDto;
    }


    public Genre mapToGenre (GenreDto genreDto){
        return new Genre(genreDto.getName());
    }


    public List<Genre> mapToGenreList(final List<GenreDto> genreDtos) {
        return genreDtos.stream()
                .map(g -> genreRepository.findById(g.getGenreId()).get())
                .collect(Collectors.toList());
    }

}
