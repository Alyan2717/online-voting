package com.web_app.online_voting.service;

import com.web_app.online_voting.model.district;
import com.web_app.online_voting.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    public district createDistrict(district district) {
        return districtRepository.insert(district);
    }

    public district getDistrict(String id) {
        return districtRepository.findById(id).orElseThrow(() -> new RuntimeException("District not found"));
    }

    public List<district> getAllDistricts() {
        return districtRepository.findAll();
    }
}
