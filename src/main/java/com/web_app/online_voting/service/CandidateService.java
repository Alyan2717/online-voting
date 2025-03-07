package com.web_app.online_voting.service;

import com.web_app.online_voting.model.candidate;
import com.web_app.online_voting.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public candidate createCandidate(candidate candidate) {
        return candidateRepository.insert(candidate);
    }

    public List<candidate> getCandidateByParty(String partyId) {
        return candidateRepository.findByPartyId(partyId);
    }

    public List<candidate> getCandidateByDistrict(String districtId) {
        return candidateRepository.findByDistrictId(districtId);
    }

    public candidate getCandidateById(String id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("candidate not found"));
    }
}
