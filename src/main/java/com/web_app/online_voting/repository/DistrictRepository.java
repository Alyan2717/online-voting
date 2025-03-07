package com.web_app.online_voting.repository;

import com.web_app.online_voting.model.district;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DistrictRepository extends MongoRepository<district, String> {
}
