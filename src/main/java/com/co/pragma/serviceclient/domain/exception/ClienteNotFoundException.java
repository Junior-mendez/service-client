package com.co.pragma.serviceclient.domain.exception;


public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException(String typeDocument, String numberDocument){
        super("Cliente no encontrado con Tipo de Documento:".concat(typeDocument).concat(" y Número de Documento: ").concat(numberDocument));
    }

}
