package com.example.springlearnings.persistence.interfaces;

import com.example.springlearnings.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IUserRepository extends MongoRepository<User, ObjectId> {

    @Query(value = "{ 'username' : ?0 }")
    User findByUsername(String username);

    @Query(value = "{ 'username' : ?0 }", exists = true)
    boolean isUserRegistered(String username);

    @Query(value = "{ 'username' : ?0 }", delete = true)
    void deleteByUsername(String username);
}
