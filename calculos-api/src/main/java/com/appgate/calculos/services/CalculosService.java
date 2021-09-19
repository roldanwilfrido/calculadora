package com.appgate.calculos.services;

import com.appgate.calculos.exceptions.CustomException;
import com.appgate.calculos.models.dto.NumerosDto;
import com.appgate.calculos.models.dto.RespuestaDto;
import com.appgate.calculos.utils.Constantes;
import com.appgate.calculos.utils.OperacionesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculosService {
    private NumerosClientService numerosClientService;
    private OperacionesService operacionesService;

    @Autowired
    public CalculosService(NumerosClientService numerosClientService, OperacionesService operacionesService) {
        this.numerosClientService = numerosClientService;
        this.operacionesService = operacionesService;
    }

    public RespuestaDto realizarOperacion(String sesionId, OperacionesEnum operacion) {
        List<NumerosDto> numeros = numerosClientService.getNumerosXSesion(sesionId);
        if (numeros.size() < 2) {
            throw new CustomException(Constantes.INSUFICIENTES_NUMEROS);
        }
        RespuestaDto respuestaDto = operacionesService.getResultado(operacion, numeros);
        NumerosDto dto = new NumerosDto();
        dto.setNumero(respuestaDto.getResultado());
        numerosClientService.blanquearLaListaDeNumerosDeLaSesionYAgregarUnNumeroNuevo(sesionId, dto);
        return respuestaDto;
    }
}
