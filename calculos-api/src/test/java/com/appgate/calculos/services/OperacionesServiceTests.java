package com.appgate.calculos.services;


import com.appgate.calculos.exceptions.CustomException;
import com.appgate.calculos.models.dto.NumerosDto;
import com.appgate.calculos.utils.Constantes;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.appgate.calculos.utils.OperacionesEnum.*;
import static org.junit.jupiter.api.Assertions.*;

public class OperacionesServiceTests {

    private OperacionesService service;

    @Test
    void getResultado() {
        service = new OperacionesService();
        NumerosDto dto1 = new NumerosDto();
        dto1.setNumero(5);
        NumerosDto dto2 = new NumerosDto();
        dto2.setNumero(2);
        List<NumerosDto> numerosDeLaSesion = Arrays.asList(dto1, dto2);
        Arrays.stream(values()).forEach(operacion -> {
            if (operacion == SUMA) {
                assertEquals(service.getResultado(operacion, numerosDeLaSesion).getResultado(), 7.0);
            } else if (operacion == RESTA) {
                assertEquals(service.getResultado(operacion, numerosDeLaSesion).getResultado(), 3.0);
            } else if (operacion == MULTIPLICACION) {
                assertEquals(service.getResultado(operacion, numerosDeLaSesion).getResultado(), 10.0);
            } else if (operacion == DIVISION) {
                assertEquals(service.getResultado(operacion, numerosDeLaSesion).getResultado(), 2.5);
            } else if (operacion == POTENCIACION) {
                assertEquals(service.getResultado(operacion, numerosDeLaSesion).getResultado(), 25.0);
            }
        });
    }

    @Test
    void getResultadoFallaPorDivisionPor0_Exception() {
        service = new OperacionesService();
        NumerosDto dto1 = new NumerosDto();
        dto1.setNumero(5);
        NumerosDto dto2 = new NumerosDto();
        dto2.setNumero(0);
        List<NumerosDto> numerosDeLaSesion = Arrays.asList(dto1, dto2);

        CustomException exception = assertThrows(CustomException.class, () -> service.getResultado(DIVISION, numerosDeLaSesion));
        assertTrue(Objects.equals(exception.getMessage(), Constantes.DIVISION_0));
    }

    @Test
    void getResultadoFallaPorPotenciacionConNumerosImaginarios_Exception() {
        service = new OperacionesService();
        NumerosDto dto1 = new NumerosDto();
        dto1.setNumero(-3);
        NumerosDto dto2 = new NumerosDto();
        dto2.setNumero(-100);
        List<NumerosDto> numerosDeLaSesion = Arrays.asList(dto1, dto2);

        CustomException exception = assertThrows(CustomException.class, () -> service.getResultado(POTENCIACION, numerosDeLaSesion));
        assertTrue(Objects.equals(exception.getMessage(), Constantes.POTENCIACION_IMAGINARIO));
    }

    @Test
    void getResultadoFallaPorPotenciacionConResultadoInfinito_Exception() {
        service = new OperacionesService();
        NumerosDto dto1 = new NumerosDto();
        dto1.setNumero(38000);
        NumerosDto dto2 = new NumerosDto();
        dto2.setNumero(10000);
        List<NumerosDto> numerosDeLaSesion = Arrays.asList(dto1, dto2);

        CustomException exception = assertThrows(CustomException.class, () -> service.getResultado(POTENCIACION, numerosDeLaSesion));
        assertTrue(Objects.equals(exception.getMessage(), Constantes.NUMERO_INFINITO));
    }
}
