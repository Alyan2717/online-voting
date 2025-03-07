package com.web_app.online_voting.service;

import com.web_app.online_voting.model.election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web_app.online_voting.repository.ElectionRepository;

import java.util.List;

@Service
public class ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    public election createElection(election election) {
        return electionRepository.insert(election);
    }

    public List<election> getAllElections() {
        return electionRepository.findAll();
    }

    public election getElectionById(String id) {
        return electionRepository.findById(id).orElseThrow(() -> new RuntimeException("Election not found"));
    }
}
