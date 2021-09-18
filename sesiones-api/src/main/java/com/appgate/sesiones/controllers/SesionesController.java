package com.appgate.sesiones.controllers;

import com.appgate.sesiones.exceptions.CustomException;
import com.appgate.sesiones.models.dto.SesionDto;
import com.appgate.sesiones.services.SesionesService;
import com.appgate.sesiones.utils.Constantes;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequestMapping("/sesiones")
@RestController
@Log4j2
public class SesionesController {
    private final SesionesService sesionesService;

    @Autowired
    public SesionesController(SesionesService sesionesService) {
        this.sesionesService = sesionesService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SesionDto>> sesionesVigentes() {
        return new ResponseEntity<>(sesionesService.sesionesActivas(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SesionDto> nuevaSesion() {
        SesionDto sesionDto = sesionesService.nuevaSesion();
        log.info(Constantes.SESION_CREADA);
        return new ResponseEntity<>(sesionDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{sesionId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> cerrarSesion(@PathVariable(name = "sesionId") String sesionId ) {
        sesionesService.cerrarSesion(getSesionDto(sesionId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private SesionDto getSesionDto(String sesionId) {
        try {
            return new SesionDto(UUID.fromString(sesionId));
        } catch (IllegalArgumentException e) {
            throw new CustomException(Constantes.SESION_INVALIDA);
        }
    }
}
