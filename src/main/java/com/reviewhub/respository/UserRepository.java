package com.reviewhub.respository;

import com.reviewhub.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByName(String user1);

    Object findByNameAndPassword(String username, String password);

    void deleteByName(String user1);
}
