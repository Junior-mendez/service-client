package com.co.pragma.serviceclient.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import com.co.pragma.serviceclient.application.models.request.ClientRequest;
import com.co.pragma.serviceclient.domain.client.Client;

@Mapper(componentModel = "spring")
public interface ClientApplicationMapper {
	
	@Mapping(source = "clientRequest.name",target ="name" )
	@Mapping(source = "clientRequest.lastname",target ="lastname" )
	@Mapping(source = "clientRequest.typeDocument",target ="typeDocument" )
	@Mapping(source = "clientRequest.numberDocument",target ="numberDocument" )
	@Mapping(source = "clientRequest.age",target ="age" )
	@Mapping(source = "clientRequest.birthCity",target ="birthCity" )
	@Mapping(source = "image",target ="image")
	Client clientRequesttoClientDomainImage(ClientRequest clientRequest, MultipartFile image);
	
	@Mapping(source = "clientRequest.name",target ="name" )
	@Mapping(source = "clientRequest.lastname",target ="lastname" )
	@Mapping(source = "clientRequest.typeDocument",target ="typeDocument" )
	@Mapping(source = "clientRequest.numberDocument",target ="numberDocument" )
	@Mapping(source = "clientRequest.age",target ="age" )
	@Mapping(source = "clientRequest.birthCity",target ="birthCity" )
	Client clientRequesttoClientDomain(ClientRequest clientRequest);

}
