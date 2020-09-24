package com.kodilla.librarybackend.controller;

import com.google.gson.Gson;
import com.kodilla.librarybackend.domain.*;
import com.kodilla.librarybackend.mapper.BookMapper;
import com.kodilla.librarybackend.service.BookService;
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
@WebMvcTest(BookController.class)
public class VolumeControllerTest {

    private final static Genre genre = new Genre ("Gatunek Testowy");
    private final static GenreDto genreDto = new GenreDto("Gatunek Testowy");
    private final static String title = new String("Dwaca");
    private final static String authors = new String("Luis Lowre");
    private final static Volume VOLUME_1 = new Volume(title,authors,genre);
    private final static VolumeDto VOLUME_DTO_1 = new VolumeDto(title,authors,genreDto.toString());
    private static Gson gson = new Gson();
    private final static String jsonContent = gson.toJson(VOLUME_DTO_1);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookMapper bookMapper;

    @Test
    public void getAllBooksTest() throws Exception{
        mockMvc.perform(get("/myLibrary/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

   /* @Test
    public void getBooksOfDefiniedAuthor() throws Exception{
        String jsonContent = gson.toJson(bookDto1);
        when(bookService.getBook(anyLong())).thenReturn(book1);
        when(bookMapper.mapToBookDto(ArgumentMatchers.any())).thenReturn(bookDto1);

        mockMvc.perform(get("/myLibrary/books/author?author=Autor1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }*/

    @Test
    public void getBooksOfSpecifiedTitleAndAuthor() throws Exception{
        String jsonContent = gson.toJson(VOLUME_DTO_1);
        when(bookService.getBook(anyLong())).thenReturn(VOLUME_1);
        when(bookMapper.mapToBookDto(ArgumentMatchers.any())).thenReturn(VOLUME_DTO_1);

        mockMvc.perform(get("/myLibrary/book/specified?title=Tytuł1&author=Autor1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



    @Test
    public void getBooktAndCreateBookTest() throws Exception {
        String jsonContent = gson.toJson(VOLUME_DTO_1);
        when(bookService.getBook(anyLong())).thenReturn(VOLUME_1);
        when(bookMapper.mapToBookDto(ArgumentMatchers.any())).thenReturn(VOLUME_DTO_1);

        mockMvc.perform(get("/myLibrary/book?bookId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createBookTest() throws Exception {
        when(bookMapper.mapToBookDto(ArgumentMatchers.any())).thenReturn(VOLUME_DTO_1);
        when(bookService.createBook(ArgumentMatchers.any())).thenReturn(VOLUME_1);

        mockMvc.perform(post("/myLibrary/book")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    /*@Test
    public void updateBookTest() throws Exception{
        when(bookMapper.mapToBook(ArgumentMatchers.any())).thenReturn(book1);
        when(bookService.createBook(ArgumentMatchers.any())).thenReturn(book1);
        when(bookMapper.mapToBookDto(ArgumentMatchers.any())).thenReturn(bookDto1);

        mockMvc.perform(MockMvcRequestBuilders.put("/myLibrary/updateBook")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }*/

}
