package com.web_app.online_voting.repository;

import com.web_app.online_voting.model.voter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VoterRepository extends MongoRepository<voter, String> {
    voter findByVoterEmail(String voterEmail);
    List<voter> findByDistrictId(String districtId);
}
