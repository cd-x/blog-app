package com.example.springlearnings.persistence;

import com.example.springlearnings.entity.Journal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IJournalRepository extends MongoRepository<Journal, String> {
}
