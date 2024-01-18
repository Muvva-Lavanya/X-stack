package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class JenkinsDemoApplication {
	
	@PostConstruct
	public void printMessage()
	{
		log.info("Jenkins demo.....");
		log.info("helloo.........");
		log.info("hiiii");
	}

	public static void main(String[] args) {
		SpringApplication.run(JenkinsDemoApplication.class, args);
	}

}
