package com.appgate.sesiones.controllers;

import com.appgate.sesiones.models.dto.SesionDto;
import com.appgate.sesiones.services.SesionesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SesionesControllerTests {
    @MockBean
    private SesionesService service;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void sesionesActivas() throws Exception {
        SesionDto sesionDto = new SesionDto(UUID.randomUUID());
        List<SesionDto> sesiones = Collections.singletonList(sesionDto);
        when(service.sesionesActivas()).thenReturn(sesiones);
        mockMvc.perform(get("/sesiones")).andDo(print()).andExpect(content().string(mapper.writeValueAsString(sesiones)));
    }

    @Test
    void validarSiExisteSesion() throws Exception {
        SesionDto sesionDto = new SesionDto(UUID.randomUUID());
        doNothing().when(service).validarSiExisteSesion(eq(sesionDto));
        mockMvc.perform(get("/sesiones/" + sesionDto.getId())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void nuevaSesion() throws Exception {
        SesionDto sesionDto = new SesionDto(UUID.randomUUID());
        when(service.nuevaSesion()).thenReturn(sesionDto);
        mockMvc.perform(post("/sesiones")).andDo(print()).andExpect(content().string(mapper.writeValueAsString(sesionDto)));
    }

    @Test
    void cerrarSesion() throws Exception {
        SesionDto sesionDto = new SesionDto(UUID.randomUUID());
        doNothing().when(service).cerrarSesion(eq(sesionDto));
        mockMvc.perform(delete("/sesiones/" + sesionDto.getId())).andExpect(status().isOk());
    }
}
