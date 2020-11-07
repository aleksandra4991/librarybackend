package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.domain.VolumeDto;
import com.kodilla.librarybackend.repository.VolumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VolumeMapper {

    @Autowired
    private VolumeRepository volumeRepository;

    public List<VolumeDto> mapToBookDtoList(final List<Volume> volumes) {
        List<VolumeDto> volumeDtos = new ArrayList<>();
        for (Volume volume : volumes) {
            VolumeDto volumeDto = new VolumeDto(volume.getTitle(),volume.getAuthors());
            volumeDto.setBookId(volume.getId());
            volumeDtos.add(volumeDto);
        }
        return volumeDtos;
    }

    public VolumeDto mapToBookDto(Volume volume) {
        VolumeDto volumeDto = new VolumeDto(volume.getTitle(),volume.getAuthors());
        return volumeDto;
    }

    public Volume mapToBook(VolumeDto volumeDto) {
        if(volumeDto.getBookId() != null){
            return volumeRepository.getOne(volumeDto.getBookId());
        } else {
            return new Volume(volumeDto.getTitle(),volumeDto.getAuthors());
        }

    }

    public List<Volume> mapToBookList(final List<VolumeDto> volumeDtos) {
        return volumeDtos.stream()
                .map(b -> volumeRepository.findById(b.getBookId()).get())
                .collect(Collectors.toList());
    }
}
