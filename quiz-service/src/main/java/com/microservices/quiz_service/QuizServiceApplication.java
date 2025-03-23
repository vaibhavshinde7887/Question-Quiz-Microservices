package com.microservices.quiz_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // use for enabling to use Feign Client to communicate with other microservices
public class QuizServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizServiceApplication.class, args);
	}


	//in Quiz service we can asking the user , u want a Quiz?
	// ok i will do it for u but actually quiz service doing nothing it just say.
	// Hey Question generate a Quiz for me
}
