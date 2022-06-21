package com.co.pragma.serviceclient.application.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
	
	private String name;
	
	private String lastname;
	
	@NonNull
	private String typeDocument;
	
	@NonNull
	private String numberDocument;
	
	private Integer age;
	
	private String birthCity;
	
	private Boolean state;

}
