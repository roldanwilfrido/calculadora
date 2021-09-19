package com.appgate.calculos.controllers;

import com.appgate.calculos.models.dto.RespuestaDto;
import com.appgate.calculos.services.CalculosService;
import com.appgate.calculos.utils.OperacionesEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CalculosControllerTests {
    @MockBean
    private CalculosService service;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void realizarOperacion() throws Exception {
        UUID sesionId = UUID.randomUUID();
        String operacion = "suma";
        RespuestaDto respuesta = new RespuestaDto(110.5);
        when(service.realizarOperacion(anyString(), any(OperacionesEnum.class))).thenReturn(respuesta);
        mockMvc.perform(get("/calculos/" + sesionId + "/" + operacion))
                .andDo(print()).andExpect(content().string(mapper.writeValueAsString(respuesta)));
    }

    @Test
    void realizarOperacionFallaPorOperacionInvalida_Exception() throws Exception {
        UUID sesionId = UUID.randomUUID();
        String operacion = "invalida";
        mockMvc.perform(get("/calculos/" + sesionId + "/" + operacion))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void realizarOperacionFallaPorSesionIdInvalido_Exception() throws Exception {
        String sesionId = "Invalido";
        String operacion = "resta";
        mockMvc.perform(get("/calculos/" + sesionId + "/" + operacion))
                .andDo(print()).andExpect(status().isBadRequest());
    }
}
