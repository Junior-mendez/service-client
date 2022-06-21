package com.co.pragma.serviceclient.domain.exception;

import com.co.pragma.serviceclient.domain.client.Client;

public class ClienteCreateException extends Exception{
	public ClienteCreateException(Client client){
        super("Cliente no fue creado: ".concat(client.toString()));
    }
}
