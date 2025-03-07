package com.web_app.online_voting.repository;

import com.web_app.online_voting.model.party;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PartyRepository extends MongoRepository<party, String> {
    List<party> findByElectionId(String electionId);
}
