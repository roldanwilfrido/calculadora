package com.appgate.numeros.services;


import com.appgate.numeros.models.dto.NumerosXSesionDto;
import com.appgate.numeros.models.entities.NumerosXSesion;
import com.appgate.numeros.repositories.NumerosXSesionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NumerosServiceTests {

    private NumerosXSesionRepository repo = mock(NumerosXSesionRepository.class);

    private NumerosService service;

    @Test
    void getTodosLosNumerosXSesion() {
        UUID sesionId = UUID.randomUUID();
        NumerosXSesion numerosXSesion = new NumerosXSesion();
        numerosXSesion.setSesionId(sesionId);
        numerosXSesion.setNumero(-43.8);
        List<NumerosXSesion> numerosXSesions = Collections.singletonList(numerosXSesion);
        when(repo.findAllBySesionId(sesionId)).thenReturn(numerosXSesions);

        service = new NumerosService(repo);
        Assertions.assertThat(service.getTodosLosNumerosXSesion(sesionId).get(0).getNumero()).isEqualTo(numerosXSesion.getNumero());
    }

    @Test
    void agregar() {
        UUID sesionId = UUID.randomUUID();
        NumerosXSesion numerosXSesion = new NumerosXSesion();
        numerosXSesion.setSesionId(sesionId);
        numerosXSesion.setNumero(76.1);
        NumerosXSesionDto dto = new NumerosXSesionDto(sesionId, numerosXSesion.getNumero());
        when(repo.save(eq(numerosXSesion))).thenReturn(numerosXSesion);

        service = new NumerosService(repo);
        service.agregar(dto);
        verify(repo, times(1)).save(any(NumerosXSesion.class));
    }

    @Test
    void removerNumerosXSesion() {
        UUID sesionId = UUID.randomUUID();
        NumerosXSesion numerosXSesion = new NumerosXSesion();
        numerosXSesion.setSesionId(sesionId);
        numerosXSesion.setNumero(17);
        NumerosXSesionDto dto = new NumerosXSesionDto(sesionId, numerosXSesion.getNumero());
        doNothing().when(repo).borrarNumerosPorSesion(sesionId);
        when(repo.save(eq(numerosXSesion))).thenReturn(numerosXSesion);

        service = new NumerosService(repo);
        service.sobreescribirLosNumerosDeUnaSesion(sesionId, dto);

        verify(repo, times(1)).save(any(NumerosXSesion.class));
    }
}
