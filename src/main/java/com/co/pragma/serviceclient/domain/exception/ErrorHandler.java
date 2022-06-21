package com.co.pragma.serviceclient.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value =  {ClientNotFoundDocumentException.class})
    public ResponseEntity<ErrorResponse> notFoundException(ClientNotFoundDocumentException ex){
        return new ResponseEntity<>(ErrorResponse.builder().error("Cliente no encontrado")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .timestamp(new Date().toString()).build(),HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
    @ExceptionHandler(value =  {ClientNotFoundIdException.class})
    public ResponseEntity<ErrorResponse> notFoundIdException(ClientNotFoundIdException ex){
        return new ResponseEntity<>(ErrorResponse.builder().error("Cliente no existe")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .timestamp(new Date().toString()).build(),HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
    @ExceptionHandler(value =  {ClientCreateException.class})
    public ResponseEntity<ErrorResponse> notFoundException(ClientCreateException ex){
        return new ResponseEntity<>(ErrorResponse.builder().error("Cliente no creado")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .timestamp(new Date().toString()).build(),HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMissingRequestBody(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder().error("Cuerpo de llamada no es válido"+ request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getCause().toString())
                .timestamp(new Date().toString()).build(),HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ClientUpdateException.class)
    public ResponseEntity<ErrorResponse> notUpdateClient(ClientUpdateException ex) {
    	 return new ResponseEntity<>(ErrorResponse.builder().error("Cliente no actualizado")
                 .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                 .message(ex.getMessage())
                 .timestamp(new Date().toString()).build(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ClientDisableException.class)
    public ResponseEntity<ErrorResponse> notDisableException(ClientDisableException ex) {
    	 return new ResponseEntity<>(ErrorResponse.builder().error("Cliente no deshabilitado")
                 .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                 .message(ex.getMessage())
                 .timestamp(new Date().toString()).build(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
