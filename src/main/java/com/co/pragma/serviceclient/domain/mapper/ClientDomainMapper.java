package com.co.pragma.serviceclient.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.co.pragma.serviceclient.domain.client.Client;
import com.co.pragma.serviceclient.infraestructure.repository.mysql.entity.ClientEntity;

@Mapper(componentModel = "spring")
public interface ClientDomainMapper {
	
	@Mapping(source = "client.state",target = "state", defaultValue = "true")
	ClientEntity clientDomainToClientEntity (Client client);
	
	
	List<Client> clientsEntityToClientsDomain(List<ClientEntity> clients);

	Client clientEntityToClientDomain (ClientEntity clientEntity);

}
