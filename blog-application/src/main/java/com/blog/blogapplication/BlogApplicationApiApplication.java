package com.blog.blogapplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * This class represents the main entry point for the Blog Application API.
 */
@SpringBootApplication
public class BlogApplicationApiApplication {

	/**
	 * The main method that starts the Spring Boot application.
	 *
	 * @param args The command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BlogApplicationApiApplication.class, args);
	}

	/**
	 * Bean configuration method for creating a ModelMapper instance.
	 *
	 * @return ModelMapper An instance of ModelMapper to be used for object mapping.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
