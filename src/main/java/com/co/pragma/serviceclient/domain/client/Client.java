package com.co.pragma.serviceclient.domain.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	
	private Long id;
	
	private String name;
	
	private String lastname;
	
	private String typeDocument;
	
	private String numberDocument;
	
	private Integer age;
	
	private String birthCity;
	
	private Boolean state;

}
