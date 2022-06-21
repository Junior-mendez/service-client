package com.co.pragma.serviceclient.application.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.co.pragma.helpers.ClientHelper;
import com.co.pragma.serviceclient.application.mapper.ClientApplicationMapper;
import com.co.pragma.serviceclient.application.request.ClientRequest;
import com.co.pragma.serviceclient.domain.client.Client;
import com.co.pragma.serviceclient.domain.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@WebMvcTest(ClientController.class)
class ClientControllerTest {
	
	@Autowired 
	private MockMvc mockMvc;
	
	@MockBean
	private ClientService clientService;
	
	@MockBean
	private ClientApplicationMapper clientApplicationMapper;
    static ObjectMapper om = new ObjectMapper();
	@BeforeEach
	  public void setUp() throws Exception {
	    om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	    JacksonTester.initFields(this, om);
	  }

	@Test
	void testallClients() throws Exception {
		when(clientService.getAllClients()).thenReturn(ClientHelper.createListClients());
		
		 mockMvc
	        .perform(
	            get("/client/all"))
	        .andDo(print())
	        .andExpect(status().isOk());
		
	}
	
	@Test
	void testClientByTypeAndNumber() throws Exception {
		when(clientService.getByTypeAndNumer(Mockito.anyString(), Mockito.anyString())).thenReturn(ClientHelper.createClientDomain());
		
		 mockMvc
	        .perform(
	            get("/client/document")
	            .param("typeDocument", "DNI")
	            .param("numberDocument", "76515667"))
	        .andDo(print())
	        .andExpect(status().isOk());
		
	}
	@Test
	void testcreateClient() throws Exception {
		ClientRequest clientRequest = ClientHelper.creeateClientRequest();
		Client clientDomain = ClientHelper.createClientDomainOfRequest();
		when(clientApplicationMapper.clientRequesttoClientDomain(clientRequest)).thenReturn(clientDomain);
		when(clientService.createClient(clientDomain)).thenReturn(clientDomain);
		
		 mockMvc
	        .perform(
	            post("/client/create")
	            .content(om.writeValueAsBytes(clientRequest))
        		.contentType(MediaType.APPLICATION_JSON_VALUE))
	        .andDo(print())
	        .andExpect(status().isOk());
		
	}
	
	@Test
	void testUpdateClient() throws Exception {
		ClientRequest clientRequest = ClientHelper.creeateClientRequest();
		Client clientDomain = ClientHelper.createClientDomainOfRequest();
		when(clientApplicationMapper.clientRequesttoClientDomain(clientRequest)).thenReturn(clientDomain);
		when(clientService.updateClient(clientDomain,11L)).thenReturn(clientDomain);
		
		 mockMvc
	        .perform(
	            put("/client/update/{id}",11L)
	            .content(om.writeValueAsBytes(clientRequest))
        		.contentType(MediaType.APPLICATION_JSON_VALUE))
	        .andDo(print())
	        .andExpect(status().isOk());
		
	}
	
	@Test
	void testDisableClient() throws Exception {

		 mockMvc
	        .perform(
	            put("/client/disable/{id}",11L))
	        .andDo(print())
	        .andExpect(status().isOk());
		
	}


}
