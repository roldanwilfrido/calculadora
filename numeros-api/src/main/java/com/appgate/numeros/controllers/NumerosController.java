package com.appgate.numeros.controllers;

import com.appgate.numeros.exceptions.ValidationException;
import com.appgate.numeros.models.dto.NumerosXSesionDto;
import com.appgate.numeros.services.NumerosService;
import com.appgate.numeros.services.SesionesClientService;
import com.appgate.numeros.utils.Constantes;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequestMapping("/numeros/{sesionId}")
@RestController
@Log4j2
public class NumerosController {
    private final NumerosService numerosService;
    private final SesionesClientService sesionesClientService;

    @Autowired
    public NumerosController(NumerosService numerosService, SesionesClientService sesionesClientService) {
        this.numerosService = numerosService;
        this.sesionesClientService = sesionesClientService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NumerosXSesionDto>> getTodosLosNumerosXSesion(@PathVariable(name = "sesionId") String sesionId) {
        sesionesClientService.validarSesion(sesionId);
        return new ResponseEntity<>(numerosService.getTodosLosNumerosXSesion(UUID.fromString(sesionId)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> agregar(@PathVariable(name = "sesionId") String sesionId, @RequestBody NumerosXSesionDto numero){
        if (Objects.isNull(numero.getNumero()))
            throw new ValidationException(Constantes.FALTA_NUMERO);
        sesionesClientService.validarSesion(sesionId);
        numero.setSesion(UUID.fromString(sesionId));
        numerosService.agregar(numero);
        log.info(Constantes.NUMERO_AGREGADO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> limpiarSesionYAgregarNuevoNumero(@PathVariable(name = "sesionId") String sesionId,
                                                           @RequestBody NumerosXSesionDto numero) {
        sesionesClientService.validarSesion(sesionId);
        numerosService.removerNumerosXSesion(UUID.fromString(sesionId), numero);

        log.info(Constantes.NUMEROS_REMOVIDOS);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
