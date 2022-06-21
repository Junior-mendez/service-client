package com.co.pragma.serviceclient.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.co.pragma.serviceclient.application.request.ClientRequest;
import com.co.pragma.serviceclient.domain.client.Client;
import com.co.pragma.serviceclient.infraestructure.repository.mysql.entity.ClientEntity;

@Mapper(componentModel = "spring")
public interface ClientDomainMapper {
	
	ClientEntity clientDomainToClientEntity (Client client);
	
	ClientEntity clientRequestToClientEntity (ClientRequest clientRequest);
	
	List<Client> clientsEntityToClientsDomain(List<ClientEntity> clients);
	
	Client clientEntityToClientDomain (ClientEntity clientEntity);

}
