package com.reviewhub.respository;

import com.reviewhub.entities.Team;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team, ObjectId> {
    Team findByName(String name);
}
