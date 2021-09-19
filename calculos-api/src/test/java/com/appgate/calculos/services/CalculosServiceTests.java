package com.appgate.calculos.services;


import com.appgate.calculos.exceptions.CustomException;
import com.appgate.calculos.models.dto.NumerosDto;
import com.appgate.calculos.models.dto.RespuestaDto;
import com.appgate.calculos.utils.Constantes;
import com.appgate.calculos.utils.OperacionesEnum;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CalculosServiceTests {

    private NumerosClientService numerosClientService = mock(NumerosClientService.class);

    private OperacionesService operacionesService = mock(OperacionesService.class);

    private CalculosService service;

    @Test
    void realizarOperacion() {
        String sesionId = UUID.randomUUID().toString();
        for (OperacionesEnum operacionesEnum: OperacionesEnum.values()) {
            List<NumerosDto> numerosDeLaSesion = Arrays.asList(getMockNumeroDto(), getMockNumeroDto());
            when(numerosClientService.getNumerosXSesion(sesionId)).thenReturn(numerosDeLaSesion);
            when(operacionesService.getResultado(operacionesEnum, numerosDeLaSesion)).thenReturn(new RespuestaDto(45));

            service = new CalculosService(numerosClientService, operacionesService);
            service.realizarOperacion(sesionId, operacionesEnum);
        }
        verify(numerosClientService, times(5)).blanquearLaListaDeNumerosDeLaSesionYAgregarUnNumeroNuevo(eq(sesionId), any(NumerosDto.class));
    }

    @Test
    void realizarOperacionFallaPorInsuficientesNumerosParaRealizarLaOperacion_Exception() {
        String sesionId = UUID.randomUUID().toString();
        List<NumerosDto> numerosDeLaSesion = Collections.singletonList(getMockNumeroDto());
        when(numerosClientService.getNumerosXSesion(sesionId)).thenReturn(numerosDeLaSesion);

        service = new CalculosService(numerosClientService, operacionesService);
        CustomException exception = assertThrows(CustomException.class, () -> service.realizarOperacion(sesionId, OperacionesEnum.SUMA));
        assertTrue(Objects.equals(exception.getMessage(), Constantes.INSUFICIENTES_NUMEROS));
        verify(numerosClientService, times(0)).blanquearLaListaDeNumerosDeLaSesionYAgregarUnNumeroNuevo(eq(sesionId), any(NumerosDto.class));
    }

    private NumerosDto getMockNumeroDto() {
        NumerosDto dto = new NumerosDto();
        dto.setNumero(new Random().nextDouble());
        return dto;
    }
}
