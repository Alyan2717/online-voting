package com.web_app.online_voting.repository;

import com.web_app.online_voting.model.election;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ElectionRepository extends MongoRepository<election, String> {

}
