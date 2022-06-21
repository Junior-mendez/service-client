package com.co.pragma.serviceclient.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ErrorHandlerException {

    @ExceptionHandler(value =  {ClienteNotFoundException.class})
    public ResponseEntity<ErrorResponse> notFoundException(ClienteNotFoundException ex){
        return new ResponseEntity<>(ErrorResponse.builder().error("Cliente no encontrado")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .timestamp(new Date().toLocaleString()).build(),HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
    @ExceptionHandler(value =  {ClienteCreateException.class})
    public ResponseEntity<ErrorResponse> notFoundException(ClienteCreateException ex){
        return new ResponseEntity<>(ErrorResponse.builder().error("Cliente no creado")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .timestamp(new Date().toLocaleString()).build(),HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleMissingRequestBody(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder().error("Cuerpo de llamada no es válido"+ request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getCause().toString())
                .timestamp(new Date().toLocaleString()).build(),HttpStatus.BAD_REQUEST);
    }
}
