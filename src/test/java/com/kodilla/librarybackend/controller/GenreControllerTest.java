package com.kodilla.librarybackend.controller;

import com.google.gson.Gson;
import com.kodilla.librarybackend.domain.Genre;
import com.kodilla.librarybackend.domain.GenreDto;
import com.kodilla.librarybackend.mapper.GenreMapper;
import com.kodilla.librarybackend.service.GenreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GenreController.class)
public class GenreControllerTest {

    private final static Genre genre1 = new Genre("genre1");
    private final static GenreDto genreDto1 = new GenreDto(1L,"genre1");
    private static Gson gson = new Gson();
    private final static String jsonContent = gson.toJson(genreDto1);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreService;

    @MockBean
    private GenreMapper genreMapper;

    @Test
    public void testGetAllGenres() throws Exception {
        mockMvc.perform(get("/myLibrary/genres")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetSpecifiedGenre() throws Exception {
        Mockito.when(genreService.getSpecifiedGenre(anyLong())).thenReturn(genre1);
        Mockito.when(genreMapper.mapToGenreDto(ArgumentMatchers.any())).thenReturn(genreDto1);

        mockMvc.perform(get("/myLibrary/genre?genreId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateNewGenre() throws Exception {
        Mockito.when(genreMapper.mapToGenreDto(ArgumentMatchers.any())).thenReturn(genreDto1);
        Mockito.when(genreService.createNewGenre(ArgumentMatchers.any())).thenReturn(genre1);

        mockMvc.perform(post("/myLibrary/genre")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    /*@Test
    public void testUpdateSpecifiedGenre() throws Exception{
        Mockito.when(genreMapper.mapToGenre(ArgumentMatchers.any())).thenReturn(genre1);
        Mockito.when(genreService.createNewGenre(ArgumentMatchers.any())).thenReturn(genre1);
        Mockito.when(genreMapper.mapToGenreDto(ArgumentMatchers.any())).thenReturn(genreDto1);

        mockMvc.perform(MockMvcRequestBuilders.put("/myLibrary/updateSpecifiedGenre")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }*/
}
