package com.appgate.numeros.exceptions;

import com.appgate.numeros.utils.Constantes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SesionesApiException.class)
    public ResponseEntity<ExceptionResponse> errorSesionesApi(SesionesApiException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST);
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecursoException.class)
    public ResponseEntity<ExceptionResponse> recursoException(RecursoException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponse> errorDeValidacion(ValidationException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST);
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> noEsNumero(HttpMessageNotReadableException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode(HttpStatus.BAD_REQUEST);
        response.setErrorMessage(Constantes.FALTA_NUMERO);
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
