package com.tienda;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiendaSergioAlonsoDecasoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaSergioAlonsoDecasoApplication.class, args);
		BasicConfigurator.configure();
	}

}
