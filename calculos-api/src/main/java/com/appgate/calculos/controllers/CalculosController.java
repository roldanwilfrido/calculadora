package com.appgate.calculos.controllers;

import com.appgate.calculos.exceptions.CustomException;
import com.appgate.calculos.models.dto.RespuestaDto;
import com.appgate.calculos.services.CalculosService;
import com.appgate.calculos.utils.Constantes;
import com.appgate.calculos.utils.OperacionesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/calculos")
@RestController
public class CalculosController {
    private final CalculosService calculosService;

    @Autowired
    public CalculosController(CalculosService calculosService) {
        this.calculosService = calculosService;
    }

    @RequestMapping(value = "/{sesionId}/{operacion}", method = RequestMethod.GET)
    public ResponseEntity<RespuestaDto> realizarOperacion(
            @PathVariable(name = "sesionId") String sesionId, @PathVariable(name = "operacion") String operacion) {
        return new ResponseEntity<>(
                calculosService.realizarOperacion(getSesionId(sesionId), getOperacion(operacion)), HttpStatus.OK);
    }

    private String getSesionId(String sesionId) {
        try {
            return UUID.fromString(sesionId).toString();
        } catch (IllegalArgumentException e) {
            throw new CustomException(Constantes.SESION_INVALIDA);
        }
    }

    private OperacionesEnum getOperacion(String operacion) {
        try {
            return OperacionesEnum.valueOf(operacion.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CustomException(Constantes.OPERACION_INVALIDA);
        }
    }
}
