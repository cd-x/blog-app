package com.example.springlearnings.persistence;
import com.example.springlearnings.entity.Journal;
import com.example.springlearnings.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserRepository extends MongoRepository<User, ObjectId> {

    @Query(value = "{ 'username' : ?0 }")
    User findByUsername(String username);

    @Query(value = "{ 'username' : ?0 }", exists = true)
    boolean isUserRegistered(String username);

    @Query(value = "{ 'username' : ?0 }", delete = true)
    void deleteByUsername(String username);

    @Transactional
    @Query(value = "{'username': ?0}")
    @Update(value = "{'$push: {'journalList': ?1}}")
    void updateJournalList(String username, Journal journal);
}
