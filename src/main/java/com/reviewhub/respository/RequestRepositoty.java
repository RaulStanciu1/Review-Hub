package com.reviewhub.respository;

import com.reviewhub.entities.Request;
import com.reviewhub.entities.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface RequestRepositoty extends MongoRepository<Request, String> {
    void deleteByTeamNameAndAndUsername(String teamName, String username);

    ArrayList<Request> findAllByTeamNameIn(ArrayList<Team> teams);
}
