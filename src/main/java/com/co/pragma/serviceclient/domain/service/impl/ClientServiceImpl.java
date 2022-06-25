package com.co.pragma.serviceclient.domain.service.impl;


import java.util.List;
import java.util.Optional;


import com.co.pragma.serviceclient.domain.client.Client;
import com.co.pragma.serviceclient.domain.exception.ClientCreateException;
import com.co.pragma.serviceclient.domain.exception.ClientDisableException;
import com.co.pragma.serviceclient.domain.exception.ClientNotFoundDocumentException;
import com.co.pragma.serviceclient.domain.exception.ClientNotFoundIdException;
import com.co.pragma.serviceclient.domain.exception.ClientUpdateException;
import com.co.pragma.serviceclient.domain.feign.ClientImageFeign;
import com.co.pragma.serviceclient.domain.feign.dto.ClientImageDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.pragma.serviceclient.domain.mapper.ClientDomainMapper;
import com.co.pragma.serviceclient.domain.repository.ClientRepository;
import com.co.pragma.serviceclient.domain.service.ClientService;
import com.co.pragma.serviceclient.infraestructure.repository.mysql.entity.ClientEntity;
import com.co.pragma.serviceclient.infraestructure.utils.ClientUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientDomainMapper clientDomainMapper;
	
	@Autowired
	private ClientImageFeign clientImageFeign;
	
	@Override
	public List<Client> getAllClients() {
		log.info("ClientService-->getAllClients");
		return clientDomainMapper.clientsEntityToClientsDomain(clientRepository.getAllClients());
	}

	@Override
	public Client createClient(Client client) throws ClientCreateException{
		log.info("ClientService-->createClient");
		ClientEntity clientEntity = clientDomainMapper.clientDomainToClientEntity(client);
		if(Optional.ofNullable(clientRepository.getByTypeAndNumber(client.getTypeDocument(), client.getNumberDocument()))
				.isPresent()){
			throw new ClientCreateException(client);
		}
		return Optional.ofNullable(clientRepository.createClient(clientEntity))
		.map(clientDomainMapper::clientEntityToClientDomain)
		.flatMap(clientSaved -> clientImageFeign.createClientImage(
				new ClientImageDTO(
						client.getTypeDocument().concat(client.getNumberDocument()),
						ClientUtil.convertFiletoBase64(client.getImage())))
		.map(image -> clientSaved.adapted().imageBase64(image.getImage()).build()))
		.orElseThrow(()->new ClientCreateException(client));

	}

	@Override
	public Client getByTypeAndNumer(String typeDocument, String numberDocument) throws ClientNotFoundDocumentException {
		log.info("ClientService-->getByTypeAndNumer");
		log.info(clientImageFeign.getClientImage(typeDocument.concat(numberDocument)).toString());
		 return Optional.ofNullable(clientRepository.getByTypeAndNumber(typeDocument, numberDocument))
				 .map(clientDomainMapper::clientEntityToClientDomain)
				 .flatMap(client -> clientImageFeign.getClientImage(typeDocument.concat(numberDocument))
				 .map(image -> client.adapted().imageBase64(image.getImage()).build()))
				 .orElseThrow(()->new ClientNotFoundDocumentException(typeDocument, numberDocument));		 
	}
	
	@Override
	public Client updateClient(Client client, Long id) {
		log.info("ClientService-->updateClient");
		clientRepository.findById(id).map(clientDomainMapper::clientEntityToClientDomain).orElseThrow(()->new ClientNotFoundIdException(id));
		client.setId(id);
		ClientEntity clientEntity = clientDomainMapper.clientDomainToClientEntity(client);
		return Optional.ofNullable(clientRepository.updateClient(clientEntity))
				.map(clientDomainMapper::clientEntityToClientDomain)
				 .flatMap(clientUpdated -> clientImageFeign.updateClientImage(
							new ClientImageDTO(
									clientUpdated.getTypeDocument().concat(clientUpdated.getNumberDocument()),
									ClientUtil.convertFiletoBase64(client.getImage())))
				.map(image -> clientUpdated.adapted().imageBase64(image.getImage()).build()))
				.orElseThrow(()->new ClientUpdateException(client));
	}

	@Override
	public void disableClient(Long id) {
		log.info("ClientService-->disableClient");
		Client client =clientRepository.findById(id).map(clientDomainMapper::clientEntityToClientDomain).orElseThrow(()->new ClientNotFoundIdException(id));
		client.setState(false);
		Optional.ofNullable(clientRepository.disableClient(clientDomainMapper.clientDomainToClientEntity(client)))
		.orElseThrow(()->new ClientDisableException(id)) ;

	}

	@Override
	public List<Client> getByAgeGreater(Integer age) {
		return Optional.ofNullable(clientRepository.getByAgeGreater(age))
				.map(clientDomainMapper::clientsEntityToClientsDomain)
				.orElseThrow(()-> new ClientDisableException(age.longValue()));
	}
	
	
	

}
