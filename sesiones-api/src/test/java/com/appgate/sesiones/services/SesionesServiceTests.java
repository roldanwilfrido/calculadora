package com.appgate.sesiones.services;


import com.appgate.sesiones.exceptions.NoExisteException;
import com.appgate.sesiones.models.dto.SesionDto;
import com.appgate.sesiones.models.entities.Sesiones;
import com.appgate.sesiones.repositories.SesionesRepository;
import com.appgate.sesiones.utils.Constantes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SesionesServiceTests {

    private SesionesRepository repo = mock(SesionesRepository.class);

    private SesionesService service;

    @Test
    void sesionesActivas() {
        Sesiones sesion = new Sesiones();
        sesion.setId(UUID.randomUUID());
        List<Sesiones> sesiones = Collections.singletonList(sesion);
        when(repo.findAll()).thenReturn(sesiones);

        service = new SesionesService(repo);
        Assertions.assertThat(service.sesionesActivas().get(0).getId()).isEqualTo(sesion.getId());
    }

    @Test
    void nuevaSesion() {
        Sesiones sesion = new Sesiones();
        sesion.setId(UUID.randomUUID());
        when(repo.save(any(Sesiones.class))).thenReturn(sesion);

        service = new SesionesService(repo);
        Assertions.assertThat(service.nuevaSesion().getId()).isEqualTo(sesion.getId());
    }

    @Test
    void cerrarSesion() {
        SesionDto sesionDto = new SesionDto(UUID.randomUUID());
        Sesiones sesion = new Sesiones();
        sesion.setId(sesionDto.getId());
        when(repo.findById(sesionDto.getId())).thenReturn(Optional.of(sesion));
        doNothing().when(repo).deleteById(sesionDto.getId());

        service = new SesionesService(repo);
        service.cerrarSesion(sesionDto);

        verify(repo, times(1)).deleteById(sesionDto.getId());
    }

    @Test
    void cerrarSesion_Exception() {
        SesionDto sesionDto = new SesionDto(UUID.randomUUID());
        when(repo.findById(sesionDto.getId())).thenReturn(Optional.empty());

        service = new SesionesService(repo);
        NoExisteException exception = assertThrows(NoExisteException.class, () -> service.cerrarSesion(sesionDto));
        assertTrue(Objects.equals(exception.getMessage(), Constantes.SESION_NO_EXISTE));
        verify(repo, times(0)).deleteById(sesionDto.getId());
    }
}
