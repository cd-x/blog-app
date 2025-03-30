package com.example.springlearnings.persistence;

import com.example.springlearnings.entity.Journal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.transaction.annotation.Transactional;

public interface IJournalRepository extends MongoRepository<Journal, String> {
    @Transactional
    @Query(value = "{'_id':?0, 'username': ?1}")
    @Update("{'content' : ?2}")
    public void updateContent(String journalId, String username, String content);
}
