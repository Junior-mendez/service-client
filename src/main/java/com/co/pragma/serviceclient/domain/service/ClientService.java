package com.co.pragma.serviceclient.domain.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.co.pragma.serviceclient.domain.client.Client;
import com.co.pragma.serviceclient.domain.exception.ClienteCreateException;
import com.co.pragma.serviceclient.domain.exception.ClienteNotFoundException;

public interface ClientService {
	
	
	List<Client> getAllClients();
	
	Client createClient(Client client) throws ClienteCreateException;
	
	
	Client getByTypeAndNumer(String typeDocument, String numberDocument) throws ClienteNotFoundException;
	

	Client updateClient(Client client, Long id) throws ClienteCreateException;
	
	void disableClient( Long id) throws ClienteCreateException;
	
}
