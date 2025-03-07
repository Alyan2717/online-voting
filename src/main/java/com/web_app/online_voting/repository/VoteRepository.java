package com.web_app.online_voting.repository;

import com.web_app.online_voting.model.vote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VoteRepository extends MongoRepository<vote, String> {
    List<vote> findByDistrictIdAndElectionId(String districtId, String electionId);
}
