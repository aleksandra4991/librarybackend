package com.kodilla.librarybackend.controller;

import com.google.gson.Gson;
import com.kodilla.librarybackend.domain.Reservation;
import com.kodilla.librarybackend.domain.ReservationDto;
import com.kodilla.librarybackend.mapper.ReservationMapper;
import com.kodilla.librarybackend.service.ReservationService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    private final static Reservation reservation = new Reservation();
    private final static ReservationDto reservationDto = new ReservationDto();
    private static Gson gson = new Gson();
    private final static String jsonContent = gson.toJson(reservationDto);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private ReservationMapper reservationMapper;

    @Test
    public void testGetReservations() throws Exception {
        mockMvc.perform(get("/myLibrary/reservations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateReservation() throws Exception {
        Mockito.when(reservationMapper.mapToReservationDto(ArgumentMatchers.any())).thenReturn(reservationDto);
        Mockito.when(reservationService.createReservation(ArgumentMatchers.any())).thenReturn(reservation);

        mockMvc.perform(post("/myLibrary/reservation/create/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetSpecifiedReservation() throws Exception {
        Mockito.when(reservationService.getSpecifiedReservation(anyLong())).thenReturn(reservation);
        Mockito.when(reservationMapper.mapToReservationDto(ArgumentMatchers.any())).thenReturn(reservationDto);
        reservationDto.setReservationId(1L);

        mockMvc.perform(get("/myLibrary/reservation?reservationId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}
