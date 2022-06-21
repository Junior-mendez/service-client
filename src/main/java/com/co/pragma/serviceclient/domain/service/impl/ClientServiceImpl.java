package com.co.pragma.serviceclient.domain.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.co.pragma.serviceclient.domain.client.Client;
import com.co.pragma.serviceclient.domain.exception.ClienteCreateException;
import com.co.pragma.serviceclient.domain.exception.ClienteNotFoundException;
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
	public Client createClient(Client client) throws ClienteCreateException{
		
		ClientEntity clientEntity = clientDomainMapper.clientDomainToClientEntity(client);
		
		if(Optional.ofNullable(clientRepository.getByTypeAndNumber(client.getTypeDocument(), client.getNumberDocument()))
				.isPresent()){
			throw new ClienteCreateException(client);
		}
		
		return Optional.ofNullable(clientRepository.createClient(clientEntity))
				.map(clientDomainMapper::clientEntityToClientDomain)
				.orElseThrow(()->new ClienteCreateException(client));
	}

	@Override
	public Client getByTypeAndNumer(String typeDocument, String numberDocument) throws ClienteNotFoundException {
		
		 return Optional.ofNullable(clientRepository.getByTypeAndNumber(typeDocument, numberDocument))
					.map(clientDomainMapper::clientEntityToClientDomain)
					.orElseThrow(() ->  new ClienteNotFoundException(typeDocument,numberDocument));
	}
	
	@Override
	public Client updateClient(Client client, Long id) throws ClienteCreateException{
		
		clientRepository.findById(id).orElseThrow(()->new ClienteCreateException(client));
		client.setId(id);
		ClientEntity clientEntity = clientDomainMapper.clientDomainToClientEntity(client);
		return Optional.ofNullable(clientRepository.updateClient(clientEntity))
				.map(clientDomainMapper::clientEntityToClientDomain)
				.orElseThrow(()->new ClienteCreateException(client));
	}

	@Override
	public void disableClient(Long id) throws ClienteCreateException {
		
		Client client =clientRepository.findById(id).map(clientDomainMapper::clientEntityToClientDomain).orElseThrow(()->new ClienteCreateException(new Client()));
		client.setState(false);
		clientRepository.disableClient(clientDomainMapper.clientDomainToClientEntity(client));

	}

	

}
