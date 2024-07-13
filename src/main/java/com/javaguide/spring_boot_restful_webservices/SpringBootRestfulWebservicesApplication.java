package com.javaguide.spring_boot_restful_webservices;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@Tag(
		name = "CRUD REST API for User Resource",
		description = "CRUD REST API"
)
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot REST API Documentation",
		description = "Spring boot RESt API Documentation",
		version = "v1.0",
		contact = @Contact(
				name = "inci",
				email = "javaguides.net@gmail.com",
				url = "https://www.javaguides.net"
		),
		license = @License(
				name = "Apache 2.0",
				url = "https://www.javaguides.net/licence"
		)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring boot user management documentation",
				url = "htttps://www.javaguides.net/user.management.html"
		)
)

public class SpringBootRestfulWebservicesApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestfulWebservicesApplication.class, args);
	}

}
