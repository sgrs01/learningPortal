package com.example.greatLearningPortal.controllers;

import com.example.greatLearningPortal.models.*;
import com.example.greatLearningPortal.services.jobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobController {

    @Autowired
    private jobService service;

    @GetMapping("/suitableCandidate/{jobId}")
    List<User> getSuitableCandidateByJobId(@PathVariable int jobId){
        return service.getSuitableCandidateByJobId(jobId);
    }
}
