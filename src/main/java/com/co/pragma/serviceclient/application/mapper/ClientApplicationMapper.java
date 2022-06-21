package com.co.pragma.serviceclient.application.mapper;

import org.mapstruct.Mapper;

import com.co.pragma.serviceclient.application.request.ClientRequest;
import com.co.pragma.serviceclient.domain.client.Client;

@Mapper(componentModel = "spring")
public interface ClientApplicationMapper {
	
	Client clientRequesttoClientDomain(ClientRequest client);

}
