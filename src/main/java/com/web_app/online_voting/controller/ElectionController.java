package com.web_app.online_voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.web_app.online_voting.model.election;
import com.web_app.online_voting.service.ElectionService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/election")
public class ElectionController {
    @Autowired
    private ElectionService electionService;

    @PostMapping("/create")
    public ResponseEntity<?> createElection(@RequestBody election election) {
        try {
            var result = electionService.createElection(election);
            Map<String, Object> response = new HashMap<>();
            response.put("election", result);
            response.put("success", true);
            response.put("message", "Election created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
