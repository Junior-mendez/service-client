package com.co.pragma.serviceclient.application.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
	
	private String name;
	
	private String lastname;
	
	private String typeDocument;
	
	private String numberDocument;
	
	private Integer age;
	
	private String birthCity;
	
	private String image;

}
