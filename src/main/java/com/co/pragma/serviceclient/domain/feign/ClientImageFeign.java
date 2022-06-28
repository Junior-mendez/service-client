package com.co.pragma.serviceclient.domain.feign;


import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.co.pragma.serviceclient.domain.feign.dto.ClientImageDTO;

@FeignClient(name = "service-client-image", url = "${url.service-client-image}")
public interface ClientImageFeign {
	
	@PostMapping(value = "/create")
	Optional<ClientImageDTO> createClientImage(
			@RequestBody ClientImageDTO image);
	
	@PostMapping(value = "/update")
	Optional<ClientImageDTO> updateClientImage(
			@RequestBody ClientImageDTO image);
	
	@GetMapping(value = "/{id}")
	Optional<ClientImageDTO> getClientImage(
			@PathVariable String id);

}
