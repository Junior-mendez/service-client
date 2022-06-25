package com.co.pragma.serviceclient.domain.service;

import java.util.List;
import java.util.Optional;

import com.co.pragma.serviceclient.domain.client.Client;
import com.co.pragma.serviceclient.domain.exception.ClientCreateException;

public interface ClientService {
	
	
	List<Client> getAllClients();
	
	Client createClient(Client client) throws ClientCreateException;
	
	
	Client getByTypeAndNumer(String typeDocument, String numberDocument);
	
	
	List<Client> getByAgeGreater(Integer age);
	

	Client updateClient(Client client, Long id) ;
	
	void disableClient( Long id) ;
	
}
