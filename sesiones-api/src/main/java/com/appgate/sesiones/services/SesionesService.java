package com.appgate.sesiones.services;

import com.appgate.sesiones.exceptions.NoExisteException;
import com.appgate.sesiones.models.dto.SesionDto;
import com.appgate.sesiones.models.entities.Sesiones;
import com.appgate.sesiones.repositories.SesionesRepository;
import com.appgate.sesiones.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SesionesService {
    private SesionesRepository sesionesRepository;

    @Autowired
    public SesionesService(SesionesRepository sesionesRepository) {
        this.sesionesRepository = sesionesRepository;
    }

    public List<SesionDto> sesionesActivas() {
        return sesionesRepository.findAll().stream()
                .map(sesion -> new SesionDto(sesion.getId()))
                .collect(Collectors.toList());
    }

    public SesionDto nuevaSesion() {
        return new SesionDto(sesionesRepository.save(new Sesiones()).getId());
    }

    public void cerrarSesion(SesionDto sesionDto) {
        validarSiExisteSesion(sesionDto);
        sesionesRepository.deleteById(sesionDto.getId());
    }

    public void validarSiExisteSesion(SesionDto sesionDto) {
        if (sesionesRepository.findById(sesionDto.getId()).isEmpty())
            throw new NoExisteException(Constantes.SESION_NO_EXISTE);
    }
}
