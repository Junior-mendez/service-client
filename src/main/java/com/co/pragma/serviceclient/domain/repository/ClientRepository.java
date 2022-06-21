package com.co.pragma.serviceclient.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.co.pragma.serviceclient.domain.exception.ClienteCreateException;
import com.co.pragma.serviceclient.infraestructure.repository.mysql.entity.ClientEntity;

@Repository
public interface ClientRepository{
	
	List<ClientEntity> getAllClients();
	
	ClientEntity createClient(ClientEntity client) throws ClienteCreateException;
	
	ClientEntity getByTypeAndNumber(String typeDocument, String numberDocument);
	
	Optional<ClientEntity>  findById(Long id);
	
	ClientEntity updateClient(ClientEntity client) throws ClienteCreateException;
	
	void disableClient(ClientEntity client);

}
