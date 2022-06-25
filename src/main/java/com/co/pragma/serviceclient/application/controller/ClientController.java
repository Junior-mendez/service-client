package com.co.pragma.serviceclient.application.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.co.pragma.serviceclient.domain.client.Client;
import com.co.pragma.serviceclient.domain.exception.ClientCreateException;
import com.co.pragma.serviceclient.domain.exception.ClientDisableException;
import com.co.pragma.serviceclient.domain.exception.ClientNotFoundDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.co.pragma.serviceclient.application.mapper.ClientApplicationMapper;
import com.co.pragma.serviceclient.application.models.request.ClientRequest;
import com.co.pragma.serviceclient.domain.service.ClientService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientApplicationMapper clientApplicationMapper;
	
	@GetMapping("/all")
	public List<Client> listClient() {
		return clientService.getAllClients();
	}
	
	
	
	@GetMapping("/document")
	 @ApiOperation(
		      value = "Returns the information of client",
		      nickname = "Search Type Document and Number Document",
		      response = Client.class)
	@ApiResponses(
		      value = {
		        @ApiResponse(code = 200, message = "Success"),
		        @ApiResponse(code = 400, message = "Bad Request"),
		        @ApiResponse(code = 403, message = "Forbidden"),
		        @ApiResponse(code = 404, message = "Not Found"),
		        @ApiResponse(code = 500, message = "Failure")
		      })
	public ResponseEntity<Client> getClientDocument(@RequestParam String typeDocument,@RequestParam String numberDocument) throws ClientNotFoundDocumentException {
		return new ResponseEntity<>(clientService.getByTypeAndNumer(typeDocument, numberDocument),HttpStatus.OK);
	}
	

	@GetMapping("/age")
	 @ApiOperation(
		      value = "Returns the information of client",
		      nickname = "Search Type Document and Number Document",
		      response = Client.class)
	@ApiResponses(
		      value = {
		        @ApiResponse(code = 200, message = "Success"),
		        @ApiResponse(code = 400, message = "Bad Request"),
		        @ApiResponse(code = 403, message = "Forbidden"),
		        @ApiResponse(code = 404, message = "Not Found"),
		        @ApiResponse(code = 500, message = "Failure")
		      })
	public ResponseEntity<List<Client>> getClientAge(@RequestParam Integer age) throws ClientDisableException {
		return new ResponseEntity<>(clientService.getByAgeGreater(age),HttpStatus.OK);
	}
	
	
	
	 @PostMapping(
			 path="/create",
			 produces = {MediaType.APPLICATION_JSON_VALUE})
	 @ApiOperation(
		      value = "Returns the client created",
		      nickname = "Create client")
	@ApiResponses(
		      value = {
		        @ApiResponse(code = 200, message = "Success"),
		        @ApiResponse(code = 400, message = "Bad Request"),
		        @ApiResponse(code = 403, message = "Forbidden"),
		        @ApiResponse(code = 404, message = "Not Found"),
		        @ApiResponse(code = 500, message = "Failure")
		      })
	public ResponseEntity<Client> createClient( @Validated @ModelAttribute ClientRequest clientRequest,  @RequestPart( name = "image") MultipartFile image ) throws ClientCreateException, IOException {
		Client clientDomain = clientApplicationMapper.clientRequesttoClientDomainImage(clientRequest, image);
		return new ResponseEntity<>(clientService.createClient(clientDomain),HttpStatus.OK);
	}
	 
	 @PostMapping(
			 path="/update/{id}",
			 produces = {MediaType.APPLICATION_JSON_VALUE})
	 @ApiOperation(
		      value = "Returns the client created",
		      nickname = "Update client")
	@ApiResponses(
		      value = {
		        @ApiResponse(code = 200, message = "Success"),
		        @ApiResponse(code = 400, message = "Bad Request"),
		        @ApiResponse(code = 403, message = "Forbidden"),
		        @ApiResponse(code = 404, message = "Not Found"),
		        @ApiResponse(code = 500, message = "Failure")
		      })
	public ResponseEntity<Client> updateClient(@Validated @ModelAttribute ClientRequest clientRequest
			, @PathVariable("id") Long id, @RequestPart("image") MultipartFile image) throws ClientCreateException {
		Client clientDomain = clientApplicationMapper.clientRequesttoClientDomainImage(clientRequest, image);
		return new ResponseEntity<>(clientService.updateClient(clientDomain,id),HttpStatus.OK);
	}
	
	 @PutMapping(
			 path="/disable/{id}")
	 @ApiOperation(
		      value = "Returns the client created",
		      nickname = "Update client")
	@ApiResponses(
		      value = {
		        @ApiResponse(code = 200, message = "Success"),
		        @ApiResponse(code = 400, message = "Bad Request"),
		        @ApiResponse(code = 403, message = "Forbidden"),
		        @ApiResponse(code = 404, message = "Not Found"),
		        @ApiResponse(code = 500, message = "Failure")
		      })
	public ResponseEntity<String> disableClient( @PathVariable("id") Long id) throws ClientCreateException {
		 clientService.disableClient(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}
