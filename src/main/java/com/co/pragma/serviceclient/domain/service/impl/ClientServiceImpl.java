package com.co.pragma.serviceclient.domain.service.impl;

import java.util.List;
import java.util.Optional;


import com.co.pragma.serviceclient.domain.client.Client;
import com.co.pragma.serviceclient.domain.exception.ClientCreateException;
import com.co.pragma.serviceclient.domain.exception.ClientDisableException;
import com.co.pragma.serviceclient.domain.exception.ClientNotFoundDocumentException;
import com.co.pragma.serviceclient.domain.exception.ClientNotFoundIdException;
import com.co.pragma.serviceclient.domain.exception.ClientUpdateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.pragma.serviceclient.domain.mapper.ClientDomainMapper;
import com.co.pragma.serviceclient.domain.repository.ClientRepository;
import com.co.pragma.serviceclient.domain.service.ClientService;
import com.co.pragma.serviceclient.infraestructure.repository.mysql.entity.ClientEntity;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientDomainMapper clientDomainMapper;
	
	@Override
	public List<Client> getAllClients() {
		return clientDomainMapper.clientsEntityToClientsDomain(clientRepository.getAllClients());
	}

	@Override
	public Client createClient(Client client) throws ClientCreateException{
		
		ClientEntity clientEntity = clientDomainMapper.clientDomainToClientEntity(client);
		
		if(Optional.ofNullable(clientRepository.getByTypeAndNumber(client.getTypeDocument(), client.getNumberDocument()))
				.isPresent()){
			throw new ClientCreateException(client);
		}
		
		return Optional.ofNullable(clientRepository.createClient(clientEntity))
				.map(clientDomainMapper::clientEntityToClientDomain)
				.orElseThrow(()->new ClientCreateException(client));
	}

	@Override
	public Client getByTypeAndNumer(String typeDocument, String numberDocument) throws ClientNotFoundDocumentException {
		
		 return Optional.ofNullable(clientRepository.getByTypeAndNumber(typeDocument, numberDocument))
					.map(clientDomainMapper::clientEntityToClientDomain)
					.orElseThrow(() ->  new ClientNotFoundDocumentException(typeDocument,numberDocument));
	}
	
	@Override
	public Client updateClient(Client client, Long id) throws ClientCreateException{
		
		clientRepository.findById(id);
		client.setId(id);
		ClientEntity clientEntity = clientDomainMapper.clientDomainToClientEntity(client);
		return Optional.ofNullable(clientRepository.updateClient(clientEntity))
				.map(clientDomainMapper::clientEntityToClientDomain)
				.orElseThrow(()->new ClientUpdateException(client));
	}

	@Override
	public void disableClient(Long id) throws ClientCreateException {
		
		Client client =clientRepository.findById(id).map(clientDomainMapper::clientEntityToClientDomain).orElseThrow(()->new ClientNotFoundIdException(id));
		client.setState(false);
		Optional.ofNullable(clientRepository.disableClient(clientDomainMapper.clientDomainToClientEntity(client)))
		.orElseThrow(()->new ClientDisableException(id)) ;

	}

	

}
