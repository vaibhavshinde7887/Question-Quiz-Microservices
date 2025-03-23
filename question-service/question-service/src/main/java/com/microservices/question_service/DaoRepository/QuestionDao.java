package com.microservices.question_service.DaoRepository;


import com.microservices.question_service.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true) // this Querry is used to get the random questions from the database it is pick the random questions from the database
    List<Integer> findRandomQuestionsByCategory(String category, int numQ); // this is used to get the questions for the quiz from the database
}
