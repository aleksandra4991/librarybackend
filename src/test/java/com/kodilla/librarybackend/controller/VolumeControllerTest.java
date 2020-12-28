package com.kodilla.librarybackend.controller;

import com.google.gson.Gson;
import com.kodilla.librarybackend.domain.Volume;
import com.kodilla.librarybackend.domain.VolumeDto;
import com.kodilla.librarybackend.mapper.VolumeMapper;
import com.kodilla.librarybackend.service.VolumeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VolumeController.class)
public class VolumeControllerTest {


    private static Gson gson = new Gson();
    private static final Volume volume = new Volume("Title1","Author1","01012020","xxxx");
    private static final VolumeDto volumeDto = new VolumeDto("Title2","Author2", "16092010","mmm");
    private final static String jsonContent = gson.toJson(volumeDto);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VolumeService volumeService;

    @MockBean
    private VolumeMapper volumeMapper;

    @Test
    public void getBooktAndCreateBookTest() throws Exception {
        String jsonContent = gson.toJson(volumeDto);
        when(volumeService.getBook(anyLong())).thenReturn(volume);
        when(volumeMapper.mapToBookDto(ArgumentMatchers.any())).thenReturn(volumeDto);

        mockMvc.perform(get("/myLibrary/book?bookId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createBookTest() throws Exception {
        when(volumeMapper.mapToBookDto(ArgumentMatchers.any())).thenReturn(volumeDto);
        when(volumeService.createBook(ArgumentMatchers.any())).thenReturn(volume);

        mockMvc.perform(post("/myLibrary/book")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    /*@Test
    public void updateBookTest() throws Exception{
        when(volumeMapper.mapToBook(ArgumentMatchers.any())).thenReturn(book1);
        when(volumeService.createBook(ArgumentMatchers.any())).thenReturn(book1);
        when(volumeMapper.mapToBookDto(ArgumentMatchers.any())).thenReturn(bookDto1);

        mockMvc.perform(MockMvcRequestBuilders.put("/myLibrary/updateBook")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }*/

}
