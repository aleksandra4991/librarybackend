package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.domain.VolumeDto;
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

    public List<VolumeDto> mapToBookDtoList(final List<Volume> volumes) {
        List<VolumeDto> volumeDtos = new ArrayList<>();
        for (Volume volume : volumes) {
            VolumeDto volumeDto = new VolumeDto(volume.getTitle(),volume.getAuthors(),volume.getGenre().toString());
            volumeDto.setBookId(volume.getId());
            volumeDtos.add(volumeDto);
        }
        return volumeDtos;
    }

    public VolumeDto mapToBookDto(Volume volume) {
        VolumeDto volumeDto = new VolumeDto(volume.getTitle(),volume.getAuthors(),volume.getGenre().toString());
        return volumeDto;
    }

    public Volume mapToBook(VolumeDto volumeDto) {
        if(volumeDto.getBookId() != null){
            return bookRepository.getOne(volumeDto.getBookId());
        } else {
            Genre genre = genreRepository.findById(new Long(volumeDto.getGenreId())).get();
            return new Volume(volumeDto.getTitle(),volumeDto.getAuthors(),genre);
        }

    }

    public List<Volume> mapToBookList(final List<VolumeDto> volumeDtos) {
        return volumeDtos.stream()
                .map(b -> bookRepository.findById(b.getBookId()).get())
                .collect(Collectors.toList());
    }
}

