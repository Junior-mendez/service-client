package com.co.pragma.serviceclient.domain.exception;

import com.co.pragma.serviceclient.domain.client.Client;

public class ClientCreateException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1170133572788774849L;

	public ClientCreateException(Client client){
        super("Cliente no fue creado: ".concat(client.toString()));
    }
}
