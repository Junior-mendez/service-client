package com.co.pragma.serviceclient.domain.service;

import java.util.List;


import com.co.pragma.serviceclient.domain.client.Client;
import com.co.pragma.serviceclient.domain.exception.ClientCreateException;
import com.co.pragma.serviceclient.domain.exception.ClientNotFoundDocumentException;

public interface ClientService {
	
	
	List<Client> getAllClients();
	
	Client createClient(Client client) throws ClientCreateException;
	
	
	Client getByTypeAndNumer(String typeDocument, String numberDocument) throws ClientNotFoundDocumentException;
	

	Client updateClient(Client client, Long id) throws ClientCreateException;
	
	void disableClient( Long id) throws ClientCreateException;
	
}
