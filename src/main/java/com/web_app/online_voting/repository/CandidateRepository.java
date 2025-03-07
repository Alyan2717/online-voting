package com.web_app.online_voting.repository;

import com.web_app.online_voting.model.candidate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CandidateRepository extends MongoRepository<candidate, String> {
    List<candidate> findByPartyId(String partyId);
    List<candidate> findByDistrictId(String districtId);
}
