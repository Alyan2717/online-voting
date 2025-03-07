package com.web_app.online_voting.controller;

import com.web_app.online_voting.model.district;
import com.web_app.online_voting.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @PostMapping("/create")
    public ResponseEntity<?> createDistrict(@RequestBody district district) {
        try{
            var result = districtService.createDistrict(district);
            Map<String, Object> response = new HashMap<>();
            response.put("district", result);
            response.put("success", true);
            response.put("message", "District created");
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getDistrict() {
        try{
            List<district> districtList = districtService.getAllDistricts();
            Map<String, Object> response = new HashMap<>();
            response.put("districtList", districtList);
            response.put("success", true);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
