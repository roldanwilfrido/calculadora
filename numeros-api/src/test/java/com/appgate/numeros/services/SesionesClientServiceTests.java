package com.appgate.numeros.services;


import com.appgate.numeros.components.UnirestWrapper;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class SesionesClientServiceTests {

    private UnirestWrapper wrapper = mock(UnirestWrapper.class);

    private SesionesClientService service;

    @Test
    void validarSesion() {
        String sesionId = UUID.randomUUID().toString();
        doNothing().when(wrapper).llamarServicio(anyString());

        service = new SesionesClientService(wrapper, "localhost:8877");
        service.validarSesion(sesionId);
        verify(wrapper, times(1)).llamarServicio(eq("localhost:8877/sesiones/" + sesionId));
    }
}
