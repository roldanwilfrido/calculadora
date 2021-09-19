package com.appgate.numeros.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class NumerosXSesionDto {

    public NumerosXSesionDto(UUID sesion, Number numero) {
        this.sesion = sesion;
        this.numero = numero;
    }

    @JsonIgnore
    private UUID sesion;

    private Number numero;
}
