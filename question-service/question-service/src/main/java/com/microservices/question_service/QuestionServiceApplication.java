package com.microservices.question_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionServiceApplication.class, args);
	}


//	in Question service we can add Question, we can remove Question,
//	we can search for the particular Question, we can generate
//	Question for quiz, we can get Question for a particular id or list of ids, we can calculate the code
}
