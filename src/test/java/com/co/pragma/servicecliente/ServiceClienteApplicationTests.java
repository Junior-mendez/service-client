package com.co.pragma.servicecliente;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import com.co.pragma.serviceclient.ServiceClienteApplication;

@ContextConfiguration(classes = { ServiceClienteApplication.class })
class ServiceClienteApplicationTests {
	
	@Test
	  void main() {
	    ServiceClienteApplication.main(new String[] {});
	  }

}
