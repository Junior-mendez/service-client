package com.co.pragma.helpers;

import java.util.List;
import java.util.Optional;

import com.co.pragma.serviceclient.application.models.request.ClientRequest;
import com.co.pragma.serviceclient.domain.client.Client;
import com.co.pragma.serviceclient.domain.feign.dto.ClientImageDTO;
import com.co.pragma.serviceclient.infraestructure.repository.mysql.entity.ClientEntity;

public class ClientHelper {

	public static List<Client> createListClients(){
		return List.of(createClientDomain());
	}
	
	public static List<ClientEntity> createListClientsEntity(){
		return List.of(createClientEntity());
	}

	public static Client createClientDomain(){
		return  Client.builder().id(1L).name("Junior")
				.lastname("Mendez")
				.typeDocument("DNI")
				.numberDocument("76515669")
				.age(26).build();
	}
	
	public static ClientEntity createClientEntity(){
		return  ClientEntity.builder().id(1L).name("Junior")
				.lastname("Mendez")
				.typeDocument("DNI")
				.numberDocument("76515669")
				.age(26).build();
	}
	
	public static ClientRequest createClientRequest(){
		return  ClientRequest.builder()
				.name("Junior")
						.lastname("Mendez")
						.typeDocument("DNI")
						.numberDocument("76515669")
						.age(26)
				.build();
	}
	
	public static Client createClientDomainOfRequest(){
		return  Client.builder().name("Junior")
				.lastname("Mendez")
				.typeDocument("DNI")
				.numberDocument("76515669")
				.age(26).build();
	}
	
	public static Optional<ClientImageDTO> createClientImageDTO(){
		return  Optional.of(ClientImageDTO.builder()
				.id("DNI76515667")
				.image("fsdfsdfdsf").build());
	}

}
