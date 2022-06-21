package com.co.pragma.helpers;

import java.util.Arrays;
import java.util.List;

import com.co.pragma.serviceclient.application.request.ClientRequest;
import com.co.pragma.serviceclient.domain.client.Client;

public class ClientHelper {

	public static List<Client> createListClients(){
		return List.of(createClientDomain());
	}

	public static Client createClientDomain(){
		return  Client.builder().id(1L).name("Junior")
				.lastname("Mendez")
				.typeDocument("DNI")
				.numberDocument("76515669")
				.age(26).build();
	}
	
	public static ClientRequest creeateClientRequest(){
		return  ClientRequest.builder().name("Junior")
				.lastname("Mendez")
				.typeDocument("DNI")
				.numberDocument("76515669")
				.age(26).build();
	}
	
	public static Client createClientDomainOfRequest(){
		return  Client.builder().name("Junior")
				.lastname("Mendez")
				.typeDocument("DNI")
				.numberDocument("76515669")
				.age(26).build();
	}

}
