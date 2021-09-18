package com.appgate.sesiones.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SesionDto {
    private UUID id;

    public SesionDto(UUID id) {
        this.id = id;
    }
}
