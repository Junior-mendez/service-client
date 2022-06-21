package com.co.pragma.servicecliente;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.pragma.serviceclient.ServiceClienteApplication;

@RunWith(SpringRunner.class)
@SpringBootConfiguration
@SpringBootTest
public class ServiceClienteApplicationTests {
	
	@Test
	  public void main() {
	    ServiceClienteApplication.main(new String[] {});
	  }

}
