package com.kodilla.librarybackend.controller;

import com.google.gson.Gson;
import com.kodilla.librarybackend.domain.Reader;
import com.kodilla.librarybackend.domain.ReaderDto;
import com.kodilla.librarybackend.mapper.ReaderMapper;
import com.kodilla.librarybackend.service.ReaderService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReaderContoller.class)
public class ReaderControllerTest {

    private final static Reader reader1 = new Reader("Aleksandra Radzikowska", true);
    private final static ReaderDto readerDto1 = new ReaderDto("Aleksandra Radzikowska",true);
    private static Gson gson = new Gson();
    private final static String jsonContent = gson.toJson(readerDto1);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReaderService readerService;
    @MockBean
    private ReaderMapper readerMapper;

    @Test
    public void testCreateReader() throws Exception {
        when(readerMapper.mapToReaderDto(ArgumentMatchers.any())).thenReturn(readerDto1);
        when(readerService.createReader(ArgumentMatchers.any())).thenReturn(reader1);

        mockMvc.perform(post("/myLibrary/reader")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void loginTest() throws Exception {

        //Given
        String emailAddress = "aleksandra9977@gmail.com";
        String password = "XyZ1@3";

        when(readerService.loginReader(emailAddress, password)).thenReturn(true);

        //When & Then
        mockMvc.perform(get("/myLibrary/reader/login").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .header("emailAddress", emailAddress)
                .header("password", password))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", Matchers.is(true)));
    }

    /*@Test
    public void testGetReaderByEmailAddressAndPassword() throws Exception {

        //Given
        Reader reader = new Reader("Aleksandra","678999056","aleksandraaaaa@gmail.com",true,"XyZ1@3");

        String emailAddress = "aleksandraaaaa@gmail.com";
        String password = "XyZ1@3";

        when(readerService.findReaderByLoginData(emailAddress, password)).thenReturn(reader);

        //When & Then
        mockMvc.perform(get("/myLibrary/reader/emailAddress/password").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .header("email", emailAddress)
                .header("password", password))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.name", Matchers.is("Aleksandra")));
    }*/

    @Test
    public void testGetReservationsOfSpecifiedReader() throws Exception {

        when(readerMapper.mapToReaderDto(ArgumentMatchers.any())).thenReturn(readerDto1);
        when(readerService.createReader(ArgumentMatchers.any())).thenReturn(reader1);

        mockMvc.perform(get("/myLibrary/reservation/reserved?readerId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRentedBooksOfSpecifiedReader() throws Exception {

        when(readerMapper.mapToReaderDto(ArgumentMatchers.any())).thenReturn(readerDto1);
        when(readerService.createReader(ArgumentMatchers.any())).thenReturn(reader1);

        mockMvc.perform(get("/myLibrary/books/rented?readerId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



    /*@Test
    public void testBlockReader() throws Exception {
        when(readerMapper.mapToReaderDto(ArgumentMatchers.any())).thenReturn(readerDto1);
        when(readerService.createReader(ArgumentMatchers.any())).thenReturn(reader1);

        mockMvc.perform(MockMvcRequestBuilders.put("/myLibrary/blockReader?readerId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }*/
}
