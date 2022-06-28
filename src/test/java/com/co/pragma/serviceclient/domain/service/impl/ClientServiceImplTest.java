package com.co.pragma.serviceclient.domain.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.co.pragma.helpers.ClientHelper;
import com.co.pragma.serviceclient.domain.client.Client;
import com.co.pragma.serviceclient.domain.exception.ClientCreateException;
import com.co.pragma.serviceclient.domain.exception.ClientDisableException;
import com.co.pragma.serviceclient.domain.exception.ClientNotFoundIdException;
import com.co.pragma.serviceclient.domain.exception.ClientUpdateException;
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
		assertEquals(1L,  clientService.getAllClients().get(0).getId());
	}
	

	@Test
	 void testGetClientByTypeAndNumberDocumet() {
		when(clientRepository.getByTypeAndNumber(Mockito.anyString(), Mockito.anyString()))
		.thenReturn(ClientHelper.createClientEntity());
		when(clientDomainMapper.clientEntityToClientDomain(Mockito.any()))
		.thenReturn(ClientHelper.createClientDomain());
		when(clientImageFeign.getClientImage(Mockito.anyString())).thenReturn(ClientHelper.createClientImageDTO());

		assertEquals(1L, clientService.getByTypeAndNumer("DNI", "76515667").getId());
	}
	

	@Test
	 void testGetClientByAge() {
		when(clientRepository.getByAgeGreater(Mockito.anyInt()))
		.thenReturn(ClientHelper.createListClientsEntity());
		when(clientDomainMapper.clientsEntityToClientsDomain(Mockito.any()))
		.thenReturn(ClientHelper.createListClients());
		
		assertEquals(1L,clientService.getByAgeGreater(20).get(0).getId());
	}

	@Test
	 void testCreateClient() throws ClientCreateException {
		when(clientDomainMapper.clientDomainToClientEntity(Mockito.any())).thenReturn(ClientHelper.createClientEntity());
		when(clientDomainMapper.clientEntityToClientDomain(Mockito.any())).thenReturn(ClientHelper.createClientDomain());
		when(clientRepository.getByTypeAndNumber(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		when(clientRepository.createClient(Mockito.any())).thenReturn(ClientHelper.createClientEntity());
		when(clientImageFeign.createClientImage(Mockito.any())).thenReturn(ClientHelper.createClientImageDTO());

		assertEquals(1L,clientService.createClient(ClientHelper.createClientDomainCreate())
		.getId());
	}

	
	@Test
	 void testCreateClientException() throws ClientCreateException {
		when(clientDomainMapper.clientDomainToClientEntity(Mockito.any())).thenReturn(ClientHelper.createClientEntity());
		when(clientRepository.getByTypeAndNumber(Mockito.anyString(), Mockito.anyString())).thenReturn(ClientHelper.createClientEntity());
		assertThrows(ClientCreateException.class,
		()->{clientService.createClient(ClientHelper.createClientDomainCreate());});
	}
	
	@Test
	 void testCreateClientFail() throws ClientCreateException {
		when(clientDomainMapper.clientDomainToClientEntity(Mockito.any())).thenReturn(ClientHelper.createClientEntity());
		when(clientRepository.getByTypeAndNumber(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		when(clientRepository.createClient(Mockito.any())).thenReturn(null);
		assertThrows(ClientCreateException.class,
		()->{clientService.createClient(ClientHelper.createClientDomainCreate());});
	}
	

	@Test
	 void testUpdateClient() throws ClientCreateException {
		when(clientDomainMapper.clientDomainToClientEntity(Mockito.any())).thenReturn(ClientHelper.createClientEntity());
		when(clientDomainMapper.clientEntityToClientDomain(Mockito.any())).thenReturn(ClientHelper.createClientDomain());
		when(clientRepository.findById(Mockito.any())).thenReturn(Optional.of(ClientHelper.createClientEntity()));
		when(clientRepository.updateClient(Mockito.any())).thenReturn(ClientHelper.createClientEntity());
		when(clientImageFeign.updateClientImage(Mockito.any())).thenReturn(ClientHelper.createClientImageDTO());

		assertEquals(1L,clientService.updateClient(ClientHelper.createClientDomainUpdate(),1L)
		.getId());
	}
	
	@Test
	 void testUpdateClientFail() throws ClientCreateException {
		when(clientDomainMapper.clientDomainToClientEntity(Mockito.any())).thenReturn(ClientHelper.createClientEntity());
		when(clientDomainMapper.clientEntityToClientDomain(Mockito.any())).thenReturn(ClientHelper.createClientDomain());
		when(clientRepository.findById(Mockito.any())).thenReturn(Optional.of(ClientHelper.createClientEntity()));
		when(clientRepository.updateClient(Mockito.any())).thenReturn(null);
	
		assertThrows(ClientUpdateException.class,
				()->{clientService.updateClient(ClientHelper.createClientDomainUpdate(),1L);});
	}
	
	
	@Test
	void testDisableClient() {
		when(clientDomainMapper.clientDomainToClientEntity(Mockito.any())).thenReturn(ClientHelper.createClientEntity());
		when(clientRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(ClientHelper.createClientEntity()));
		when(clientDomainMapper.clientEntityToClientDomain(Mockito.any())).thenReturn(ClientHelper.createClientDomain());
		when(clientRepository.disableClient(Mockito.any())).thenReturn(ClientHelper.createClientEntity());

		clientService.disableClient(1L);
	}
	
	@Test
	void testDisableClientFail() {
		when(clientDomainMapper.clientDomainToClientEntity(Mockito.any())).thenReturn(ClientHelper.createClientEntity());
		when(clientDomainMapper.clientEntityToClientDomain(Mockito.any())).thenReturn(ClientHelper.createClientDomain());
		when(clientRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(ClientHelper.createClientEntity()));
		when(clientRepository.disableClient(Mockito.any())).thenReturn(null);

		assertThrows(ClientDisableException.class,
				()->{clientService.disableClient(1L);});
	}
	
}
