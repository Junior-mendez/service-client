package com.co.pragma.serviceclient.domain.feign.dto;



import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientImageDTO {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("image")
	private String image;

}
