package com.example.springlearnings.persistence;

import com.example.springlearnings.entity.Journal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.transaction.annotation.Transactional;

public interface IJournalRepository extends MongoRepository<Journal, String> {
}
