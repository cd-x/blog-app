package com.example.springlearnings.persistence.impl;

import com.example.springlearnings.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class UserRepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserWithSentimentAnalysis() {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        List<User> userList = mongoTemplate.find(query, User.class);
        log.debug("user received: {}", userList);
        return userList;
    }
}
