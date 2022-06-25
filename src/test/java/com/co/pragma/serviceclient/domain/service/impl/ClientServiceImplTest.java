package com.co.pragma.serviceclient.domain.service.impl;


import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.pragma.helpers.ClientHelper;
import com.co.pragma.serviceclient.domain.feign.ClientImageFeign;
import com.co.pragma.serviceclient.domain.mapper.ClientDomainMapper;
import com.co.pragma.serviceclient.domain.repository.ClientRepository;
import com.co.pragma.serviceclient.infraestructure.repository.mysql.MySqlRepository;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
	
	@Mock
	ClientRepository clientRepository;
	
	@Mock
	MySqlRepository mySqlRepository;
	
	@Mock
	ClientImageFeign clientImageFeign;
	
	@Mock
	ClientDomainMapper clientDomainMapper;
	@InjectMocks
	private	ClientServiceImpl clientService;

	@Test
	 void testGetAllClients() {
		when(clientRepository.getAllClients()).thenReturn(ClientHelper.createListClientsEntity());
		when(clientDomainMapper.clientsEntityToClientsDomain(ClientHelper.createListClientsEntity()))
		.thenReturn(ClientHelper.createListClients());
		
		clientService.getAllClients();
	}
	

	@Test
	 void testGetClientByTypeAndNumberDocumet() {
		when(clientRepository.getByTypeAndNumber(Mockito.anyString(), Mockito.anyString()))
		.thenReturn(ClientHelper.createClientEntity());
		when(clientDomainMapper.clientEntityToClientDomain(Mockito.any()))
		.thenReturn(ClientHelper.createClientDomain());
		when(clientImageFeign.getClientImage(Mockito.anyString())).thenReturn(ClientHelper.createClientImageDTO());
		
		clientService.getByTypeAndNumer("DNI", "76515667");
	}
	

	@Test
	 void testGetClientByAge() {
		when(clientRepository.getByAgeGreater(Mockito.anyInt()))
		.thenReturn(ClientHelper.createListClientsEntity());
		when(clientDomainMapper.clientsEntityToClientsDomain(Mockito.any()))
		.thenReturn(ClientHelper.createListClients());
		
		clientService.getByAgeGreater(20);
	}


}
