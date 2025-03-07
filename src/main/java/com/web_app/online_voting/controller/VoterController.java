package com.web_app.online_voting.controller;

import com.web_app.online_voting.model.candidate;
import com.web_app.online_voting.model.election;
import com.web_app.online_voting.model.party;
import com.web_app.online_voting.model.voter;
import com.web_app.online_voting.service.CandidateService;
import com.web_app.online_voting.service.ElectionService;
import com.web_app.online_voting.service.PartyService;
import com.web_app.online_voting.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/voter")
public class VoterController {

    @Autowired
    private VoterService voterService;
    @Autowired
    private ElectionService electionService;
    @Autowired
    private PartyService partyService;
    @Autowired
    private CandidateService candidateService;

    @PostMapping("/register")
    public ResponseEntity<?> registerVoter(@RequestBody voter voter) {
        try{
            var result = voterService.registerVoter(voter);
            Map<String, Object> response = new HashMap<>();
            response.put("voter", result);
            response.put("success", true);
            response.put("message", "Voter Successfully Registered.");
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginVoter(@RequestParam String email, @RequestParam String password) {
        try{
            var result = voterService.loginVoter(email, password);
            Map<String, Object> response = new HashMap<>();
            response.put("voter", result);
            response.put("success", true);
            response.put("message", "Voter Successfully Login.");
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/active-election")
    public ResponseEntity<?> getActiveElection() {
        try{
            List<election> activeElections = new ArrayList<>();
            LocalDate currentDate = LocalDate.now();
            var electionList = electionService.getAllElections();
            if(!electionList.isEmpty()){
                for(election election : electionList){
                    LocalDate startDate = LocalDate.parse(election.electionStartDate, DateTimeFormatter.ISO_DATE);
                    LocalDate endDate = LocalDate.parse(election.electionEndDate, DateTimeFormatter.ISO_DATE);
                    if(!currentDate.isBefore(startDate) && !currentDate.isAfter(endDate)){
                        activeElections.add(election);
                    }
                }
            }
            Map<String, Object> response = new HashMap<>();
            response.put("activeElections", activeElections);
            response.put("success", true);
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/party-election")
    public ResponseEntity<?> getPartyByElection(@RequestParam String electionId) {
        try{
            var partyList = partyService.getPartyByElectionId(electionId);
            Map<String, Object> response = new HashMap<>();
            response.put("partyList", partyList);
            response.put("success", true);
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/candidate-district")
    public ResponseEntity<?> getCandidateByDistrict(@RequestParam String districtId) {
        try{
            List<candidate> candidates = new ArrayList<>();
            var candidateList = candidateService.getCandidateByDistrict(districtId);
            var voterList = voterService.findByDistrictId(districtId);
            for (var voter : voterList) {
                for (var candidate : candidateList) {
                    if (voter.districtId.equals(candidate.districtId) && !candidates.contains(candidate)) {
                        candidates.add(candidate);
                    }
                }
            }
            Map<String, Object> response = new HashMap<>();
            response.put("candidateList", candidates);
            response.put("success", true);
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
