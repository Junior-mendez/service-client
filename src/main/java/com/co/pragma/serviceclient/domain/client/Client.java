package com.co.pragma.serviceclient.domain.client;

import org.springframework.web.multipart.MultipartFile;

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
	
	private MultipartFile image;
	
	private String imageBase64;
	
	public ClientBuilder adapted() {
        return Client.builder()
                .id(id)
                .name(name)
                .lastname(lastname)
                .typeDocument(typeDocument)
                .age(age)
                .numberDocument(numberDocument)
                .birthCity(birthCity)
                .state(state)
                .imageBase64(imageBase64);
    }

}
