package com.kodilla.librarybackend.mapper;

import com.kodilla.librarybackend.domain.Reader;
import com.kodilla.librarybackend.domain.ReaderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderMapperTest {

    @Autowired
    private ReaderMapper readerMapper;

    private final static Reader reader1 = new Reader("Aleksandra Radzikowska", true);
    private final static Reader reader2 = new Reader("Sylwia Radzikowska", true);


    private final static ReaderDto readerDto1 = new ReaderDto("Aleksandra Radzikowska",true);
    private final static ReaderDto readerDto2 = new ReaderDto("Sylwia Radzikowska",true);

    private final static List<Reader> readerList = new ArrayList<>(Arrays.asList(reader1, reader2));
    private final static List<ReaderDto> readerDtoList = new ArrayList<>(Arrays.asList(readerDto1, readerDto2));

    @Test
    public void mapToReaderDtoList() {
        assertThat(readerDtoList, sameBeanAs(readerMapper.mapToReaderDtoList(readerList)));
    }

    @Test
    public void mapToReaderDto() {
        assertThat(readerDto1, sameBeanAs(readerMapper.mapToReaderDto(reader1)));
    }

    @Test
    public void mapToReader() {
        assertThat(reader2, sameBeanAs(readerMapper.mapToReader(readerDto2)));
    }

}
