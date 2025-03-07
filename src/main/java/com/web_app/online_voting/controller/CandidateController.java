package com.web_app.online_voting.controller;

import com.web_app.online_voting.model.candidate;
import com.web_app.online_voting.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @PostMapping("/create")
    public ResponseEntity<?> createCandiate(@RequestBody candidate candidate) {
        try{
            var result = candidateService.createCandidate(candidate);
            Map<String, Object> response = new HashMap<>();
            response.put("candidate", result);
            response.put("success", true);
            response.put("message", "Candidate created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
