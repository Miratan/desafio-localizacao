package com.desafiolocalizacaoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.desafiolocalizacaoserver.service.EmployeeService;
import com.desafiolocalizacaoserver.service.StoreService;

@SpringBootApplication
public class DesafioLocalizacaoServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DesafioLocalizacaoServerApplication.class, args);
		context.getBean(EmployeeService.class);
		context.getBean(StoreService.class);
	}

}
