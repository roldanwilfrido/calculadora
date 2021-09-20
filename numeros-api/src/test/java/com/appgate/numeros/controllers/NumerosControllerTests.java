package com.appgate.numeros.controllers;

import com.appgate.numeros.exceptions.RecursoException;
import com.appgate.numeros.exceptions.SesionesApiException;
import com.appgate.numeros.models.dto.NumerosXSesionDto;
import com.appgate.numeros.services.NumerosService;
import com.appgate.numeros.services.SesionesClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class NumerosControllerTests {
    @MockBean
    private NumerosService service;
    @MockBean
    private SesionesClientService sesionesClientService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void getTodosLosNumerosXSesion() throws Exception {
        UUID sesionId = UUID.randomUUID();
        NumerosXSesionDto dto = new NumerosXSesionDto(sesionId, 5.9);
        List<NumerosXSesionDto> numerosXSesionDtos = Collections.singletonList(dto);
        when(service.getTodosLosNumerosXSesion(sesionId)).thenReturn(numerosXSesionDtos);
        mockMvc.perform(get("/numeros/" + sesionId)).andDo(print()).andExpect(content().string(mapper.writeValueAsString(numerosXSesionDtos)));
    }

    @Test
    void agregarNumero() throws Exception {
        UUID sesionId = UUID.randomUUID();
        NumerosXSesionDto dto = new NumerosXSesionDto(sesionId, -1.4);
        doNothing().when(service).agregar(dto);
        mockMvc.perform(post("/numeros/" + sesionId)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto))).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void agregarNumero_Exception() throws Exception {
        UUID sesionId = UUID.randomUUID();
        mockMvc.perform(post("/numeros/" + sesionId)
                .contentType(MediaType.APPLICATION_JSON).content("{}")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void agregarNumeroFallaCuandoNoSeEnviaTextoYNoUnNumero() throws Exception {
        UUID sesionId = UUID.randomUUID();
        mockMvc.perform(post("/numeros/" + sesionId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numero\": \"texto\"}")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void limpiarSesionYAgregarNuevoNumero() throws Exception {
        UUID sesionId = UUID.randomUUID();
        NumerosXSesionDto dto = new NumerosXSesionDto(sesionId, 21.7);
        doNothing().when(service).sobreescribirLosNumerosDeUnaSesion(sesionId, dto);
        mockMvc.perform(patch("/numeros/" + sesionId)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto))).andExpect(status().isOk());
    }

    @Test
    void errorCuandoSesionNoExiste() throws Exception {
        UUID sesionId = UUID.randomUUID();
        NumerosXSesionDto dto = new NumerosXSesionDto(sesionId, 58);
        doThrow(new SesionesApiException("")).when(sesionesClientService).validarSesion(sesionId.toString());
        mockMvc.perform(patch("/numeros/" + sesionId)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto))).andExpect(status().isBadRequest());
    }

    @Test
    void errorCuandoSesionAPINoEstaDisponible() throws Exception {
        UUID sesionId = UUID.randomUUID();
        NumerosXSesionDto dto = new NumerosXSesionDto(sesionId, 20008);
        doThrow(new RecursoException("")).when(sesionesClientService).validarSesion(sesionId.toString());
        mockMvc.perform(patch("/numeros/" + sesionId)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dto))).andExpect(status().is5xxServerError());
    }
}
