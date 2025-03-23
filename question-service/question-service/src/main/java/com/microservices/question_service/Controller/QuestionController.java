package com.microservices.question_service.Controller;


import com.microservices.question_service.Entity.Question;
import com.microservices.question_service.Entity.QuestionWrapper;
import com.microservices.question_service.Entity.Response;
import com.microservices.question_service.Service.QuestionService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment; // it is used to get the port number of the server which is running the application

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();

    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {

        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);

    }

    @GetMapping("generate") // this is used to generate the questions for the quiz from the database
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestions ){ // this is used to get the questions for the quiz from the database
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }


    @PostMapping("getQuestions") // This will basically returning questions from the database(only Qs not ans)
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port")); // it is used to get the port number of the server which is running the application in which instance
        return questionService.getQuestionsFromId(questionIds);
    }
    // generate the questions for the quiz
    // getQuestions (questionid) by there id
    // getScore for calculate the score
    @PostMapping("getScore") // it return only one score becouase  the number of student  the submit the quiz to the quiz database and
    // the quiz database send to the question service and the question service send the score to the quiz database to get the score
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }
}
