package com.appgate.numeros.services;

import com.appgate.numeros.models.dto.NumerosXSesionDto;
import com.appgate.numeros.models.entities.NumerosXSesion;
import com.appgate.numeros.repositories.NumerosXSesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NumerosService {
    private NumerosXSesionRepository numerosXSesionRepository;

    @Autowired
    public NumerosService(NumerosXSesionRepository numerosXSesionRepository) {
        this.numerosXSesionRepository = numerosXSesionRepository;
    }

    public List<NumerosXSesionDto> getTodosLosNumerosXSesion(UUID sesionId) {
        return numerosXSesionRepository.findAllBySesionId(sesionId).stream()
                .map(nxs -> new NumerosXSesionDto(nxs.getSesionId(), nxs.getNumero()))
                .collect(Collectors.toList());
    }

    public void agregar(NumerosXSesionDto dto) {
        numerosXSesionRepository.save(objetoBuilder(dto));
    }

    public void removerNumerosXSesion(UUID sesionId, NumerosXSesionDto dto) {
        numerosXSesionRepository.borrarNumerosPorSesion(sesionId);
        dto.setSesion(sesionId);
        numerosXSesionRepository.save(objetoBuilder(dto));
    }

    private NumerosXSesion objetoBuilder(NumerosXSesionDto dto){
        NumerosXSesion objeto = new NumerosXSesion();
        objeto.setSesionId(dto.getSesion());
        objeto.setNumero(dto.getNumero());
        return objeto;
    }
}
