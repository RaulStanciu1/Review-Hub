package com.reviewhub.respository;

import com.reviewhub.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    Object findByName(String user1);

    Object findByNameAndPassword(String username, String password);

    void deleteByName(String user1);
}
