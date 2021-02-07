package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.domain.VolumeDto;
import com.kodilla.librarybackend.repository.VolumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VolumeMapper {

    @Lazy
    @Autowired
    private VolumeRepository volumeRepository;

    public List<VolumeDto> mapToBookDtoList(final List<Volume> volumes) {
        List<VolumeDto> volumeDtos = new ArrayList<>();
        for (Volume volume : volumes) {
            VolumeDto volumeDto = new VolumeDto(volume.getTitle(),volume.getAuthors(),volume.getPublishedDate(),volume.getDescription());
            volumeDto.setId(volume.getId());
            volumeDtos.add(volumeDto);
        }
        return volumeDtos;
    }

    public VolumeDto mapToBookDto(Volume volume) {
        VolumeDto volumeDto = new VolumeDto(volume.getTitle(),volume.getAuthors(),volume.getPublishedDate(),volume.getDescription());
        return volumeDto;
    }

    public Volume mapToBook(VolumeDto volumeDto) {
        if(volumeDto.getId() != null){
            return volumeRepository.getOne(volumeDto.getId());
        } else {
            return new Volume(volumeDto.getTitle(),volumeDto.getAuthors(),volumeDto.getPublishedDate(),volumeDto.getDescription());
        }

    }

    public List<Volume> mapToBookList(final List<VolumeDto> volumeDtos) {
        return volumeDtos.stream()
                .map(b -> volumeRepository.findById(b.getId()).get())
                .collect(Collectors.toList());
    }
}
