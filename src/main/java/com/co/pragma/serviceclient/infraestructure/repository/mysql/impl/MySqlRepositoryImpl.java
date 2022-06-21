package com.co.pragma.serviceclient.infraestructure.repository.mysql.impl;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.co.pragma.serviceclient.domain.exception.ClienteCreateException;
import com.co.pragma.serviceclient.domain.repository.ClientRepository;
import com.co.pragma.serviceclient.infraestructure.repository.mysql.MySqlRepository;
import com.co.pragma.serviceclient.infraestructure.repository.mysql.entity.ClientEntity;

@Component
public class MySqlRepositoryImpl implements ClientRepository {
	
	@Lazy
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
	public ClientEntity createClient(ClientEntity client) throws ClienteCreateException {
		return mySqlRepository.save(client);
	}


	@Override
	public Optional<ClientEntity> findById(Long id) {
		return mySqlRepository.findById(id);
	}


	@Override
	public ClientEntity updateClient(ClientEntity client) throws ClienteCreateException {
		return mySqlRepository.save(client);
	}


	@Override
	public void disableClient(ClientEntity client) {
		
		mySqlRepository.save(client);
		
	}


	
	
	

}
