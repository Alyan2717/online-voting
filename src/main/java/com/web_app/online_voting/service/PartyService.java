package com.web_app.online_voting.service;

import com.web_app.online_voting.model.party;
import com.web_app.online_voting.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyService {
    @Autowired
    private PartyRepository partyRepository;

    public party createParty(party party){
        return partyRepository.insert(party);
    }

    public List<party> getPartyByElectionId(String electionId){
        return partyRepository.findByElectionId(electionId);
    }
}
