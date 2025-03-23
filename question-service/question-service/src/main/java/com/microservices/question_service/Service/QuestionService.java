package com.microservices.question_service.Service;


import com.microservices.question_service.DaoRepository.QuestionDao;
import com.microservices.question_service.Entity.Question;
import com.microservices.question_service.Entity.QuestionWrapper;
import com.microservices.question_service.Entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService { // this Question service is used by Quiz service to get the questions from the database

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);

    }

        public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) { // this is return the questions for the quiz
            List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) { // this is used to get the questions from the database
        List<QuestionWrapper> wrappers = new ArrayList<>(); // this method is user to list the questions from the database
        List<Question> questions = new ArrayList<>(); // and this is for the questions

        for(Integer id : questionIds){
            questions.add(questionDao.findById(id).get()); // it is bassically get the questions from the database by the id of the questions
        }

        for(Question question : questions){ // we use this for loop to get the questions from the database and then we will add the questions to the list
            QuestionWrapper wrapper = new QuestionWrapper(); // we create the object of the QuestionWrapper class
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper); // we add this to the list
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }
    public ResponseEntity<Integer> getScore(List<Response> responses) { // it is use for get score how many Q are right and add to the database

        int right = 0;

        for(Response response : responses){ // i use for if the id and the response is equal to the right answer in the database then the right++ will be increase
            Question question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer()))
                right++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}