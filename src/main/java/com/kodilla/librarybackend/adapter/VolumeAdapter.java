package com.kodilla.librarybackend.adapter;

import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.domain.VolumeDto;
import com.kodilla.librarybackend.service.VolumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VolumeAdapter {

    @Lazy
    @Autowired
    private VolumeService volumeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(VolumeAdapter.class);

    public List<Volume> createAllGoogleVolumeList(List<VolumeDto> volumeDtoList){
        LOGGER.info("Google books adapter starts...");
        return volumeDtoList.stream().map(v -> {
                    return new Volume(v.getTitle(),v.getAuthors(),v.getPublishedDate(),v.getDescription());
        }).collect(Collectors.toList());
    }

    public List <Volume> createSpecificVolume(List <VolumeDto> volumeDtoList){
        LOGGER.info("Specific google book adapter is looking for requested book");
        return volumeDtoList.stream().map(v -> {
            return new Volume(v.getTitle(),v.getAuthors(),v.getPublishedDate(),v.getDescription());
        }).collect(Collectors.toList());
    }
}
