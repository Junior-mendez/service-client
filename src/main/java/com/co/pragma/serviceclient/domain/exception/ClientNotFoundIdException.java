package com.co.pragma.serviceclient.domain.exception;


public class ClientNotFoundIdException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2606633086728900903L;

	public ClientNotFoundIdException(Long id){
        super("Cliente con id ".concat(id.toString()).concat(" no existe."));
    }
}
