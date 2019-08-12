package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.GenreDto;
import com.kodilla.librarybackend.domain.Reader;
import com.kodilla.librarybackend.domain.ReaderDto;
import com.kodilla.librarybackend.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    @Autowired
    private ReaderRepository readerRepository;

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readers) {
        return readers.stream()
                .map(r->new ReaderDto(r.getName(),r.isStatus()))
                .collect(Collectors.toList());
    }

    public ReaderDto mapToReaderDto(Reader reader) {
        ReaderDto readerDto = new ReaderDto(reader.getName(),reader.isStatus());
        return readerDto;
    }


    public Reader mapToReader (ReaderDto readerDto){
        return new Reader(readerDto.getReaderName(),readerDto.getStatus());
    }


    public List<Reader> mapToReaderList(final List<ReaderDto> readerDtos) {
        return readerDtos.stream()
                .map(r -> readerRepository.findById(r.getReaderId()).get())
                .collect(Collectors.toList());
    }
}
