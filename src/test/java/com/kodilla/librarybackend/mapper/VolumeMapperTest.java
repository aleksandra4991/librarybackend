package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.domain.VolumeDto;
import com.kodilla.librarybackend.domain.Genre;
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
    private BookMapper bookMapper;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void mapToBookDtoList() {

        Genre genre = new Genre("Gatunek Testowy");
        genreRepository.save(genre);

        Volume volume1 = new Volume(genre.getId());
        Volume volume2 = new Volume(genre.getId());
        List<Volume> volumeList = new ArrayList<>(Arrays.asList(volume1, volume2));

        VolumeDto volumeDto1 = new VolumeDto("Dawca","Lois Lowry", genre.getId().toString());
        VolumeDto volumeDto2 = new VolumeDto("XXX","X Y Z",genre.getId().toString());
        List<VolumeDto> volumeDtos = new ArrayList<>(Arrays.asList(volumeDto1, volumeDto2));

        assertThat(volumeDtos, sameBeanAs(bookMapper.mapToBookDtoList(volumeList)));

        //Clean Up
        genreRepository.deleteAllInBatch();
    }

    @Test
    public void mapToBookDto() {

        Genre genre = new Genre("Gatunek Testowy");
        genreRepository.save(genre);

        Volume volume1 = new Volume(genre.getId());
        VolumeDto volumeDto1 = new VolumeDto("XXX","X Y Z", genre.getId().toString());

        assertThat(volumeDto1,sameBeanAs(bookMapper.mapToBookDto(volume1)));

        //Clean Up
        genreRepository.deleteAllInBatch();
    }

    @Test
    public void mapToBook() {

        Genre genre = new Genre("Gatunek Testowy");
        genreRepository.save(genre);

        Volume volume1 = new Volume(genre.getId());
        VolumeDto volumeDto1 = new VolumeDto("XXX","X Y Z", genre.getId().toString());

        assertThat(volume1,sameBeanAs(bookMapper.mapToBook(volumeDto1)));

        //Clean Up
        genreRepository.deleteAllInBatch();
    }
}
