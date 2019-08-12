package com.kodilla.librarybackend.service;

import com.kodilla.librarybackend.domain.Reader;
import com.kodilla.librarybackend.repository.ReaderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


import java.util.List;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.MatcherAssert.assertThat;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderServiceTest {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired(required = true)
    private ReaderService readerService;

    /*@Test
    public void testGetAllReaders(){

        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXX@gmail.com",true);
        Reader reader2 = new Reader("Karolina Zając","793333985","karolcia@gmail.com",true);


        //When
        readerRepository.save(reader1);
        readerRepository.save(reader2);
        List<Reader> requestedReaders = readerService.getAllReaders();

        //Then
        assertThat(reader1, sameBeanAs(requestedReaders.get(requestedReaders.size()-2)));
        assertThat(reader2, sameBeanAs(requestedReaders.get(requestedReaders.size()-1)));


        //Clean Up
        readerService.deleteReader(reader1.getId());
        readerService.deleteReader(reader2.getId());
    }*/

    @Test
    public void testGetReader(){

        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXX@gmail.com",true);
        readerRepository.save(reader1);

        //When
        Reader specifiedReader = readerService.getReader(reader1.getId());

        //Then
        assertThat(reader1, sameBeanAs(specifiedReader));

        //Clean Up
        readerService.deleteReader(reader1.getId());

    }

    @Test
    public void testDeleteReader(){
        //Given
        Reader reader1 = new Reader("Aleksandra Radzikowska","792333985","aradzikowskaXXX@gmail.com",true);
        readerRepository.save(reader1);
        Reader specifiedReader = readerService.getReader(reader1.getId());

        long readerCounterBeforeDeletion = readerRepository.count();

        //When
        readerService.deleteReader(specifiedReader.getId());
        long readerCounterAfterDeletion = readerRepository.count();

        //Then
        Assert.assertEquals(readerCounterBeforeDeletion - 1,readerCounterAfterDeletion );
    }
}
