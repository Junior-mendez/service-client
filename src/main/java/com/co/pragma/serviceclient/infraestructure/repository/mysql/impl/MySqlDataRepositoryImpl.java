package com.co.pragma.serviceclient.infraestructure.repository.mysql.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.co.pragma.serviceclient.domain.exception.ClientCreateException;
import com.co.pragma.serviceclient.domain.repository.ClientRepository;
import com.co.pragma.serviceclient.infraestructure.repository.mysql.MySqlRepository;
import com.co.pragma.serviceclient.infraestructure.repository.mysql.entity.ClientEntity;

@Component
public class MySqlDataRepositoryImpl implements ClientRepository {
	

	@Autowired
	private MySqlRepository mySqlRepository;
	
	@Override
	public List<ClientEntity> getAllClients() {
		
		return mySqlRepository.findByStateIsTrue();
	}


	@Override
	public ClientEntity getByTypeAndNumber(String typeDocument, String numberDocument) {
		return mySqlRepository.findByTypeDocumentAndNumberDocumentAndStateIsTrue(typeDocument, numberDocument);
	}


	@Override
	public ClientEntity createClient(ClientEntity client)  {
		return mySqlRepository.save(client);
	}


	@Override
	public Optional<ClientEntity> findById(Long id) {
		return mySqlRepository.findById(id);
	}


	@Override
	public ClientEntity updateClient(ClientEntity client) {
		return mySqlRepository.save(client);
	}


	@Override
	public ClientEntity disableClient(ClientEntity client) {
		
		return mySqlRepository.save(client);
		
	}


	@Override
	public List<ClientEntity> getByAgeGreater(Integer age) {
		
		return mySqlRepository.findByAgeGreaterThanEqual(age);
	}


	
	
	

}
