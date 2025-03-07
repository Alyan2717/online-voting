package com.web_app.online_voting.controller;

import com.web_app.online_voting.model.party;
import com.web_app.online_voting.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/party")
public class PartyElection {
    @Autowired
    private PartyService partyService;

    @PostMapping("/create")
    public ResponseEntity<?> createParty(@RequestBody party party) {
        try {
            var result = partyService.createParty(party);
            Map<String, Object> response = new HashMap<>();
            response.put("party", result);
            response.put("success", true);
            response.put("message", "Party created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
