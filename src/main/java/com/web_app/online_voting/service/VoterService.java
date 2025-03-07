package com.web_app.online_voting.service;

import com.web_app.online_voting.model.voter;
import com.web_app.online_voting.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;

    public voter registerVoter(voter voter) {
        return voterRepository.insert(voter);
    }

    public voter loginVoter(String email, String password) {
        voter voter = voterRepository.findByVoterEmail(email);
        if (voter == null) {
            throw new RuntimeException("Voter not found"); // Or return null
        }
        if(voter.voterPassword.equals(password)) {
            return voter;
        } else {
            throw new RuntimeException("Wrong password");
        }
    }

    public voter findByVoterId(String id){
        return voterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voter not found with id: " + id));
    }

    public List<voter> findByDistrictId(String id){
        return voterRepository.findByDistrictId(id);
    }

    public voter updateVoter(voter voter) {
        return voterRepository.save(voter);
    }
}
