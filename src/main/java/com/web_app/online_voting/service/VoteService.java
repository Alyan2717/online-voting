package com.web_app.online_voting.service;

import com.web_app.online_voting.model.*;
import com.web_app.online_voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private VoterService voterService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private ElectionService electionService;
    @Autowired
    private DistrictService districtService;

    public vote castVote(vote vote) {
        try{
            voter voter = voterService.findByVoterId(vote.voterId);
            if(voter.voterHasVoted){
                throw new RuntimeException("Voter has voted.");
            }
            candidate candidate = candidateService.getCandidateById(vote.candidateId);
            if(vote.districtId.equals(candidate.districtId)){
                voter.voterHasVoted = true;
                voterService.updateVoter(voter);
                return voteRepository.insert(vote);
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<candidate> getWinnerDistrict(String electionId, String districtId) {
        var voteCount = getVoteCountDistrict(electionId, districtId);
        List<candidate> candidateList = new ArrayList<>();
        List<String> winnerCandidateIds = new ArrayList<>();
        int maxVotes = 0;

        for (Map.Entry<String, Integer> entry : voteCount.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winnerCandidateIds.clear();
                winnerCandidateIds.add(entry.getKey());
            }
            else if (entry.getValue() == maxVotes) {
                winnerCandidateIds.add(entry.getKey());
            }
        }
        for(String id : winnerCandidateIds){
            candidate candidate = candidateService.getCandidateById(id);
            candidateList.add(candidate);
        }
        return candidateList;
    }

    public Map<String,Integer> getVoteCountDistrict(String electionId, String districtId) {
        List<vote> voteList = voteRepository.findByDistrictIdAndElectionId(districtId, electionId);
        Map<String,Integer> voteCounter = new HashMap<>();
        for(vote vote : voteList){
            voter voter = voterService.findByVoterId(vote.voterId);
            candidate candidate = candidateService.getCandidateById(vote.candidateId);
            voteCounter.put(candidate.id, voteCounter.getOrDefault(candidate.id, 0) + 1);
        }
        return voteCounter;
    }

    public Map<String, Integer> getVoteCountCandidate(String electionId, String districtId) {
        Map<String, Integer> candidateVoteCounter = new HashMap<>();
        var voteCounter = getVoteCountDistrict(electionId, districtId);
        for (Map.Entry<String, Integer> entry : voteCounter.entrySet()){
            candidate candidate = candidateService.getCandidateById(entry.getKey());
            candidateVoteCounter.put(candidate.candidateName, entry.getValue());
        }
        return candidateVoteCounter;
    }
}
