package com.microservices.quiz_service.Service;

import com.microservices.quiz_service.DaoRepository.QuizDao;

import com.microservices.quiz_service.Entity.QuestionWrapper;
import com.microservices.quiz_service.Entity.Quiz;
import com.microservices.quiz_service.Entity.Response;
import com.microservices.quiz_service.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface; // we use this for feign client to connect to the question service for quiz service


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) { // use for logic of creating the quiz

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody(); // we need to call generate url - RestTemplate - http://localhost:8080/question/generate
        Quiz quiz = new Quiz(); // create a new quiz object
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz); // save the quiz object in the database

        return new ResponseEntity<>("Successfully Create a Quiz", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) { // this is used to get the questions from the database
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds); // this is return the questions from the database
        return questions; // and return the questions

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) { // this is used to calculate the total score of the quiz
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}