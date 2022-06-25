package com.co.pragma.serviceclient.application.models.request;


import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
	

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("lastname")
	private String lastname;

	@JsonProperty("type_document")
	@NotBlank
	private String typeDocument;

	@JsonProperty("number_document")
	@NotBlank
	private String numberDocument;

	@JsonProperty("age")
	private Integer age;

	@JsonProperty("birth_city")
	private String birthCity;

}
