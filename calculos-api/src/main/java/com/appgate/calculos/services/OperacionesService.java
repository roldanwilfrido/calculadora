package com.appgate.calculos.services;

import com.appgate.calculos.exceptions.CustomException;
import com.appgate.calculos.models.dto.NumerosDto;
import com.appgate.calculos.models.dto.RespuestaDto;
import com.appgate.calculos.utils.Constantes;
import com.appgate.calculos.utils.OperacionesEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static com.appgate.calculos.utils.OperacionesEnum.*;

@Service
public class OperacionesService {

    public RespuestaDto getResultado(OperacionesEnum operacion, List<NumerosDto> numeros) {
        AtomicReference<Double> respuesta = new AtomicReference<>();
        if (operacion == SUMA) {
            realizarSuma(numeros, respuesta);
        }else if (operacion == RESTA) {
            realizarResta(numeros, respuesta);
        } else if (operacion == MULTIPLICACION) {
            realizarMultiplicacion(numeros, respuesta);
        } else if (operacion == DIVISION) {
            realizarDivision(numeros, respuesta);
        } else if (operacion == POTENCIACION) {
            realizarPotenciacion(numeros, respuesta);
        }

        return new RespuestaDto(respuesta.get());
    }

    private void realizarSuma(List<NumerosDto> numeros, AtomicReference<Double> respuesta) {
        numeros.forEach(nxs -> {
            if (Objects.isNull(respuesta.get())) {
                respuesta.updateAndGet(v -> nxs.getNumero().doubleValue());
            } else {
                respuesta.updateAndGet(v -> v + nxs.getNumero().doubleValue());
            }
        });
    }

    private void realizarResta(List<NumerosDto> numeros, AtomicReference<Double> respuesta) {
        numeros.forEach(nxs -> {
            if (Objects.isNull(respuesta.get())) {
                respuesta.updateAndGet(v -> nxs.getNumero().doubleValue());
            } else {
                respuesta.updateAndGet(v -> v - nxs.getNumero().doubleValue());
            }
        });
    }

    private void realizarMultiplicacion(List<NumerosDto> numeros, AtomicReference<Double> respuesta) {
        numeros.forEach(nxs -> {
            if (Objects.isNull(respuesta.get())) {
                respuesta.updateAndGet(v -> nxs.getNumero().doubleValue());
            } else {
                respuesta.updateAndGet(v -> v * nxs.getNumero().doubleValue());
            }
        });
    }

    private void realizarDivision(List<NumerosDto> numeros, AtomicReference<Double> respuesta) {
        numeros.forEach(nxs -> {
            if (Objects.isNull(respuesta.get())) {
                respuesta.updateAndGet(v -> nxs.getNumero().doubleValue());
            } else {
                if (nxs.getNumero().doubleValue() == 0) {
                    throw new CustomException(Constantes.DIVISION_0);
                }
                respuesta.updateAndGet(v -> v / nxs.getNumero().doubleValue());
            }
        });
    }

    private void realizarPotenciacion(List<NumerosDto> numeros, AtomicReference<Double> respuesta) {
        numeros.forEach(nxs -> {
            if (Objects.isNull(respuesta.get())) {
                respuesta.updateAndGet(v -> nxs.getNumero().doubleValue());
            } else {
                if (nxs.getNumero().doubleValue() != (Integer) nxs.getNumero() || nxs.getNumero().doubleValue() < 0) {
                    throw new CustomException(Constantes.POTENCIACION_IMAGINARIO);
                }
                respuesta.updateAndGet(v -> Math.pow(v, nxs.getNumero().doubleValue()));
            }
        });
        if (respuesta.get().toString().equals("Infinity")) {
            throw new CustomException(Constantes.NUMERO_INFINITO);
        }
    }
}
