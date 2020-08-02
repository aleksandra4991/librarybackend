package com.kodilla.librarybackend.mapper;

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
                .map(r->new ReaderDto(r.getId(),r.getUuid(),r.getName(),r.getPhoneNumber(),r.getEmailAddress(),r.isStatus(),r.getPassword()))
                .collect(Collectors.toList());
    }

    public ReaderDto mapToReaderDto(Reader reader) {
        ReaderDto readerDto = new ReaderDto(reader.getId(),reader.getUuid(),reader.getName(),reader.getPhoneNumber(),reader.getEmailAddress(),reader.isStatus(),reader.getPassword());
        return readerDto;
    }


    public static Reader mapToReader (final ReaderDto readerDto){
        Reader reader = new Reader(readerDto.getReaderName(),readerDto.getPhoneNumber(),readerDto.getEmailAddress(),readerDto.getStatus(),readerDto.getPassword());
        if(readerDto.getReaderId() != null){
            reader.setId(readerDto.getReaderId());
        }
        if(readerDto.getUuid() != null){
            reader.setUuid(readerDto.getUuid());
        }
        return reader;
    }


    public List<Reader> mapToReaderList(final List<ReaderDto> readerDtos) {
        return readerDtos.stream()
                .map(r -> readerRepository.findById(r.getReaderId()).get())
                .collect(Collectors.toList());
    }
}
