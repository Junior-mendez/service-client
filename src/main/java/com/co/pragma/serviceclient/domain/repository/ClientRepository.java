package com.co.pragma.serviceclient.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.co.pragma.serviceclient.domain.exception.ClientCreateException;
import com.co.pragma.serviceclient.infraestructure.repository.mysql.entity.ClientEntity;

@Repository
public interface ClientRepository{
	
	List<ClientEntity> getAllClients();
	
	ClientEntity createClient(ClientEntity client) ;
	
	ClientEntity getByTypeAndNumber(String typeDocument, String numberDocument);
	
	List<ClientEntity> getByAgeGreater (Integer age);
	
	Optional<ClientEntity>  findById(Long id);
	
	ClientEntity updateClient(ClientEntity client) ;
	
	ClientEntity disableClient(ClientEntity client);

}
