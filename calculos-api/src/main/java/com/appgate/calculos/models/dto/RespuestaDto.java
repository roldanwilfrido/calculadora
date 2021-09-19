package com.appgate.calculos.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaDto {

    public RespuestaDto(Number resultado) {
        this.resultado = resultado;
    }

    private Number resultado;
}
