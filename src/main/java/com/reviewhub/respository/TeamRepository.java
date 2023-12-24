package com.reviewhub.respository;

import com.reviewhub.entities.Team;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TeamRepository extends MongoRepository<Team, ObjectId> {
    Team findByName(String name);

    ArrayList<Team> findByAdminsContaining(ObjectId id);
}
