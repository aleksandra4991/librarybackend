package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.domain.VolumeDto;
import com.kodilla.librarybackend.repository.GenreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VolumeMapperTest {

    @Autowired
    private VolumeMapper volumeMapper;

    @Autowired
    private GenreRepository genreRepository;

    private static final Volume volume1 = new Volume("Title1","Author1","01012020","xxxx");
    private static final Volume volume2 = new Volume("Title2","Author2", "16092010","mmm");
    private static final VolumeDto volumeDto1 = new VolumeDto("Title1","Author1","01012020","xxxx");
    private static final VolumeDto volumeDto2 = new VolumeDto("Title2","Author2", "16092010","mmm");

    private static final List<Volume> volumeList = new ArrayList<>(Arrays.asList(volume1,volume2));
    private static final List<VolumeDto> volumeDtoList = new ArrayList<>(Arrays.asList(volumeDto1,volumeDto2));

    @Test
    public void mapToBookDtoList() {


        assertThat(volumeDtoList, sameBeanAs(volumeMapper.mapToBookDtoList(volumeList)));

        //Clean Up
        genreRepository.deleteAllInBatch();
    }

    @Test
    public void mapToBookDto() {

        assertThat(volumeDto1,sameBeanAs(volumeMapper.mapToBookDto(volume1)));

        //Clean Up
        genreRepository.deleteAllInBatch();
    }

    @Test
    public void mapToBook() {

        assertThat(volume1,sameBeanAs(volumeMapper.mapToBook(volumeDto1)));

        //Clean Up
        genreRepository.deleteAllInBatch();
    }
}
