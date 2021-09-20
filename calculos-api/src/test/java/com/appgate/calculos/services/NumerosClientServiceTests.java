package com.appgate.calculos.services;


import com.appgate.calculos.components.UnirestWrapper;
import com.appgate.calculos.exceptions.NumerosApiException;
import com.appgate.calculos.models.dto.NumerosDto;
import com.appgate.calculos.utils.Constantes;
import com.mashape.unirest.http.JsonNode;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class NumerosClientServiceTests {

    private UnirestWrapper wrapper = mock(UnirestWrapper.class);

    private NumerosClientService service;

    @Test
    void getNumerosXSesion() {
        String sesionId = UUID.randomUUID().toString();
        JsonNode json = new JsonNode("[\n" +
                "    {\n" +
                "        \"numero\": 20\n" +
                "    },\n" +
                "    {\n" +
                "        \"numero\": 30\n" +
                "    }\n" +
                "]");
        when(wrapper.getJson(anyString())).thenReturn(json);

        service = new NumerosClientService(wrapper, "localhost:8879");
        service.getNumerosXSesion(sesionId);
        verify(wrapper, times(1)).getJson(eq("localhost:8879/numeros/" + sesionId));
    }

    @Test
    void getNumerosXSesionFallaAlProcesarLaListaDeNumerosDeLaSesion() {
        String sesionId = UUID.randomUUID().toString();
        JsonNode json = new JsonNode("{\"error\":\"error\"}");
        when(wrapper.getJson(anyString())).thenReturn(json);

        service = new NumerosClientService(wrapper, "localhost:8879");
        NumerosApiException exception = assertThrows(NumerosApiException.class, () -> service.getNumerosXSesion(sesionId));
        assertTrue(Objects.equals(exception.getMessage(), Constantes.ERROR_OBTENIENDO_NUMEROS));
    }

    @Test
    void blanquearLaListaDeNumerosDeLaSesionYAgregarUnNumeroNuevo() {
        String sesionId = UUID.randomUUID().toString();
        when(wrapper.patch(anyString(), anyString())).thenReturn(new JsonNode(""));

        service = new NumerosClientService(wrapper, "localhost:8879");
        service.sobreescribirLosNumerosDeUnaSesion(sesionId, new NumerosDto());
        verify(wrapper, times(1)).patch(eq("localhost:8879/numeros/" + sesionId), anyString());
    }
}
