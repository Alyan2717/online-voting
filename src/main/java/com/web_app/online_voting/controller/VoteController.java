package com.web_app.online_voting.controller;

import com.web_app.online_voting.model.vote;
import com.web_app.online_voting.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/cast")
    public ResponseEntity<?> castVote(@RequestBody vote vote) {
        try{
            var result = voteService.castVote(vote);
            Map<String, Object> response = new HashMap<>();
            response.put("vote", result);
            response.put("success", true);
            response.put("message", "Vote casted successfully");
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/winner-district")
    public ResponseEntity<?> getWinnerDistrict(@RequestParam String electionId, @RequestParam String districtId) {
        try{
            var result = voteService.getWinnerDistrict(electionId, districtId);
            Map<String, Object> response = new HashMap<>();
            response.put("winner", result);
            response.put("success", true);
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/voteCount")
    public ResponseEntity<?> getVoteCount(@RequestParam String electionId, @RequestParam String districtId) {
        try{
            var voteCount = voteService.getVoteCountCandidate(electionId, districtId);
            Map<String, Object> response = new HashMap<>();
            response.put("voteCount", voteCount);
            response.put("success", true);
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
